package Entities.FriendlyNPCs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import java.util.ArrayList;

public class FriendlyNpcFactory {

    private FriendlyNpcFactory() {
    }

    public static ArrayList<FriendlyNPC> npcFac(Painter painter, SpriteBatch batch) {
        ArrayList<FriendlyNPC> npcs = new ArrayList<>();
        npcs.add(new FriendlyNPC(painter, batch));
        return npcs;
    }

}
