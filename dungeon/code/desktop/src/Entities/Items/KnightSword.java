package Entities.Items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;

import java.util.ArrayList;

/**
 * Waffe des Ritters
 */

public class KnightSword extends Item {

    public KnightSword(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.itemName = "Knightsword";
        this.itemDescription = "Sword for a knight.";
        setAnimation();
    }

    private void setAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("weapons/weapon_knight_sword.png");
        initIdleAnimation(animation);
    }

}
