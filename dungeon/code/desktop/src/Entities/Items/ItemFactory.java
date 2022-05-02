package Entities.Items;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import graphic.Painter;


public class ItemFactory {
    private ItemFactory() {
    };

    public static ArrayList<Item> itemFac(Painter painter, SpriteBatch batch) {
        ArrayList<Item> items = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            //int ranNum = random.nextInt(2 + 1) + 1;
            int ranNum = 1;
            switch (i) {
                case 0:
                    if (ranNum == 1) {
                        items.add(new HPPot(painter, batch));
                    }
                    break;
                case 1:
                    if (ranNum == 1) {
                        items.add(new MPPot(painter, batch));
                    }

                    break;
                case 2:
                    if (ranNum == 1) {
                        items.add(new ManaOrb(painter, batch));
                    }

                    break;
                case 3:
                    if (ranNum == 1) {
                        items.add(new StrengthOrb(painter, batch));
                    }
                    break;
                default:
                    break;    
            }
        }

        return items;
    }

    public static ArrayList<Item> itemListFac(Painter painter, SpriteBatch batch){
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int ranNum = 1;
            switch (i) {
                case 0:
                    if (ranNum == 1) {
                        items.add(new HPPot(painter, batch));
                    }
                    break;
                case 1:
                    if (ranNum == 1) {
                        items.add(new MPPot(painter, batch));
                    }

                    break;
                case 2:
                    if (ranNum == 1) {
                        items.add(new ManaOrb(painter, batch));
                    }

                    break;
                case 3:
                    if (ranNum == 1) {
                        items.add(new StrengthOrb(painter, batch));
                    }
                    break;
                default:
                    break;    
            }
        }

        return items;

    }
}
