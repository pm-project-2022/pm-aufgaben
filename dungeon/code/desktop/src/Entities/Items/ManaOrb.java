package Entities.Items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Attribute.Items.ManaItems;
import graphic.Painter;


import java.util.ArrayList;

public class ManaOrb extends Item {
    public ManaOrb(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.itemName = "Mana Orb";
        this.itemDescription = "Increases mana permanently";
        this.attributes = new  ManaItems(1);
        setAnimation();
    }

    /**
     * sets and manages animations
     */
    private void setAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("buffEntity/SpeedbuffOrb.png");
        initIdleAnimation(animation);
    }
}
