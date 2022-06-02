package Entities.FriendlyNPCs;

import Entities.BasicEntity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

import java.util.ArrayList;

/**
 * Verwaltet die Animationen, Position und Level eines NPCs
 */

public class MinigameNPC extends BasicEntity {

    protected Animation idleAnimation;
    protected Level currentFloor;
    protected Point currentPosition;

    public MinigameNPC(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        setAnimation();
    }

    /**
     * sets animation
     * @return animations
     */
    private Animation setAnimation(){
        ArrayList<String> animation = new ArrayList<>();
        animation.add("character/friendlyNPC/ogre_idle_anim_f0.png");
        animation.add("character/friendlyNPC/ogre_idle_anim_f1.png");
        animation.add("character/friendlyNPC/ogre_idle_anim_f2.png");
        animation.add("character/friendlyNPC/ogre_idle_anim_f3.png");
        idleAnimation = new Animation(animation,8);
        return idleAnimation;
    }

    @Override
    public Animation getActiveAnimation() {
        return idleAnimation;
    }

    /**
     * sets position on random tile in dungeon
     * @param currentFloor
     */
    public void setLevel(Level currentFloor) {
        this.currentFloor = currentFloor;
        this.currentPosition = this.currentFloor.getRandomRoom().getRandomFloorTile().getCoordinate().toPoint();
    }

    @Override
    public Point getPosition() {
        return this.currentPosition;
    }

    /**
     * sets position
     * @param position position in dungeon
     */
    public void setPosition(Point position){
        this.currentPosition = position;
    }

    /**
     * gets the current floor
     * @return current floor
     */
    public Level getCurrentFloor(){
        return this.currentFloor;
    }
}
