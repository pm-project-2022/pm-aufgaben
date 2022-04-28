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

public abstract class Monster extends Animatable {
    // animations
    protected Animation idleAnimation, idleMirrorAnimation, runAnimation, runMirrorAnimation, activeAnimation;
    private boolean runDirection;

    // behaviour
    IMovementBehaviour movementBehaviour;
    IMovementBehaviour initialBehaviour;
    PointAndBoolean pAb;

    protected Level currentLevel;
    protected Point position;
    protected MyHero hero;

    protected StatusValues stats;
    protected int floorLevel;
    protected boolean entity;

    public Monster(Painter painter, SpriteBatch batch, MyHero hero, int levelScaling, IMovementBehaviour iMB) {
        super(painter, batch);
        this.hero = hero;
        this.floorLevel = levelScaling;
        this.entity = false;
        this.movementBehaviour = iMB;
        this.initialBehaviour = iMB;
    }

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
