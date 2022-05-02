package Entities.Moveable.Monster.NormalMonster;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Attribute.Moveables.Monster.MonsterAttributes;
import Entities.Moveable.Hero.Hero;
import Entities.Moveable.Monster.Monster;
import Entities.Moveable.Monster.MonsterMovement.IMovement;
import graphic.Painter;

public class Chort extends Monster {

    public Chort(Painter painter, SpriteBatch batch, Hero hero, IMovement movementBehaviour, int floorLevel) {
        super(painter, batch, hero, movementBehaviour);
        this.attributes = new MonsterAttributes(10,10,10,10,10,floorLevel,10,0.15f);
        initAnimation(idleAnimation(), idleMirroredAnimation(), runAnimation(), runMirroredAnimation());
    }

    private ArrayList<String> idleAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/monster/normalmonster/chort/chort_idle_anim_f0.png");
        animation.add("character/monster/normalmonster/chort/chort_idle_anim_f1.png");
        animation.add("character/monster/normalmonster/chort/chort_idle_anim_f2.png");
        animation.add("character/monster/normalmonster/chort/chort_idle_anim_f3.png");
        return animation;
    }

    private ArrayList<String> idleMirroredAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/monster/normalmonster/chort/chort_idle_anim_mirrored_f0.png");
        animation.add("character/monster/normalmonster/chort/chort_idle_anim_mirrored_f1.png");
        animation.add("character/monster/normalmonster/chort/chort_idle_anim_mirrored_f2.png");
        animation.add("character/monster/normalmonster/chort/chort_idle_anim_mirrored_f3.png");
        return animation;
    }

    private ArrayList<String> runAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/monster/normalmonster/chort/chort_run_anim_f0.png");
        animation.add("character/monster/normalmonster/chort/chort_run_anim_f1.png");
        animation.add("character/monster/normalmonster/chort/chort_run_anim_f2.png");
        animation.add("character/monster/normalmonster/chort/chort_run_anim_f3.png");
        return animation;
    }

    private ArrayList<String> runMirroredAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("character/monster/normalmonster/chort/chort_run_anim_mirrored_f0.png");
        animation.add("character/monster/normalmonster/chort/chort_run_anim_mirrored_f1.png");
        animation.add("character/monster/normalmonster/chort/chort_run_anim_mirrored_f2.png");
        animation.add("character/monster/normalmonster/chort/chort_run_anim_mirrored_f3.png");
        return animation;
    }
}
