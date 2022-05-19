package Entities.Moveable.Hero.Classes;

import Attribute.Moveables.Hero.HeroAttributes;
import Entities.Moveable.Hero.Hero;
import Skills.Auras.WizzardAura;
import Skills.ConvertStats.ConvertHptoMana;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;

import java.util.ArrayList;

public class Wizard extends Hero {

    public Wizard(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.attributes = new HeroAttributes(50,200,100,50,50,50,1,0,10,0.2f);
        initAnimation(idleAnimation(), idleMirroredAnimation(), runAnimation(), runMirroredAnimation());
        this.name = "Wizard";
        this.aura = new WizzardAura(painter, batch);
        this.convertRessoure = new ConvertHptoMana(painter, batch);
    }

    /**
     * Initiates the normal idleAnimation
     */
    private ArrayList<String> idleAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/heroclasses/wizard/Male/wizzard_m_idle_anim_f0.png");
        animation.add("character/heroclasses/wizard/Male/wizzard_m_idle_anim_f1.png");
        animation.add("character/heroclasses/wizard/Male/wizzard_m_idle_anim_f2.png");
        animation.add("character/heroclasses/wizard/Male/wizzard_m_idle_anim_f3.png");
        return animation;
    }

    /**
     * Initiates the mirrored idleAnimation
     */
    private ArrayList<String> idleMirroredAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/heroclasses/wizard/Male/mirror_wizzard_m_idle_anim_f0.png");
        animation.add("character/heroclasses/wizard/Male/mirror_wizzard_m_idle_anim_f1.png");
        animation.add("character/heroclasses/wizard/Male/mirror_wizzard_m_idle_anim_f2.png");
        animation.add("character/heroclasses/wizard/Male/mirror_wizzard_m_idle_anim_f3.png");
        return animation;
    }

    /**
     * Initiates the normal runAnimation
     */
    private ArrayList<String> runAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/heroclasses/wizard/Male/wizzard_m_run_anim_f0.png");
        animation.add("character/heroclasses/wizard/Male/wizzard_m_run_anim_f1.png");
        animation.add("character/heroclasses/wizard/Male/wizzard_m_run_anim_f2.png");
        animation.add("character/heroclasses/wizard/Male/wizzard_m_run_anim_f3.png");
        return animation;
    }

    /**
     * Initiates the mirrored runAnimation
     */
    private ArrayList<String> runMirroredAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/heroclasses/wizard/Male/mirror_wizzard_m_run_anim_f0.png");
        animation.add("character/heroclasses/wizard/Male/mirror_wizzard_m_run_anim_f1.png");
        animation.add("character/heroclasses/wizard/Male/mirror_wizzard_m_run_anim_f2.png");
        animation.add("character/heroclasses/wizard/Male/mirror_wizzard_m_run_anim_f3.png");
        return animation;
    }

}
