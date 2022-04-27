package EntityController.Monster;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import EntityController.Hero.MyHero;
import EntityController.MovementBehaviour.IMovementBehaviour;
import EntityController.Statuswerte.StatusValues;
import basiselements.Animatable;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import level.elements.room.Room;
import tools.Point;

public abstract class Monster extends Animatable{
    //animations
    protected Animation idleAnimation, idleMirrorAnimation, runAnimation, runMirrorAnimation,activeAnimation;
    //private boolean movementState, viewDirection;

    //behaviour
    IMovementBehaviour movementBehaviour;
    boolean runDirectionY;
    
    protected Level currentLevel;
    protected Point position;
    protected MyHero hero;


    protected StatusValues stats;
    protected int floorLevel;
    protected boolean entity;
    
    
    public Monster(Painter painter, SpriteBatch batch, MyHero hero, int levelScaling, IMovementBehaviour iMB) {
        super(painter, batch);
        //this.movementState = false;
        //this.viewDirection = true;
        this.hero = hero;
        this.floorLevel = levelScaling;
        this.entity = false;
        this.runDirectionY = true;
        this.movementBehaviour = iMB;
    }


    public void setLevel(Level level){
        this.currentLevel = level;
        Room room = level.getRandomRoom();
        Point point = room.getRandomFloorTile().getCoordinate().toPoint();
        this.position = point;
    }

    @Override
    public void update() {
        this.position = this.movementBehaviour.getMovementBehaviour(position, stats, currentLevel);
        this.activeAnimation = this.runAnimation;
        /*Point newPosition = new Point(this.position);
        if(this.runDirectionY){
            newPosition.y += this.stats.getMovementspeed();
            if(currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()){
                this.position = newPosition;
            }else{
                this.runDirectionY = false;
            }
        }else{
            newPosition.y -= this.stats.getMovementspeed();
            if(currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()){
                this.position = newPosition;
            }else{
                this.runDirectionY = true;
            }
        }*/
    }

    public StatusValues getStats(){
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
