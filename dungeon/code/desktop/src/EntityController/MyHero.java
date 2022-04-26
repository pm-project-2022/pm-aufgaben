package EntityController;

import basiselements.Animatable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Logger.RunAnimationConsoleFormatter;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

/**
 * An extend Animatable that allows to create a hero for the game
 */

public class MyHero extends Animatable {
    private Animation idleAnimation, idleMirrorAnimation, runAnimation, runMirrorAnimation;
    private boolean movementState = false, viewDirection = true;
    private Point position;
    private Level currentLevel;
    private Logger logger;
    private ConsoleHandler consoleHandler;

    /**
     * Constructor for the hero. Sets the movementState to false and viewDirection
     * to true, which triggers the normal idleAnimation at start. Also initiates the animations.
     * 
     * @param painter necessary for the super class
     * @param batch   necessary for the super class
     */
    public MyHero(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        this.movementState = false;
        this.viewDirection = true;
        initLogger();
        idleAnimation();
        idleMirrorAnimation();
        runAnimation();
        runMirrorAnimation();
    }

    /**
     * Logger for the movement
     */
    private void initLogger() {
        this.logger = Logger.getLogger(MyHero.class.getName());
        this.logger.setUseParentHandlers(false);
        this.consoleHandler = new ConsoleHandler();
        this.consoleHandler.setFormatter(new RunAnimationConsoleFormatter());
        this.logger.addHandler(consoleHandler);
    }

    /**
     * Initiates the normal idleAnimation
     */
    private void idleAnimation() {
        List<String> animation = new ArrayList<String>();
        animation.add("character/knight/knight_m_idle_anim_f0.png");
        animation.add("character/knight/knight_m_idle_anim_f1.png");
        animation.add("character/knight/knight_m_idle_anim_f2.png");
        animation.add("character/knight/knight_m_idle_anim_f3.png");
        this.idleAnimation = new Animation(animation, 8);
    }

    /**
     * Initiates the mirrored idleAnimation
     */
    private void idleMirrorAnimation() {
        List<String> animation = new ArrayList<String>();
        animation.add("character/knight/knight_m_idle_anim_mirrored_f0.png");
        animation.add("character/knight/knight_m_idle_anim_mirrored_f1.png");
        animation.add("character/knight/knight_m_idle_anim_mirrored_f2.png");
        animation.add("character/knight/knight_m_idle_anim_mirrored_f3.png");
        this.idleMirrorAnimation = new Animation(animation, 8);
    }

    /**
     * Initiates the normal runAnimation
     */
    private void runAnimation() {
        List<String> animation = new ArrayList<String>();
        animation.add("character/knight/knight_m_run_anim_f0.png");
        animation.add("character/knight/knight_m_run_anim_f1.png");
        animation.add("character/knight/knight_m_run_anim_f2.png");
        animation.add("character/knight/knight_m_run_anim_f3.png");
        this.runAnimation = new Animation(animation, 8);
    }

    /**
     * Initiates the mirrored runAnimation
     */
    private void runMirrorAnimation() {
        List<String> animation = new ArrayList<String>();
        animation.add("character/knight/knight_m_run_anim_mirrored_f0.png");
        animation.add("character/knight/knight_m_run_anim_mirrored_f1.png");
        animation.add("character/knight/knight_m_run_anim_mirrored_f2.png");
        animation.add("character/knight/knight_m_run_anim_mirrored_f3.png");
        this.runMirrorAnimation = new Animation(animation, 8);
    }

    /**
     * setter for the level
     * @param level current level
     */
    public void setLevel(Level level) {
        currentLevel = level;
        position = level.getStartTile().getCoordinate().toPoint();
    }

    /**
     * updates the position, movementstate and viewdirection of the hero
     */
    @Override
    public void update() {
        Point newPosition = new Point(this.position);
        float movementSpeed = 0.1f;

        if (keyPressed()) {
            this.movementState = true;

            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                newPosition.y += movementSpeed;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                newPosition.y -= movementSpeed;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                newPosition.x += movementSpeed;
                this.viewDirection = true;
            }
            // Wenn die Taste A gedrückt ist, bewege dich nach links
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                newPosition.x -= movementSpeed;
                this.viewDirection = false;
            }

            if (currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()) {
                this.position = newPosition;
            }

        } else {
            this.movementState = false;
        }

    }

    /**
     * checks whether the button has been pressed or not.
     * @return true if a button was pressed, false if not
     */

    private boolean keyPressed() {
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S)
                || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * gets the current position of the hero
     */

    @Override
    public Point getPosition() {
        return position;
    }

    /**
     * returns the hero animation depending on the movement and viewDirection state
     */

    @Override
    public Animation getActiveAnimation() {
        if (this.movementState) {
            if (this.viewDirection) {
                // logger.info("guckt nach rechts und läuft hoch runter oder nach rechts");
                return runAnimation;
            } else {
                // logger.info("guckt nach links und läuft hoch runter oder nach links");
                return runMirrorAnimation;
            }
        } else {
            if (this.viewDirection) {
                // logger.info("guckt nach rechts und steht");
                return idleAnimation;
            } else {
                // logger.info("guckt nach links und steht");
                return idleMirrorAnimation;
            }
        }
    }

}
