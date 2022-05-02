package Entities.Moveable.Monster.MonsterMovement.SimpleMonsterMovement;

import Entities.Fight.Fight;
import Entities.Moveable.Monster.Monster;
import Entities.Moveable.Monster.MonsterMovement.IMovement;
import Helper.PointBooleanTransmitter;
import tools.Point;

public class Idle implements IMovement {
    private boolean idle;
    private Point newPosition;
    private boolean runDirection;
    private boolean collision;

    public Idle(boolean runDirection) {
        this.runDirection = runDirection;
        this.collision = true;
    }

    @Override
    public PointBooleanTransmitter getMonsterMovement(Monster monster) {
        this.newPosition = new Point(monster.getPosition());
        if (runDirection) {
            if (monster.getCurrentFloor().getTileAt(this.newPosition.toCoordinate()) == monster.getHero()
                    .getCurrentFloor().getTileAt(monster.getHero().getPosition().toCoordinate())) {
                fightTrigger(monster);
            }

            if (monster.getCurrentFloor().getTileAt(newPosition.toCoordinate()).isAccessible()) {
                return new PointBooleanTransmitter(this.runDirection, this.idle, this.newPosition);

            } else {
                return new PointBooleanTransmitter(this.runDirection, this.collision, this.newPosition);
            }

        } else {
            if (monster.getCurrentFloor().getTileAt(this.newPosition.toCoordinate()) == monster.getHero()
                    .getCurrentFloor().getTileAt(monster.getHero().getPosition().toCoordinate())) {
                fightTrigger(monster);
            }

            if (monster.getCurrentFloor().getTileAt(newPosition.toCoordinate()).isAccessible()) {
                return new PointBooleanTransmitter(this.runDirection, this.collision, this.newPosition);

            } else {
                return new PointBooleanTransmitter(this.runDirection, this.collision, this.newPosition);
            }
        }
    }

    private void fightTrigger(Monster monster) {
        if (new Fight(monster).fight()) {
            if (this.runDirection) {
                this.newPosition.x -= 1.0f;
            } else {
                this.newPosition.x += 1.0f;
            }
            this.collision = true;
        }
    }
}