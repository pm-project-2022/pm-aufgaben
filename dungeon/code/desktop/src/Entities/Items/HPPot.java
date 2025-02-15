package Entities.Items;

import Attribute.Items.HPItems;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;

import java.util.ArrayList;

/**
 * HP Pot Klasse. Verwaltet die Stats der HP pots
 */

public class HPPot extends Item{

    public HPPot(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.itemName = "Health Potion";
        this.itemDescription = "Recharges HP";
        this.attributes = new HPItems(50);
        this.texture = "potions/flask_red.png";
        setAnimation();
    }

    private void setAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("potions/flask_red.png");
        initIdleAnimation(animation);
    }

}
