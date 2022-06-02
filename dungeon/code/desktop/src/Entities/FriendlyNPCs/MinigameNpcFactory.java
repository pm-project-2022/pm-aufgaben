package Entities.FriendlyNPCs;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;

import java.util.ArrayList;

/**
 * Gibt einen freundlichen Npcs zurück
 */

public class MinigameNpcFactory {

    private MinigameNpcFactory() {
    }

    /**
     * adds friendly npc to a list
     * @param painter render
     * @param batch render
     * @return list with npc
     */
    public static ArrayList<MinigameNPC> npcFac(Painter painter, SpriteBatch batch) {
        ArrayList<MinigameNPC> npcs = new ArrayList<>();
        npcs.add(new MinigameNPC(painter, batch));
        return npcs;
    }

}
