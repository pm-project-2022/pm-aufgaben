package Entities.Chest;

import Entities.Items.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;

import java.util.ArrayList;
import java.util.Random;

public class ChestFactory {

    private ChestFactory() {

    }

    /**
     * adds chests with item to a list
     * @param painter render
     * @param batch render
     * @return list with chest containing item
     */
    public static ArrayList<Item> chestFac(Painter painter, SpriteBatch batch) {
        ArrayList<Item> chests = new ArrayList<>();
        chests.add(new Chest(painter,batch));
        int ranNum = new Random().nextInt(6);
        switch (ranNum){
            case 0: chests.add(new HPPot(painter,batch));break;
            case 1: chests.add(new MPPot(painter,batch));break;
            case 2: chests.add(new ManaOrb(painter,batch));break;
            case 3: chests.add(new StrengthOrb(painter,batch));break;
            case 4: chests.add(new Sword(painter,batch));break;
            case 5: chests.add(new KnightSword(painter,batch));break;
        }
        return chests;
    }
}
