package EntityController.Monster;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import EntityController.Hero.MyHero;
import EntityController.MovementBehaviour.IMovementBehaviour;
import EntityController.MovementBehaviour.SimpleMovementBehaviour.Idle;
import EntityController.Statuswerte.StatusValues;
import basiselements.Animatable;
import graphic.Animation;
import graphic.Painter;
import helper.PointAndBoolean;
import level.elements.Level;
import level.elements.room.Room;
import tools.Point;


/**
 * Absctract class for monster
 */

public abstract class Monster extends Animatable {
    //manages the animationd
    protected Animation idleAnimation, idleMirrorAnimation, runAnimation, runMirrorAnimation, activeAnimation;
    private boolean runDirection;

    //manages the monster behaviour
    protected IMovementBehaviour movementBehaviour;
    protected IMovementBehaviour initialBehaviour;
    protected PointAndBoolean pAb;
    protected Level currentLevel;
    protected Point position;
    protected MyHero hero;

    //manages the monster attributes
    protected StatusValues stats;
    protected int floorLevel;
    protected boolean entity;

    /**
     * Constructor for a monster
     * @param painter
     * @param batch
     * @param hero playable hero
     * @param levelScaling floor level
     * @param iMB
     */
    public Monster(Painter painter, SpriteBatch batch, MyHero hero, int floor, IMovementBehaviour iMB) {
        super(painter, batch);
        this.hero = hero;
        this.floorLevel = floor;
        this.entity = false;
        this.movementBehaviour = iMB;
        this.initialBehaviour = iMB;
    }

    /**
     * sets the game level and spawns monster to a random room and position in the room
     * @param level
     */
    public void setLevel(Level level) {
        this.currentLevel = level;
        Room room = level.getRandomRoom();
        Point point = room.getRandomFloorTile().getCoordinate().toPoint();
        this.position = point;
    }

    @Override
    public void update() {
        pAb = this.movementBehaviour.getMovementBehaviour(position, stats, currentLevel, this.hero, this);
        this.position = pAb.getPoint();
        this.runDirection = pAb.getRunDirection();
        animations();
    }

    @Override
    public boolean removable() {
        if(this.stats.getHealhtPoints() == 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Manages the animations depending on the states
     */
    private void animations() {
        if(this.pAb.getCollision()){
            this.movementBehaviour = new Idle(this.runDirection);

            if(this.runDirection){
                this.activeAnimation = idleAnimation;
            }else{
                this.activeAnimation = idleMirrorAnimation;
            }
            
            return;
        }

        if (this.runDirection) {
            this.activeAnimation = this.runAnimation;
        } else {
            this.activeAnimation = this.runMirrorAnimation;
        }
    }

    /**
     * getter for the attributes
     * @return stats
     */
    public StatusValues getStats() {
        return this.stats;
    }

    @Override
    public Animation getActiveAnimation() {
        return activeAnimation;
    }

    @Override
    public Point getPosition() {
        return this.position;
    }
}
