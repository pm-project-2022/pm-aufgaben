package Entities.Moveable.Hero;

import Entities.FriendlyNPCs.FriendlyNPC;
import Entities.Items.Item;
import Entities.Moveable.Moveable;
import Helper.PointBooleanTransmitter;
import Inventory.Inventory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import Logger.ColumnFormatter;

public class Hero extends Moveable {
    protected boolean viewDirection;
    protected ArrayList<Item> floorItems;
    protected ArrayList<Item> availableItems;
    protected ArrayList<Item> chests;
    protected ArrayList<Item> traps;
    protected ArrayList<FriendlyNPC> npcs;
    protected Inventory inventory;
    protected Logger log;
    protected String name;


    public Hero(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.viewDirection = true;
        this.inventory = new Inventory();
        this.floorItems = new ArrayList<>();
        this.chests = new ArrayList<>();
        this.traps = new ArrayList<>();
        this.npcs = new ArrayList<>();
        initLogger();
    }

    private void initLogger() {
        log = Logger.getLogger("Hero");
        log.setUseParentHandlers(false);
        log.setLevel(java.util.logging.Level.ALL);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setFormatter(new ColumnFormatter());
        ch.setLevel(java.util.logging.Level.ALL);
        log.addHandler(ch);
    }

    @Override
    public void update() {
        animations(movementUpdate(movementKeyPressed(), this.currentPosition));
        updateExp();
        pickUpItem();
        updateInventoryItemPosition();
        equipItem();
        itemViewDirection();
        dropItem();
        consumeItem();
        showInventory();
        printStats();
        openChest();
        stepOnTrap();
        talkToNpc();
    }

    /**
     * manages and sets animations
     */
    private void animations(boolean keyPressed) {
        if (keyPressed) {
            if (viewDirection) {
                this.activeAnimation = this.runAnimation;
            } else {
                this.activeAnimation = this.runMirroredAnimation;
            }
        } else {
            if (this.viewDirection) {
                this.activeAnimation = this.idleAnimation;
            } else {
                this.activeAnimation = this.idleMirroredAnimation;
            }
        }
    }

    /**
     * checks whether the button has been pressed or not.
     *
     * @return true if a button was pressed, false if not
     */
    private boolean movementKeyPressed() {
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean movementUpdate(boolean keyPressed, Point currentPosition) {
        Point newPosition = new Point(currentPosition);
        if (keyPressed) {
            // Wenn die Taste W gedrückt ist, bewege dich nach oben
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                newPosition.y += this.attributes.getMovementSpeed();
            }
            // Wenn die Taste S gedrückt ist, bewege dich nach unten
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                newPosition.y -= this.attributes.getMovementSpeed();
            }
            // Wenn die Taste D gedrückt ist, bewege dich nach rechts
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                this.viewDirection = true;
                newPosition.x += this.attributes.getMovementSpeed();
            }
            // Wenn die Taste A gedrückt ist, bewege dich nach links
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                this.viewDirection = false;
                newPosition.x -= this.attributes.getMovementSpeed();
            }
            if (this.currentFloor.getTileAt(newPosition.toCoordinate()).isAccessible()) {
                this.currentPosition = newPosition;
            }
            return true;
        } else {
            return false;
        }
    }

    private void updateExp() {
        if(this.attributes.getExp() > this.attributes.getExpForLvlUp()){
            this.attributes.updateHeroLevelAndStats();
        }
    }

    private void pickUpItem() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            for (Item item : this.floorItems) {
                if (this.getCurrentFloor().getTileAt(this.currentPosition.toCoordinate()) == item.getCurrentFloor().getTileAt(item.getPosition().toCoordinate()) && item.getIsOnFloor()) {
                    if (this.inventory.addItem(item)) {
                        item.setIsOnFloor(false);
                        item.setPickUp(true);
                        item.setPosition(this.currentPosition);
                    }

                }
            }
        }
    }

    private void openChest() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            if (this.getCurrentFloor().getTileAt(this.currentPosition.toCoordinate()) == chests.get(0).getCurrentFloor().getTileAt(chests.get(0).getPosition().toCoordinate()) && chests.get(0).getIsOnFloor()) {
                chests.get(0).setRemoveOrConsume(true);
                chests.get(1).setIsOnFloor(true);
                this.floorItems.add(chests.get(1));
            }

        }
    }

    private void stepOnTrap() {
        for (Item traps : traps) {
            if (this.getCurrentFloor().getTileAt(this.currentPosition.toCoordinate()) == traps.getCurrentFloor().getTileAt(traps.getPosition().toCoordinate())) {
                traps.setIsOnFloor(true);
                attributes.setCurrentHP(attributes.getCurrentHP() - 1);
            }
        }
    }

    private void talkToNpc() {
        for (FriendlyNPC npc : npcs) {
            if (this.getCurrentFloor().getTileAt(this.currentPosition.toCoordinate()) == npc.getCurrentFloor().getTileAt(npc.getPosition().toCoordinate())) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {

                    for (int i = 0; i < floorItems.size(); i++) {
                        if (!floorItems.get(i).getPickUp()) {
                            if (inventory.addItem(floorItems.get(i))) {
                                floorItems.get(i).setIsOnFloor(false);
                                floorItems.get(i).setPickUp(true);
                                floorItems.get(i).setPosition(this.currentPosition);
                                npc.setPosition(this.currentFloor.getRandomRoom().getRandomFloorTile().getCoordinate().toPoint());
                                log.info("Item gebracht");
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private void updateInventoryItemPosition() {
        if (this.inventory.isEmpty()) {
        } else {
            for (int i = 0; i < this.inventory.getInventory().length; i++) {
                if (this.inventory.getInventory()[i] != null) {
                    this.inventory.getInventory()[i].setPosition(this.currentPosition);
                }
            }
        }
    }

    private void itemViewDirection() {
        for (Item item : this.inventory.getInventory()) {
            if (item != null && item.isEquipped()) {
                item.setViewDirection(new PointBooleanTransmitter(this.viewDirection, this.currentPosition));
            }
        }
    }

    private void equipItem() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            this.inventory.equipItem(0);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            this.inventory.equipItem(1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            this.inventory.equipItem(2);
        }
    }

    private void dropItem() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            this.inventory.dropItem();
        }

    }

    private void consumeItem() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            this.inventory.consumeItem(this);
        }
    }

    private void showInventory() {
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            this.inventory.showInventory();
        }
    }

    private void printStats() {
        if (Gdx.input.isKeyPressed(Input.Keys.C)) {
            System.out.println(this.attributes.toString());
        }
    }

    

    public void setLevel(Level currentFloor) {
        this.currentFloor = currentFloor;
        this.currentPosition = this.currentFloor.getStartTile().getCoordinate().toPoint();
    }

    public void setFloorItems(ArrayList<Item> floorItems) {
        this.floorItems = floorItems;
    }

    public void setFloorTraps(ArrayList<Item> floorTraps) {
        this.traps = floorTraps;
    }

    public void setNpcs(ArrayList<FriendlyNPC> npcs) {
        this.npcs = npcs;
    }

    public void setFloorChests(ArrayList<Item> chests) {
        this.chests = chests;
    }

    public String getName(){
        return name;
    }

    @Override
    public Animation getActiveAnimation() {
        return this.activeAnimation;
    }

    @Override
    public Point getPosition() {
        return this.currentPosition;
    }


}
