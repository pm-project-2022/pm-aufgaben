package Entities.Chest;

import Entities.Items.Item;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;

import java.util.ArrayList;

public class Chest extends Item {

    public Chest(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        setAnimation();
    }

    /**
     * sets animation
     */
    private void setAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("chest/chest_full_open_anim_f0.png");
        animation.add("chest/chest_full_open_anim_f1.png");
        animation.add("chest/chest_full_open_anim_f2.png");
        initIdleAnimation(animation);
    }


}
