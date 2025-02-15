package Entities.Moveable.Monster.MonsterMovement.SimpleMonsterMovement;

import Entities.Fight.Melee.Fight;
import Entities.Moveable.Monster.Monster;
import Entities.Moveable.Monster.MonsterMovement.IMovement;
import Helper.Booleans;
import Helper.PointBooleanTransmitter;
import tools.Point;

/**
 * lässt das monster entlang sder x achse patrollieren
 */

public class PatrolXAxis implements IMovement {
    private boolean runDirectionX;
    private boolean collision;
    private Point newPosition;
    private Point newHeroPoint;

    public PatrolXAxis() {
        this.runDirectionX = true;
        this.collision = false;
    }

    @Override
    public PointBooleanTransmitter getMonsterMovement(Monster monster) {
        this.newPosition = new Point(monster.getPosition());
        if (this.runDirectionX) {
            newPosition.x += monster.getAttributes().getMovementSpeed();

            if (monster.getCurrentFloor().getTileAt(this.newPosition.toCoordinate()) == monster.getHero()
                    .getCurrentFloor().getTileAt(monster.getHero().getPosition().toCoordinate())) {
                fightTrigger(monster);
            }

            if (monster.getCurrentFloor().getTileAt(newPosition.toCoordinate()).isAccessible()) {
                return new PointBooleanTransmitter(this.runDirectionX, this.collision, newPosition);
            } else {
                this.runDirectionX = false;
                return new PointBooleanTransmitter(this.runDirectionX, this.collision, monster.getPosition());
            }
        } else {
            newPosition.x -= monster.getAttributes().getMovementSpeed();

            if (monster.getCurrentFloor().getTileAt(this.newPosition.toCoordinate()) == monster.getHero()
                    .getCurrentFloor().getTileAt(monster.getHero().getPosition().toCoordinate())) {
                fightTrigger(monster);
            }

            if (monster.getCurrentFloor().getTileAt(newPosition.toCoordinate()).isAccessible()) {
                return new PointBooleanTransmitter(this.runDirectionX, this.collision, newPosition);
            } else {
                this.runDirectionX = true;
                return new PointBooleanTransmitter(this.runDirectionX, this.collision, newPosition);
            }
        }
    }

    private void fightTrigger(Monster monster) {
        Fight fight = new Fight(monster);
        Booleans fightresult = fight.fight();
        if (fightresult.getMonsterDmg()) {
            if (this.runDirectionX) {
                this.newPosition.x -= 1.0f;
            } else {
                this.newPosition.x += 1.0f;
            }
            this.collision = true;
        }

        if (fightresult.getHeroDmg()) {
            this.newHeroPoint = monster.getHero().getPosition();
            if (this.runDirectionX) {
                this.newHeroPoint.x += 1.0f;
            } else {
                this.newHeroPoint.x -= 1.0f;
            }
            if(monster.getHero().getCurrentFloor().getTileAt(this.newHeroPoint.toCoordinate()).isAccessible()){
                monster.getHero().setPosition(this.newHeroPoint);
            }
        }

    }

}