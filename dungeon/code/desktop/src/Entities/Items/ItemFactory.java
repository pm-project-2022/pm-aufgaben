package Entities.Items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;

import java.util.ArrayList;
import java.util.Random;

/**
 * Erzeugt eine zuälliger Anzahl von zufälligen Items und gibt diese als Arrayliste zurück
 */

public class ItemFactory {
    private ItemFactory() {
    }

    /**
     * adds items to a list
     * @param painter render
     * @param batch render
     * @return list with items
     */
    public static ArrayList<Item> itemFac(Painter painter, SpriteBatch batch) {
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            int ranNum = new Random().nextInt(5);
            switch (ranNum) {
                case 0: items.add(new HPPot(painter, batch));break;
                case 1: items.add(new MPPot(painter, batch));break;
                case 2: items.add(new ManaOrb(painter, batch));break;
                case 3: items.add(new StrengthOrb(painter, batch));break;
                default: break;
            }
        }
        return items;
    }

}
