package Items.DungeonLoot.Speedorb;

import Items.DungeonLoot.DungeonLoot;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;

public class LootSpeedOrb extends DungeonLoot {
    /**
     * sets texture and name
     */
    public LootSpeedOrb(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        texturePath = "buffEntity/SpeedbuffOrb.png";
        name = "SpeedbuffOrb";
    }
}
