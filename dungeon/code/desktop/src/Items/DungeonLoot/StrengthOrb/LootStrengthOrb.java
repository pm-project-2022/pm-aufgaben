package Items.DungeonLoot.StrengthOrb;

import Items.DungeonLoot.DungeonLoot;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;

public class LootStrengthOrb extends DungeonLoot {
    public LootStrengthOrb(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        /**
         * sets texture and name
         */
        texturePath = "buffEntity/StrengthbuffOrb.png";
        name = "DamagebuffOrb";
    }
}
