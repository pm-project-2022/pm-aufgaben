package Items.DungeonLoot;

import Items.Item;
import basiselements.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import level.elements.Level;
import level.elements.room.Room;
import tools.Point;

public abstract class DungeonLoot extends Item {

    //manages positions
    protected Level currentLevel;
    protected Point position;
    protected String texturePath;

    public DungeonLoot(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        texturePath = "";
    }

    /**
     * sets level and location for loot in dungeon
     * @param level current level hero is on
     */
    public void setLevel(Level level) {
        this.currentLevel = level;
        Room room = level.getRandomRoom();
        Point point = room.getRandomFloorTile().getCoordinate().toPoint();
        this.position = point;
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public String getTexturePath() {
        return texturePath;
    }
}
