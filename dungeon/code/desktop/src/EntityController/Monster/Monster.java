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
    //manages the animation
    protected Animation idleAnimation, idleMirrorAnimation, runAnimation, runMirrorAnimation, activeAnimation;
    

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

    /**
     * getter for current level
     * @return current level
     */
    public Level getCurrentLevel(){
        return this.currentLevel;
    }

    /**
     * getter for hero
     * @return current hero
     */
    public MyHero getHero(){
        return this.hero;
    }

    @Override
    public boolean removable() {
        if(this.stats.getHealhtPoints() == 0){
            return true;
        }else{
            return false;
        }
    }
    
    
    @Override
    public void update() {
        pAb = this.movementBehaviour.getMovementBehaviour(this);
        this.position = pAb.getPoint();
        animations();
    }

    

    /**
     * Manages the animations depending on the states
     */
    private void animations() {
        if(this.pAb.getCollision()){
            this.movementBehaviour = new Idle(this.pAb.getRunDirection());

            if(this.pAb.getRunDirection()){
                this.activeAnimation = idleAnimation;
            }else{
                this.activeAnimation = idleMirrorAnimation;
            }
            
            return;
        }

        if (pAb.getRunDirection()) {
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
