package HUD;

import basiselements.HUDElement;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.HUDPainter;
import tools.Point;

public class HealthBar extends HUDElement {

    /**
     * hud element healthbar
     */
    protected String texturePath;
    protected Point position;

    public HealthBar(HUDPainter painter, SpriteBatch batch, Point position) {
        super(painter, batch);
        this.position = position;
        this.texturePath = "hud/healthbar.png";
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
