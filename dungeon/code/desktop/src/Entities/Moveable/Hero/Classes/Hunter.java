package Entities.Moveable.Hero.Classes;

import Attribute.Moveables.Hero.HeroAttributes;
import Entities.Fight.Ranged.RangedFight;
import Entities.Moveable.Hero.Hero;
import Skills.Auras.HunterAura;
import Skills.ConvertStats.MovementSpeedBoost;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;

import java.util.ArrayList;

/**
 * Verwaltet die klassenspezifischen Attribute wie Animationen und Stats für den Hunter
 */

public class Hunter extends Hero {

    public Hunter(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.attributes = new HeroAttributes(75,125,75,75,70,70,1,0,10,0.2f);
        initAnimation(idleAnimation(), idleMirroredAnimation(), runAnimation(), runMirroredAnimation());
        this.name = "Hunter";
        this.aura = new HunterAura(painter, batch);
        this.convert = new MovementSpeedBoost(painter, batch);
    }

    /**
     * setzt die ranged attacke des knights
     */
    public void setRangedFight(RangedFight rangedFight){
        this.rangedFight = rangedFight;
    }

    /**
     * Initiates the normal idleAnimation
     */
    private ArrayList<String> idleAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/heroclasses/hunter/male/elf_m_idle_anim_f0.png");
        animation.add("character/heroclasses/hunter/male/elf_m_idle_anim_f1.png");
        animation.add("character/heroclasses/hunter/male/elf_m_idle_anim_f2.png");
        animation.add("character/heroclasses/hunter/male/elf_m_idle_anim_f3.png");
        return animation;
    }

    /**
     * Initiates the mirrored idleAnimation
     */
    private ArrayList<String> idleMirroredAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/heroclasses/hunter/male/mirror_elf_m_idle_anim_f0.png");
        animation.add("character/heroclasses/hunter/male/mirror_elf_m_idle_anim_f1.png");
        animation.add("character/heroclasses/hunter/male/mirror_elf_m_idle_anim_f2.png");
        animation.add("character/heroclasses/hunter/male/mirror_elf_m_idle_anim_f3.png");
        return animation;
    }

    /**
     * Initiates the normal runAnimation
     */
    private ArrayList<String> runAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/heroclasses/hunter/male/elf_m_run_anim_f0.png");
        animation.add("character/heroclasses/hunter/male/elf_m_run_anim_f1.png");
        animation.add("character/heroclasses/hunter/male/elf_m_run_anim_f2.png");
        animation.add("character/heroclasses/hunter/male/elf_m_run_anim_f3.png");
        return animation;
    }

    /**
     * Initiates the mirrored runAnimation
     */
    private ArrayList<String> runMirroredAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/heroclasses/hunter/male/mirror_elf_m_run_anim_f0.png");
        animation.add("character/heroclasses/hunter/male/mirror_elf_m_run_anim_f1.png");
        animation.add("character/heroclasses/hunter/male/mirror_elf_m_run_anim_f2.png");
        animation.add("character/heroclasses/hunter/male/mirror_elf_m_run_anim_f3.png");
        return animation;
    }

}
