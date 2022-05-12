package Entities.Moveable.Monster;

import Entities.Moveable.Hero.Hero;
import Entities.Moveable.Monster.MonsterMovement.IMovement;
import Entities.Moveable.Monster.MonsterMovement.ComplexBehaviour.AggressiveMovement;
import Entities.Moveable.Monster.MonsterMovement.SimpleMonsterMovement.Idle;
import Entities.Moveable.Moveable;
import Helper.PointBooleanTransmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import level.elements.room.Room;
import tools.Point;

public class Monster extends Moveable {
    private Hero hero;
    private boolean firstTime;
    private IMovement movementBehaviour;
    private IMovement initialMovementBehaviour;
    private IMovement aggressiveBehaviour;
    private PointBooleanTransmitter pointBooleanTransmitter;
    private Room escapeRoom;
    private int walkingCount;

    public Monster(Painter painter, SpriteBatch batch, Hero hero, IMovement movementBehaviour) {
        super(painter, batch);
        this.hero = hero;
        this.movementBehaviour = movementBehaviour;
        this.initialMovementBehaviour = movementBehaviour;
        this.aggressiveBehaviour = new AggressiveMovement();
        this.firstTime = true;
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
        if (!this.hero.getHeroDead()) {
            if (heroInRoom() && firstTime) {
                this.movementBehaviour = this.aggressiveBehaviour;
                walkingCount++;
                if (this.attributes.getCurrentHP() > this.attributes.getMaxHP() / 2) {
                    if (walkingCount % 5 == 0) {
                        this.pointBooleanTransmitter = this.movementBehaviour.getMonsterMovement(this);
                        this.currentPosition = pointBooleanTransmitter.getPoint();
                        animations();
                    }
                } else {
                    this.movementBehaviour = new Idle(this.pointBooleanTransmitter.getRunDirection());
                    this.pointBooleanTransmitter = this.movementBehaviour.getMonsterMovement(this);
                    this.currentPosition = pointBooleanTransmitter.getPoint();
                    animations();
                }
            } else {
                this.movementBehaviour = this.initialMovementBehaviour;
                this.pointBooleanTransmitter = this.movementBehaviour.getMonsterMovement(this);
                this.currentPosition = pointBooleanTransmitter.getPoint();
                animations();
            }

        }
    }

    private boolean heroInRoom() {
        return this.currentFloor.getRoomToPoint(this.currentPosition.toCoordinate()) == this.hero.getCurrentFloor()
                .getRoomToPoint(this.hero.getPosition().toCoordinate());
    }

    private void animations() {

        if (this.pointBooleanTransmitter.getCollision()) {
            this.movementBehaviour = new Idle(this.pointBooleanTransmitter.getRunDirection()) {

            };

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
        if (this.attributes.getCurrentHP() == 0) {
            this.hero.getAttributes().setExp(this.attributes.getExp() + this.hero.getAttributes().getExp());
            return true;
        } else {
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
