package Entities.Chest;

import Entities.Items.*;
import Entities.Items.MagicItems.HPEnhancer;
import Entities.Items.MagicItems.TrapRemover;

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
        int ranNum = new Random().nextInt(2);
        switch (ranNum){
            case 0: chests.add(new HPEnhancer(painter, batch));break;
            case 1: chests.add(new TrapRemover(painter, batch));break;
        }
        return chests;
    }
}
