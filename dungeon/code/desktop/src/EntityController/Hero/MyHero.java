package EntityController.Hero;

import basiselements.Animatable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import EntityController.Statuswerte.StatusValues;
import Logger.RunAnimationConsoleFormatter;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

/**
 * An extend Animatable that allows to create a hero for the game
 */

public class MyHero extends Animatable {
    private Animation idleAnimation, idleMirrorAnimation, runAnimation, runMirrorAnimation,activeAnimation;
    private boolean movementState, viewDirection;
    private Point position;
    private Level currentLevel;
    private Logger logger;
    private ConsoleHandler consoleHandler;
    private StatusValues stats;

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
        initAnimation();
        this.stats = new StatusValues(true, 100000,100000,100000,100000,100000,100000,100000,0, 0.2f);
        this.activeAnimation = idleAnimation;
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
     * Inits animations
     */

     private void initAnimation() {
        idleAnimation();
        idleMirrorAnimation();
        runAnimation();
        runMirrorAnimation();
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

    public Level getLevel() {
        return this.currentLevel;
    }

    /**
     * updates the position, movementstate and viewdirection of the hero
     */
    
    @Override
    public void update() {
        Point newPosition = new Point(this.position);
        
        if (keyPressed()) {
            this.movementState = true;

            // Wenn die Taste W gedrückt ist, bewege dich nach oben
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                newPosition.y += this.stats.getMovementspeed();
            }
            // Wenn die Taste S gedrückt ist, bewege dich nach unten
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                newPosition.y -= this.stats.getMovementspeed();
            }
            // Wenn die Taste D gedrückt ist, bewege dich nach rechts
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                this.viewDirection = true;
                newPosition.x += this.stats.getMovementspeed();
            }
            // Wenn die Taste A gedrückt ist, bewege dich nach links
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                this.viewDirection = false;
                newPosition.x -= this.stats.getMovementspeed();
            }
            if (currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()) {
                this.position = newPosition;
            }
        }
        animations();
    }
    

    private void animations(){
        if(keyPressed()){
            if(viewDirection){
                this.activeAnimation = runAnimation;
            }
            else{
                this.activeAnimation = runMirrorAnimation;
            }
        } else {
            if (this.viewDirection) {
                this.activeAnimation = this.idleAnimation;
            } else {
                this.activeAnimation = this.idleMirrorAnimation;
            }
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
        return activeAnimation;
    }

}
