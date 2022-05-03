package HUD;

import basiselements.HUDElement;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.HUDPainter;
import tools.Point;

public class ManaBar extends HUDElement {

    protected String texturePath;
    protected Point position;

    public ManaBar(HUDPainter painter, SpriteBatch batch, Point position) {
        super(painter, batch);
        this.position = position;
        this.texturePath = "hud/manabar.png";
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
