package Entities.Moveable.Monster;

import Entities.Moveable.Hero.Hero;
import Entities.Moveable.Monster.MonsterMovement.IMovement;
import Entities.Moveable.Monster.MonsterMovement.ComplexBehaviour.AggressiveMovement;
import Entities.Moveable.Monster.MonsterMovement.ComplexBehaviour.Escape;
import Entities.Moveable.Monster.MonsterMovement.SimpleMonsterMovement.Idle;
import Entities.Moveable.Moveable;
import Helper.PointBooleanTransmitter;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import level.elements.room.Room;
import tools.Point;

public class Monster extends Moveable {
    private Hero hero;
    private ArrayList<Monster> monster;
    private IMovement movementBehaviour;
    private IMovement initialMovementBehaviour;
    private IMovement aggressiveBehaviour;
    private IMovement escapeBehavior;
    private PointBooleanTransmitter pointBooleanTransmitter;
    private Point escapeRoom;
    private int walkingCount;
    private boolean escape;
    private boolean callForHelp;

    public Monster(Painter painter, SpriteBatch batch, Hero hero, IMovement movementBehaviour) {
        super(painter, batch);
        this.hero = hero;
        this.movementBehaviour = movementBehaviour;
        this.initialMovementBehaviour = movementBehaviour;
        this.aggressiveBehaviour = new AggressiveMovement();
        this.escapeBehavior = new Escape();
        this.escape = true;
        this.callForHelp = false;
    }

    public void setLevel(Level currentFloor) {
        this.currentFloor = currentFloor;
        this.currentPosition = this.currentFloor.getRandomRoom().getRandomFloorTile().getCoordinate().toPoint();
    }

    public Hero getHero() {
        return this.hero;
    }

    public void setMonster(ArrayList<Monster> monster) {
        this.monster = monster;
    }

    @Override
    public void update() {
        if (!this.hero.getHeroDead()) {

            if (heroInRoom() && this.attributes.getCurrentHP() > this.attributes.getMaxHP() / 2) {
                this.movementBehaviour = this.aggressiveBehaviour;
                walkingCount++;
                if (walkingCount % 5 == 0) {
                    this.pointBooleanTransmitter = this.movementBehaviour.getMonsterMovement(this);
                    this.currentPosition = pointBooleanTransmitter.getPoint();
                    animations();
                }
            } else if (this.attributes.getCurrentHP() <= this.attributes.getMaxHP() / 2
                    && this.attributes.getCurrentHP() > this.attributes.getMaxHP() / 4) {
                this.movementBehaviour = this.escapeBehavior;
                if (this.escape) {
                    this.escapeRoom = currentFloor.getRandomRoom().getRandomFloorTile().getCoordinate().toPoint();
                    this.escape = false;
                }
                walkingCount++;
                if (walkingCount % 7 == 0) {
                    this.pointBooleanTransmitter = this.movementBehaviour.getMonsterMovement(this);
                    this.currentPosition = pointBooleanTransmitter.getPoint();
                    if (this.pointBooleanTransmitter.getPoint() == this.escapeRoom) {
                        this.escape = true;
                    }
                    animations();
                }
            } else if (this.attributes.getCurrentHP() <= this.attributes.getMaxHP() / 4) {
                if (!callForHelp) {
                    for (Monster monster : this.monster) {
                        if (monster.getAttributes().getCurrentHP() > monster.getAttributes().getMaxHP() / 4) {
                            Room room = this.currentFloor.getRoomToPoint(this.currentPosition.toCoordinate());
                            monster.setCurrentPosition(room.getRandomFloorTile().getCoordinate().toPoint());
                            callForHelp = true;
                            return;
                        }
                    }
                }
                this.movementBehaviour = new Idle(this.pointBooleanTransmitter.getRunDirection());
                this.pointBooleanTransmitter = this.movementBehaviour.getMonsterMovement(this);
                this.currentPosition = pointBooleanTransmitter.getPoint();
                animations();
            } else {
                this.movementBehaviour = this.initialMovementBehaviour;
                this.pointBooleanTransmitter = this.movementBehaviour.getMonsterMovement(this);
                this.currentPosition = pointBooleanTransmitter.getPoint();
                animations();
            }
            /*
             * if (heroInRoom()) {
             * this.movementBehaviour = this.aggressiveBehaviour;
             * walkingCount++;
             * if (this.attributes.getCurrentHP() > this.attributes.getMaxHP() / 2) {
             * if (walkingCount % 5 == 0) {
             * this.pointBooleanTransmitter =
             * this.movementBehaviour.getMonsterMovement(this);
             * this.currentPosition = pointBooleanTransmitter.getPoint();
             * animations();
             * }
             * }
             * } else {
             * this.movementBehaviour = this.initialMovementBehaviour;
             * this.pointBooleanTransmitter =
             * this.movementBehaviour.getMonsterMovement(this);
             * this.currentPosition = pointBooleanTransmitter.getPoint();
             * animations();
             * }
             */
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

    public Point getEscapeRoom() {
        return escapeRoom;
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

    public void setCurrentPosition(Point point) {
        this.currentPosition = point;
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
