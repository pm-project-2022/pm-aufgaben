package Entities.Items;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import graphic.Painter;

public class Sword extends Item {

    public Sword(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.itemName = "Sword";
        this.itemDescription = "Standard sword for a knight.";
        setAnimation();
    }

    private void setAnimation() {
        ArrayList<String> animation = new ArrayList<String>();
        animation.add("weapons/weapon_regular_sword_01.png");
        initIdleAnimation(animation);
    }

}
