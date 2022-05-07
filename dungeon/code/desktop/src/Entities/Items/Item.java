package Entities.Items;

import Entities.BasicEntity;
import Helper.PointBooleanTransmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

import java.util.ArrayList;

public class Item extends BasicEntity {
    protected Animation invisible;
    protected String itemName;
    protected String itemDescription;
    protected boolean isOnFloor;
    protected boolean isEquipped;
    protected boolean removeOrConsume;
    protected boolean pickUp;
    protected PointBooleanTransmitter viewDirection;
    protected float xOffset = 0.7f;

    public Item(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.isOnFloor = true;
        this.isEquipped = false;
        this.removeOrConsume = false;
        this.pickUp = false;
    }

    /**
     * initiates item animations
     * @param idleAnimation idleanimation of item
     */
    protected void initIdleAnimation(ArrayList<String> idleAnimation) {
        this.idleAnimation = new Animation(idleAnimation, 16);
        ArrayList<String> animations = new ArrayList<>();
        animations.add("InvisibleItem/invisibileItem.png");
        this.invisible = new Animation(animations, 1);

    }

    @Override
    public boolean removable() {
        return this.removeOrConsume;
    }

    @Override
    public void update() {
        if (this.isOnFloor) {
            this.activeAnimation = this.idleAnimation;
        } else {
            if (this.isEquipped) {
                if (this.viewDirection.getIdle()) {
                    float xPos = this.viewDirection.getPoint().x + xOffset;
                    float yPos = this.viewDirection.getPoint().y;
                    this.currentPosition = new Point(xPos, yPos);
                } else {
                    float xPos = this.viewDirection.getPoint().x - xOffset;
                    float yPos = this.viewDirection.getPoint().y;
                    this.currentPosition = new Point(xPos, yPos);
                }
                this.activeAnimation = this.idleAnimation;
            }else{
                this.activeAnimation = this.invisible;
            }

        }

    }

    /**
     * change direction of item
     * @param viewDirection true or false for left or right
     */
    public void setViewDirection(PointBooleanTransmitter viewDirection) {
        this.viewDirection = viewDirection;
    }

    /**
     * sets item equipped
     * @param equipped true or false
     */
    public void setEquipped(boolean equipped) {
        this.isEquipped = equipped;
    }

    /**
     * is equipped or not
     * @return true or false
     */
    public boolean isEquipped() {
        return this.isEquipped;
    }

    /**
     * getter for is equipped or not
     * @return true if equipped, false if not
     */
    public boolean getIsEquipped() {
        return this.isEquipped;
    }

    /**
     * setter for remove or consume items
     * @param removeOrConsume true or false
     */
    public void setRemoveOrConsume(boolean removeOrConsume) {
        this.removeOrConsume = removeOrConsume;
    }

    /**
     * gets the item name
     * @return item name
     */
    public String getItemName() {
        return this.itemName;
    }

    public String getItemDescription() {
        return this.itemDescription;
    }

    /**
     * getter for isOnFloor
     * @return true or false
     */
    public boolean getIsOnFloor() {
        return this.isOnFloor;
    }

    /**
     * sets if item is on floor or nor
     * @param isOnFloor return true if on floor, false if not
     */
    public void setIsOnFloor(boolean isOnFloor) {
        this.isOnFloor = isOnFloor;
    }

    /**
     * item spawns on random tile in dungeon
     * @param currentFloor
     */
    public void setLevel(Level currentFloor) {
        this.currentFloor = currentFloor;
        this.currentPosition = this.currentFloor.getRandomRoom().getRandomFloorTile().getCoordinate().toPoint();
    }

    /**
     * sets item pos at chest pos
     * @param item item in chest
     */
    public void setLevel(Item item){
        this.currentFloor = item.getCurrentFloor();
        this.currentPosition = item.getPosition();
    }

    public boolean getHeroViewDirection() {
        return true;
    }

    @Override
    public Animation getActiveAnimation() {
        return this.activeAnimation;
    }

    /**
     * sets the position
     * @param newPosition new Position
     */
    public void setPosition(Point newPosition) {
        this.currentPosition = newPosition;
    }

    @Override
    public Point getPosition() {
        return this.currentPosition;
    }

    /**
     * setter for pickup item
     * @param pickUp boolean true or false
     */
    public void setPickUp(boolean pickUp){
        this.pickUp = pickUp;
    }

    /**
     * getter for pickup item
     * @return returns true if picked up, false if not
     */
    public boolean getPickUp(){
        return pickUp;
    }

}
