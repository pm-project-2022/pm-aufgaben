package HUD;

import basiselements.HUDElement;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.HUDPainter;
import tools.Point;

public class LvlBar extends HUDElement {

    /**
     * Hud element expbar
     */
    protected String texturePath;
    protected Point position;

    public LvlBar(HUDPainter painter, SpriteBatch batch, Point position) {
        super(painter, batch);
        this.position = position;
        this.texturePath = "hud/lvlbar.png";
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public String getTexturePath() {
        return texturePath;
    }
}
