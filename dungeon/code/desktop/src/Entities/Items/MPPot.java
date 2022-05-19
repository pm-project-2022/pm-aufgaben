package Entities.Items;

import Attribute.Items.ManaItems;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;

import java.util.ArrayList;

/**
 * MP Pot Klasse. Verwaltet die Stats der MP pots
 */

public class MPPot extends Item{

    public MPPot(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.itemName = "Mana Potion";
        this.itemDescription = "Recharges MP";
        this.attributes = new ManaItems(50);
        setAnimation();
    }

    private void setAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("potions/flask_blue.png");
        initIdleAnimation(animation);
    }

}
