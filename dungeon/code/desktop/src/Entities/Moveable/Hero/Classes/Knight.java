package Entities.Moveable.Hero.Classes;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Attribute.Moveables.Hero.HeroAttributes;
import Entities.Moveable.Hero.Hero;
import graphic.Painter;

public class Knight extends Hero {
    
    public Knight(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.attributes = new HeroAttributes(100,100,50,100,50,50,1,0,10,0.2f);
        initAnimation(idleAnimation(), idleMirroredAnimation(), runAnimation(), runMirroredAnimation());
    }

    /**
     * Initiates the normal idleAnimation
     */
    private ArrayList<String> idleAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/knight/knight_m_idle_anim_f0.png");
        animation.add("character/knight/knight_m_idle_anim_f1.png");
        animation.add("character/knight/knight_m_idle_anim_f2.png");
        animation.add("character/knight/knight_m_idle_anim_f3.png");
        return animation;
    }

    /**
     * Initiates the mirrored idleAnimation
     */
    private ArrayList<String> idleMirroredAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/knight/knight_m_idle_anim_mirrored_f0.png");
        animation.add("character/knight/knight_m_idle_anim_mirrored_f1.png");
        animation.add("character/knight/knight_m_idle_anim_mirrored_f2.png");
        animation.add("character/knight/knight_m_idle_anim_mirrored_f3.png");
        return animation;
    }

    /**
     * Initiates the normal runAnimation
     */
    private ArrayList<String> runAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/knight/knight_m_run_anim_f0.png");
        animation.add("character/knight/knight_m_run_anim_f1.png");
        animation.add("character/knight/knight_m_run_anim_f2.png");
        animation.add("character/knight/knight_m_run_anim_f3.png");
        return animation;
    }

    /**
     * Initiates the mirrored runAnimation
     */
    private ArrayList<String> runMirroredAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/knight/knight_m_run_anim_mirrored_f0.png");
        animation.add("character/knight/knight_m_run_anim_mirrored_f1.png");
        animation.add("character/knight/knight_m_run_anim_mirrored_f2.png");
        animation.add("character/knight/knight_m_run_anim_mirrored_f3.png");
        return animation;
    }

}
