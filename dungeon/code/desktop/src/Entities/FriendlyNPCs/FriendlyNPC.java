package Entities.FriendlyNPCs;

import Entities.BasicEntity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

import java.util.ArrayList;

public class FriendlyNPC extends BasicEntity {

    protected Animation idleAnimation;
    protected Level currentFloor;
    protected Point currentPosition;

    public FriendlyNPC(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        setAnimation();
    }

    private Animation setAnimation(){
        ArrayList<String> animation = new ArrayList();
        animation.add("character/friendlyNPC/elf_f_idle_anim_f0.png");
        animation.add("character/friendlyNPC/elf_f_idle_anim_f1.png");
        animation.add("character/friendlyNPC/elf_f_idle_anim_f2.png");
        animation.add("character/friendlyNPC/elf_f_idle_anim_f3.png");
        idleAnimation = new Animation(animation,8);
        return idleAnimation;
    }

    @Override
    public Animation getActiveAnimation() {
        return idleAnimation;
    }

    public void setLevel(Level currentFloor) {
        this.currentFloor = currentFloor;
        this.currentPosition = this.currentFloor.getRandomRoom().getRandomFloorTile().getCoordinate().toPoint();
    }

    @Override
    public Point getPosition() {
        return this.currentPosition;
    }

    public void setPosition(Point position){
        this.currentPosition = position;
    }

    public Level getCurrentFloor(){
        return this.currentFloor;
    }
}
