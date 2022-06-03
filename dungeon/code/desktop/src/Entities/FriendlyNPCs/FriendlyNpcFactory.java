package Entities.FriendlyNPCs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;

import java.util.ArrayList;

/**
 * Gibt einen freundlichen Npcs zurück
 */

public class FriendlyNpcFactory {

    private FriendlyNpcFactory() {
    }

    /**
     * adds friendly npc to a list
     * @param painter render
     * @param batch render
     * @return list with npc
     */
    public static ArrayList<FriendlyNPC> npcFac(Painter painter, SpriteBatch batch) {
        ArrayList<FriendlyNPC> npcs = new ArrayList<>();
        npcs.add(new FriendlyNPC(painter, batch));
        return npcs;
    }

}
