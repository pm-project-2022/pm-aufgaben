package Traps;

import Entities.Items.Item;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;

import java.util.ArrayList;


public class TrapFactory {

    private TrapFactory(){

    }

    /**
     * adds traps to a list
     * @param painter render
     * @param batch render
     * @return list with traps
     */
    public static ArrayList<Item> trapFac(Painter painter, SpriteBatch batch) {
        ArrayList<Item> traps = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            traps.add(new Traps(painter,batch));
        }

        return traps;
    }

}
