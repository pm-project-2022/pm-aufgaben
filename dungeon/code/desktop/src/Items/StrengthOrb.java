package Items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;

import java.util.ArrayList;
import java.util.List;

public class StrengthOrb extends Item{
    public StrengthOrb(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        name = "DamagebuffOrb";
        setAnimations();
    }
    /**
     * sets and manages animations
     */
    private void setAnimations(){
        idleAnimation();
    }
    private void idleAnimation(){
        List<String> animation = new ArrayList<String>();
        animation.add("buffEntity/StrengthbuffOrb.png");
        this.idleAnimation = new Animation(animation,1);
    }
}
