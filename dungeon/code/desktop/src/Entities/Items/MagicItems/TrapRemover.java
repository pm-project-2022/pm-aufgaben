package Entities.Items.MagicItems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Attribute.Items.ManaItems;
import graphic.Painter;

public class TrapRemover extends MagicItem{
    

    public TrapRemover(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.itemName = "Trapremover";
        this.attributes = new ManaItems(50);
    }
    

}
