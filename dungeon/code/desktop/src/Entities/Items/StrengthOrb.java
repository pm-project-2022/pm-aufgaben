package Entities.Items;

import Attribute.Items.AttackPowerItems;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;

import java.util.ArrayList;

/**
 * Item. dass das maximale attack power permanent erhöht
 */

public class StrengthOrb extends Item{

    public StrengthOrb(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.itemName = "Strength Orb";
        this.itemDescription = "Increases attackpower permanently";
        this.attributes = new AttackPowerItems(1);
        this.texture = "buffEntity/StrengthbuffOrb.png";
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
