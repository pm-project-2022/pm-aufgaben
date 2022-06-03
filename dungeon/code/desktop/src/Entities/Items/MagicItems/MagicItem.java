package Entities.Items.MagicItems;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Entities.Items.Item;
import graphic.Painter;

/**
 * Grundklasse der MagicItems. Behinhaltet den Spritze für die Items
 */

public class MagicItem extends Item {

    public MagicItem(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        setAnimation();
    }

    private void setAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("magic/skull.png");
        initIdleAnimation(animation);
    }
    
}
