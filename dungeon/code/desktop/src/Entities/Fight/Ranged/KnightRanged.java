package Entities.Fight.Ranged;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Entities.Moveable.Hero.Hero;
import graphic.Animation;
import graphic.Painter;

public class KnightRanged extends RangedFight {

    public KnightRanged(Painter painter, SpriteBatch batch, Hero hero) {
        super(painter, batch, hero);
        setIdleAnim();
        setIdleAnimeMirror();
    }

    /**
     * setter für die idle animation
     */
    private void setIdleAnim(){
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("weapons/spear_right.png");
        this.idleAnimation = new Animation(animation, 1);
    }

    /**
     * setter für die idle animation mirrored
     */
    private void setIdleAnimeMirror(){
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("weapons/spear_left.png");
        this.idleMirroredAnimation = new Animation(animation, 1);
    }
    
}
