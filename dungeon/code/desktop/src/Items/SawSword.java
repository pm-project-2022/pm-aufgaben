package Items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;

import java.util.ArrayList;
import java.util.List;

public class SawSword extends Item{
    public SawSword(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        name = "Sawsword";
        setAnimations();
        this.activeAnimation = idleAnimation;
    }

    /**
     * sets and manages animations
     */
    private void setAnimations(){
        idleAnimation();
    }
    private void idleAnimation(){
        List<String> animation = new ArrayList<String>();
        animation.add("weapons/weapon_saw_sword.png");
        this.idleAnimation = new Animation(animation,1);
    }


}
