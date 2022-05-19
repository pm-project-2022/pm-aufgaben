package Entities.Items.MagicItems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Attribute.Items.HPItems;
import graphic.Painter;

/**
 * Verstärkt die HP des Helden
 */

public class HPEnhancer extends MagicItem{

    public HPEnhancer(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.itemName = "HpEnhancer";
        this.attributes = new HPItems(50);
    }

}
