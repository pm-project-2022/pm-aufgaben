package Entities.Items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Attribute.Items.AttackPowerItems;
import graphic.Painter;


import java.util.ArrayList;


public class StrengthOrb extends Item{
    
    public StrengthOrb(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.itemName = "Strength Orb";
        this.itemDescription = "Increases attackpower permanently";
        this.attributes = new AttackPowerItems(1);
        setAnimation();
    }

    /**
     * sets and manages animations
     */
    private void setAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("buffEntity/StrengthbuffOrb.png");
        initIdleAnimation(animation);
    }
}
