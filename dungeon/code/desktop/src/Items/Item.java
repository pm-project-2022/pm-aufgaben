package Items;

import basiselements.Animatable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import desktop.MyGame;
import graphic.Animation;
import graphic.Painter;
import tools.Point;

/**
 * superclass for all character items
 */
public abstract class Item extends Animatable {
    //manages positions, visibility and animations
    public Point position;
    public float xOffset = 0.7f;
    public boolean isVisible = true;
    public String name = "";
    public SpriteBatch batch;
    protected Animation activeAnimation, hitAnimation, idleAnimation;

    /**
     * superclass for all items
     * @param painter necessary for the super class
     * @param batch necessary for the super class
     */
    public Item(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.batch = batch;

    }

    /**
     * renders item at character
     * @return returns the position
     */
    public Point getPosition() {
        if (MyGame.hero.lookingLeft()) {
            float xPos = MyGame.hero.getPosition().x + xOffset;
            float yPos = MyGame.hero.getPosition().y;
            position = new Point(xPos, yPos);
        } else {
            float xPos = MyGame.hero.getPosition().x - xOffset;
            float yPos = MyGame.hero.getPosition().y;
            position = new Point(xPos, yPos);
        }
        return position;
    }

    /**
     * updates animations
     */
    public void update(){
        animations();
    }

    /**
     * sets animations
     */
    public void animations() {
        if (Gdx.input.isKeyPressed(Input.Keys.L)&&getClass().getSimpleName().equals("Sword")) {
            this.activeAnimation = hitAnimation;
        } else {
            this.activeAnimation = idleAnimation;
        }
    }

    /**
     * getter for item name
     * @return returns item name
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean removable() {
        return !isVisible;
    }

    /**
     * getter for active animation
     * @return returns active animation
     */
    public Animation getActiveAnimation() {
        return activeAnimation;
    }
}
