package Items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;

import java.util.ArrayList;
import java.util.List;

public class Sword extends Item {

    public Sword(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        name = "Sword";
        setAnimations();
        this.activeAnimation = idleAnimation;
    }

    /**
     * sets and manages animations
     */
    private void setAnimations(){
        idleAnimation();
        hitAnimation();
    }

    private void idleAnimation(){
        List<String> animation = new ArrayList<String>();
        animation.add("weapons/weapon_regular_sword_01.png");
        this.idleAnimation = new Animation(animation,1);
    }

    public void hitAnimation(){
        List<String> animation = new ArrayList<String>();
        animation.add("weapons/weapon_regular_sword_01.png");
        animation.add("weapons/weapon_regular_sword_02.png");
        animation.add("weapons/weapon_regular_sword_03.png");
        animation.add("weapons/weapon_regular_sword_04.png");
        animation.add("weapons/weapon_regular_sword_05.png");
        animation.add("weapons/weapon_regular_sword_06.png");
        this.hitAnimation = new Animation(animation,3);
    }

}
