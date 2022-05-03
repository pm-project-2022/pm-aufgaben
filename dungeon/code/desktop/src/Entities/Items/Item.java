package Entities.Items;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Entities.BasicEntity;
import Helper.PointBooleanTransmitter;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

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

    protected void initIdleAnimation(ArrayList<String> idleAnimation) {
        this.idleAnimation = new Animation(idleAnimation, 1);
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

    public void setViewDirection(PointBooleanTransmitter viewDirection) {
        this.viewDirection = viewDirection;
    }

    public void setEquipped(boolean equipped) {
        this.isEquipped = equipped;
    }

    public boolean isEquipped() {
        return this.isEquipped;
    }

    public boolean getIsEquipped() {
        return this.isEquipped;
    }

    public void setRemoveOrConsume(boolean removeOrConsume) {
        this.removeOrConsume = removeOrConsume;
    }

    public String getItemName() {
        return this.itemName;
    }

    public String getItemDescription() {
        return this.itemDescription;
    }

    public boolean getIsOnFloor() {
        return this.isOnFloor;
    }

    public void setIsOnFloor(boolean isOnFloor) {
        this.isOnFloor = isOnFloor;
    }

    public void setLevel(Level currentFloor) {
        this.currentFloor = currentFloor;
        this.currentPosition = this.currentFloor.getRandomRoom().getRandomFloorTile().getCoordinate().toPoint();
    }

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

    public void setPosition(Point newPosition) {
        this.currentPosition = newPosition;
    }

    @Override
    public Point getPosition() {
        return this.currentPosition;
    }

    public void setPickUp(boolean pickUp){
        this.pickUp = pickUp;
    }
    public boolean getPickUp(){
        return pickUp;
    }

}
