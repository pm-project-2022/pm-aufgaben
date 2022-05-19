package Entities.Moveable;

import Entities.BasicEntity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;

import java.util.ArrayList;

/**
 * Vorlageklasse jeder Entität die sich bewegen kann
 */

public class Moveable extends BasicEntity{
    //stores the animations

    protected Animation idleMirroredAnimation;
    protected Animation runAnimation;
    protected Animation runMirroredAnimation;


    public Moveable(Painter painter, SpriteBatch batch) {
        super(painter, batch);
    }

    protected void initAnimation(ArrayList<String> idleAnimation, ArrayList<String> idleMirroredAnimation, ArrayList<String> runAnimation, ArrayList<String> runMirroredAnimation) {
        initIdleAnimation(idleAnimation);
        initMirrorIdleAnimation(idleMirroredAnimation);
        initRunAnimation(runAnimation);
        initMirrorRunAnimation(runMirroredAnimation);
        this.activeAnimation = this.idleAnimation;
    }

    private void initIdleAnimation(ArrayList<String> idleAnimation){
        this.idleAnimation = new Animation(idleAnimation, 8);
    }

    private void initMirrorIdleAnimation(ArrayList<String> idleMirroredAnimation){
        this.idleMirroredAnimation = new Animation(idleMirroredAnimation, 8);
    }

    private void initRunAnimation(ArrayList<String> runAnimation){
        this.runAnimation = new Animation(runAnimation, 8);
    }

    private void initMirrorRunAnimation(ArrayList<String> runMirroredAnimation){
        this.runMirroredAnimation = new Animation(runMirroredAnimation, 8);
    }
}
