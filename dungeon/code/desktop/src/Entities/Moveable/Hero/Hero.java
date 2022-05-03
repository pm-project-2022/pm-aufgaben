package Entities.Moveable.Hero;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Entities.Items.Item;
import Entities.Moveable.Moveable;
import Helper.PointBooleanTransmitter;
import Inventory.Inventory;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

public class Hero extends Moveable {
    protected boolean viewDirection;
    protected ArrayList<Item> floorItems;
    protected ArrayList<Item> availableItems;
    protected Inventory inventory;

    public Hero(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.viewDirection = true;
        this.inventory = new Inventory();
        this.floorItems = new ArrayList<Item>();
    }

    @Override
    public void update() {
        animations(movementUpdate(movementKeyPressed(), this.currentPosition));
        pickUpItem();
        updateInventoryItemPosition();
        equipItem();
        itemViewDirection();
        dropItem();
        consumeItem();
        showInventory();
        printStats();
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

    private void pickUpItem() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            for (Item item : this.floorItems) {
                if (this.getCurrentFloor().getTileAt(this.currentPosition.toCoordinate()) == item.getCurrentFloor()
                        .getTileAt(item.getPosition().toCoordinate()) && item.getIsOnFloor()) {
                    if (this.inventory.addItem(item)) {
                        item.setIsOnFloor(false);
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

    private void itemViewDirection(){
        for (Item item : this.inventory.getInventory()) {
            if(item != null && item.isEquipped()){
                item.setViewDirection(new PointBooleanTransmitter(this.viewDirection, this.currentPosition));
            }
        }
    }

    private void equipItem(){
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

    private void consumeItem(){
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

    public void setLevel(Level currentFloor) {
        this.currentFloor = currentFloor;
        this.currentPosition = this.currentFloor.getStartTile().getCoordinate().toPoint();
    }

    public void setFloorItems(ArrayList<Item> floorItems) {
        this.floorItems = floorItems;
    }

    public void setAvailableItems(ArrayList<Item> availableItems) {
        this.availableItems = availableItems;
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
