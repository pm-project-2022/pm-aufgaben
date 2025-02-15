package Traps;

import Entities.Items.Item;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;

import java.util.ArrayList;

/**
 * Verwaltet die traps
 */

public class Traps extends Item {

    public Traps(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        setAnimation();
        isOnFloor = false;
        this.itemName = "Trap";
        this.removeOrConsume = false;
    }

    /**
     * sets trap animations
     */
    private void setAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("traps/floor_spikes_anim_f0.png");
        animation.add("traps/floor_spikes_anim_f1.png");
        animation.add("traps/floor_spikes_anim_f2.png");
        animation.add("traps/floor_spikes_anim_f3.png");
        initIdleAnimation(animation);
    }


    @Override
    public void update() {
        if (isOnFloor) {
            activeAnimation = idleAnimation;
        }
        else{
            activeAnimation = this.invisible;
        }
    }
    
}
