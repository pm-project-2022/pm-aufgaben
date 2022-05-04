package Entities.Moveable.Monster;

import Entities.Moveable.Hero.Hero;
import Entities.Moveable.Monster.MonsterMovement.IMovement;
import Entities.Moveable.Monster.MonsterMovement.SimpleMonsterMovement.Idle;
import Entities.Moveable.Moveable;
import Helper.PointBooleanTransmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

public class Monster extends Moveable {
    private Hero hero;

    private IMovement movementBehaviour;
    private PointBooleanTransmitter pointBooleanTransmitter;

    public Monster(Painter painter, SpriteBatch batch, Hero hero, IMovement movementBehaviour) {
        super(painter, batch);
        this.hero = hero;
        this.movementBehaviour = movementBehaviour;
    }

    public void setLevel(Level currentFloor) {
        this.currentFloor = currentFloor;
        this.currentPosition = this.currentFloor.getRandomRoom().getRandomFloorTile().getCoordinate().toPoint();
    }

    public Hero getHero() {
        return this.hero;
    }

    @Override
    public void update() {
        this.pointBooleanTransmitter = this.movementBehaviour.getMonsterMovement(this);
        this.currentPosition = pointBooleanTransmitter.getPoint();
        animations();
    }

    private void animations() {

        if (this.pointBooleanTransmitter.getCollision()) {

            //this.movementBehaviour = new Idle(this.pointBooleanTransmitter.getRunDirection());

            if (this.pointBooleanTransmitter.getRunDirection()) {
                this.activeAnimation = this.idleAnimation;
            } else {
                this.activeAnimation = this.idleMirroredAnimation;
            }

            return;
        }

        if (pointBooleanTransmitter.getRunDirection()) {
            this.activeAnimation = this.runAnimation;
        } else {
            this.activeAnimation = this.runMirroredAnimation;
        }
    }
    @Override
    public boolean removable() {
        if(this.attributes.getCurrentHP() == 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Animation getActiveAnimation() {
        return this.activeAnimation;
    }

    @Override
    public Point getPosition() {
        return this.currentPosition;
    }

}
