package EntityController.Monster.SmallMonster;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import EntityController.Hero.MyHero;
import EntityController.Monster.Monster;
import EntityController.MovementBehaviour.IMovementBehaviour;
import EntityController.Statuswerte.StatusValues;
import graphic.Animation;
import graphic.Painter;

public class Imp extends Monster {

    public Imp(Painter painter, SpriteBatch batch, MyHero hero, int floorLevel, IMovementBehaviour iMB) {
        super(painter, batch, hero, floorLevel,iMB);
        setAnimations();
        this.stats = new StatusValues(this.entity,10,10,10,10,10,10,floorLevel,10, 0.1f);
        this.activeAnimation = idleAnimation;
    }

    //sets monsteranimations
    private void setAnimations() {
        initIdleAnimation();
        initMirrorIdleAnimation();
        initRunAnimation();
        initMirrorRunAnimation();
    }

    private void initIdleAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/monster/smallmonster/imp/imp_idle_anim_f0.png");
        animation.add("character/monster/smallmonster/imp/imp_idle_anim_f1.png");
        animation.add("character/monster/smallmonster/imp/imp_idle_anim_f2.png");
        animation.add("character/monster/smallmonster/imp/imp_idle_anim_f3.png");
        this.idleAnimation = new Animation(animation, 8);
    }

    private void initMirrorIdleAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/monster/smallmonster/imp/imp_idle_anim_mirrored_f0.png");
        animation.add("character/monster/smallmonster/imp/imp_idle_anim_mirrored_f1.png");
        animation.add("character/monster/smallmonster/imp/imp_idle_anim_mirrored_f2.png");
        animation.add("character/monster/smallmonster/imp/imp_idle_anim_mirrored_f3.png");
        this.idleMirrorAnimation = new Animation(animation, 8);
    }

    private void initRunAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/monster/smallmonster/imp/imp_run_anim_f0.png");
        animation.add("character/monster/smallmonster/imp/imp_run_anim_f1.png");
        animation.add("character/monster/smallmonster/imp/imp_run_anim_f2.png");
        animation.add("character/monster/smallmonster/imp/imp_run_anim_f3.png");
        this.runAnimation = new Animation(animation, 8);
    }

    private void initMirrorRunAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/monster/smallmonster/imp/imp_run_anim_mirrored_f0.png");
        animation.add("character/monster/smallmonster/imp/imp_run_anim_mirrored_f1.png");
        animation.add("character/monster/smallmonster/imp/imp_run_anim_mirrored_f2.png");
        animation.add("character/monster/smallmonster/imp/imp_run_anim_mirrored_f3.png");
        this.runMirrorAnimation = new Animation(animation,8);
    }   
    
}
