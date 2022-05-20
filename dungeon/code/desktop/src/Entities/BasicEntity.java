package Entities;

import Attribute.BasicAttributes;
import basiselements.Animatable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

/**
 * Vorlageklasse jeder Entität
 */

public abstract class BasicEntity extends Animatable {
    //manages the attributes of an entity
    protected BasicAttributes attributes;

    protected Animation activeAnimation;
    protected Animation idleAnimation;
    protected Animation idleMirroredAnimation;

    //stores the current position and current floor of an entity
    protected Point currentPosition;
    protected Level currentFloor;

    public BasicEntity(Painter painter, SpriteBatch batch) {
        super(painter, batch);
    }

    public BasicAttributes getAttributes(){
        return this.attributes;
    }

    public Level getCurrentFloor(){
        return this.currentFloor;
    }

    @Override
    public Animation getActiveAnimation() {
        return null;
    }

    @Override
    public Point getPosition() {
        return null;
    }
}
