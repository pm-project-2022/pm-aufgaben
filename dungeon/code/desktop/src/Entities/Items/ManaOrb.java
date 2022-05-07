package Entities.Items;

import Attribute.Items.ManaItems;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
        animation.add("buffEntity/SpeedbuffOrb2.png");
        animation.add("buffEntity/SpeedbuffOrb3.png");
        animation.add("buffEntity/SpeedbuffOrb4.png");
        initIdleAnimation(animation);
    }
}
