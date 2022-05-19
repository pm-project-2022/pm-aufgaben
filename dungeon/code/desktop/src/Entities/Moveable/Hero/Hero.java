package Entities.Moveable.Hero;

import Entities.FriendlyNPCs.FriendlyNPC;
import Entities.Items.Item;
import Entities.Moveable.Moveable;
import Helper.PointBooleanTransmitter;
import Inventory.Inventory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import desktop.MyGame;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import Logger.ColumnFormatter;
import Skills.BasicSkill;

/**
 * Verwaltet die allgemeinen Attribute und Eigenschaften des Helds
 */

public class Hero extends Moveable {
    // blickrichtung des helds
    protected boolean viewDirection;

    // statsu ob der held lebt oder tot ist
    protected boolean heroDead;

    // listen mit entitys
    protected ArrayList<Item> floorItems;
    protected ArrayList<Item> chests;
    protected ArrayList<Item> traps;
    protected ArrayList<FriendlyNPC> npcs;

    // inventar
    protected Inventory inventory;

    // Skills
    protected BasicSkill aura;
    protected BasicSkill convert;
    private int convertCooldown = 0;
    // Logger
    protected Logger log;
    protected String name;

    // counter
    protected int walkingCount, trapCount = 0;

    public Hero(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.viewDirection = true;
        this.inventory = new Inventory();
        this.heroDead = false;
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
        if (!this.heroDead) {
            animations(movementUpdate(movementKeyPressed(), this.currentPosition));
            pickUpItem();
            updateInventoryItemPosition();
            equipItem();
            itemViewDirection();
            dropItem();
            consumeItem();
            showInventory();
            openChest();
            stepOnTrap();
            talkToNpc();
            updateDead();
            manageSkills();
            updateStats();
        }
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
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S)
                || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean movementUpdate(boolean keyPressed, Point currentPosition) {
        Point newPosition = new Point(currentPosition);

        if (keyPressed) {
            walkingCount++;
            if (walkingCount % 10 == 0) {
                MyGame.walking.play(0.05f);
            }
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

    /**
     * checkt ob der charakter ein level aufsteigen kann oder nicht
     * @return true, wenn ja, false wenn nein
     */
    private boolean updateExp() {
        if(this.attributes.getExp() >= this.attributes.getExpForLvlUp()){
            return true;
        }else{
            return false;
        }        
    }

    /**
     * updates die stats nach einem level up
     */
    private void updateStats(){
        if(updateExp()){
            this.attributes.updateHeroLevelAndStats();
            if(this.aura.getAuraAttributes().getActive()){
                this.aura.activateAura(this);
            }
        }
    }

    private void pickUpItem() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            for (Item item : this.floorItems) {
                if (this.getCurrentFloor().getTileAt(this.currentPosition.toCoordinate()) == item.getCurrentFloor()
                        .getTileAt(item.getPosition().toCoordinate()) && item.getIsOnFloor()) {
                    if (this.inventory.addItem(item)) {
                        MyGame.itemPickup.play(0.2f);
                        item.setIsOnFloor(false);
                        item.setPickUp(true);
                        item.setPosition(this.currentPosition);
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

    private void equipItem() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            this.inventory.equipItem(0);
            MyGame.equipItem.play(0.1f);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            this.inventory.equipItem(1);
            MyGame.equipItem.play(0.1f);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            this.inventory.equipItem(2);
            MyGame.equipItem.play(0.1f);
        }

    }

    private void itemViewDirection() {
        for (Item item : this.inventory.getInventory()) {
            if (item != null && item.isEquipped()) {
                item.setViewDirection(new PointBooleanTransmitter(this.viewDirection, this.currentPosition));
            }
        }
    }

    private void dropItem() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            this.inventory.dropItem();
            MyGame.dropItem.play(0.1f);
        }

    }

    private void consumeItem() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            this.inventory.consume(this);
            MyGame.useItem.play(0.1f);
        }
    }

    private void showInventory() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            this.inventory.showInventory();
        }
    }

    private void openChest() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            if (this.getCurrentFloor().getTileAt(this.currentPosition.toCoordinate()) == chests.get(0).getCurrentFloor()
                    .getTileAt(chests.get(0).getPosition().toCoordinate()) && chests.get(0).getIsOnFloor()) {
                MyGame.openChest.play(0.1f);
                chests.get(0).setRemoveOrConsume(true);
                chests.get(1).setIsOnFloor(true);
                this.floorItems.add(chests.get(1));
            }

        }
    }

    private void stepOnTrap() {
        for (Item traps : traps) {
            if (this.getCurrentFloor().getTileAt(this.currentPosition.toCoordinate()) == traps.getCurrentFloor()
                    .getTileAt(traps.getPosition().toCoordinate())) {
                traps.setIsOnFloor(true);
                trapCount++;
                if (trapCount % 5 == 0) {
                    MyGame.stepTraps.play(0.1f);
                }

                attributes.setCurrentHP(attributes.getCurrentHP() - 1);
            }
        }
    }

    private void talkToNpc() {
        for (FriendlyNPC npc : npcs) {
            if (this.getCurrentFloor().getTileAt(this.currentPosition.toCoordinate()) == npc.getCurrentFloor()
                    .getTileAt(npc.getPosition().toCoordinate())) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
                    MyGame.talkNpc.play(0.1f);
                    for (int i = 0; i < floorItems.size(); i++) {
                        if (!floorItems.get(i).getPickUp()) {
                            if (inventory.addItem(floorItems.get(i))) {
                                floorItems.get(i).setIsOnFloor(false);
                                floorItems.get(i).setPickUp(true);
                                floorItems.get(i).setPosition(this.currentPosition);
                                npc.setPosition(this.currentFloor.getRandomRoom().getRandomFloorTile().getCoordinate()
                                        .toPoint());
                                log.info("Item gebracht");
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private void updateDead() {
        if (this.attributes.getCurrentHP() <= 0) {
            this.heroDead = true;
            MyGame.death.play(0.4f);
        }
    }

    private void manageSkills() {
        auraSkill();
        convertSkill();
        convertRessourcesCD();
    }

    private void auraSkill() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            if (this.attributes.getLevel() < this.aura.getAuraAttributes().getAuraLevel()) {
                log.warning("Level ist zu niedrig um die Aura zu aktivieren");
            } else if (!this.aura.getAuraAttributes().getActive()) {
                this.aura.activateAura(this);
                log.info("Status: " + this.aura.getAuraAttributes().getActive());
                this.aura.getAuraAttributes().setActive(!this.aura.getAuraAttributes().getActive());
                System.out.println();
            } else {
                this.aura.deactiveAura(this);
                log.info("Status: " + this.aura.getAuraAttributes().getActive());
                this.aura.getAuraAttributes().setActive(!this.aura.getAuraAttributes().getActive());
            }
        }
    }

    private void convertSkill(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.X)){
            if(this.attributes.getLevel() < this.convert.getConvertAttributes().getSkillLevel()){
                log.warning("Level ist zu niedrig um den Skill zu benutzen");
            }else if(!this.convert.enoughRessources(this)){
                log.warning("Nicht genügend Leben oder Mana vorhanden.");
            }else if(this.convertCooldown > 0){
                log.warning("Skill ist auf Cooldown.");
            }else{
                this.convert.convert(this);
                log.info("Cooldown startet");
                this.convertCooldown = this.convert.getConvertAttributes().getCooldown();

            }
        }
    }

    private void convertRessourcesCD(){
       if(this.convertCooldown > 0){
           this.convertCooldown--;
       }
    }

    public void setPosition(Point newPosition) {
        this.currentPosition = newPosition;
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

    public String getName() {
        return name;
    }

    public void setHeroDead(boolean dead) {
        this.heroDead = dead;
    }

    public ArrayList<Item> getTraps() {
        return this.traps;
    }

    public boolean getHeroDead() {
        return this.heroDead;
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
