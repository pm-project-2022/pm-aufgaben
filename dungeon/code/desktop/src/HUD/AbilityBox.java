package HUD;

import basiselements.HUDElement;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.HUDPainter;
import tools.Point;

public class AbilityBox extends HUDElement {
    private String texturePath;
    private Point position;

    /**
     * paints an ability box
     * @param painter render
     * @param batch render
     * @param position position on screen
     */
    public AbilityBox(HUDPainter painter, SpriteBatch batch, Point position) {
        super(painter, batch);
        texturePath = "hud/HUD 2D Pixel 2.png";
        this.position = position;
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
