package Entities.Moveable.Monster.MonsterMovement.SimpleMonsterMovement;

import Entities.Fight.Fight;
import Entities.Moveable.Monster.Monster;
import Entities.Moveable.Monster.MonsterMovement.IMovement;
import Helper.Booleans;
import Helper.PointBooleanTransmitter;
import tools.Point;

public class Idle implements IMovement {
    private boolean idle;
    private Point newPosition;
    private Point newHeroPoint;
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

            if (monster.getCurrentFloor().getTileAt(this.newPosition.toCoordinate()).isAccessible()) {
                return new PointBooleanTransmitter(this.runDirection, this.idle, this.newPosition);

            } else {
                return new PointBooleanTransmitter(this.runDirection, this.collision, monster.getPosition());
            }

        } else {
            if (monster.getCurrentFloor().getTileAt(this.newPosition.toCoordinate()) == monster.getHero()
                    .getCurrentFloor().getTileAt(monster.getHero().getPosition().toCoordinate())) {
                fightTrigger(monster);
            }

            if (monster.getCurrentFloor().getTileAt(newPosition.toCoordinate()).isAccessible()) {
                return new PointBooleanTransmitter(this.runDirection, this.collision, this.newPosition);

            } else {
                return new PointBooleanTransmitter(this.runDirection, this.collision, monster.getPosition());
            }
        }
    }

    private void fightTrigger(Monster monster){
        Fight fight = new Fight(monster);
        Booleans fightresult = fight.fight();
        if(fightresult.getMonsterDmg()){
            if(this.runDirection){
                this.newPosition.x -= 1.0f;
            }else{
                this.newPosition.x += 1.0f;
            }
            this.collision = true;
        }

        if(fightresult.getHeroDmg()){
            this.newHeroPoint = new Point(monster.getHero().getPosition());
            if(this.runDirection){
                this.newHeroPoint.x += 1.0f;
            }else{
                this.newHeroPoint.x -= 1.0f;
            }

            if(monster.getHero().getCurrentFloor().getTileAt(this.newHeroPoint.toCoordinate()).isAccessible()){
                monster.getHero().setPosition(this.newHeroPoint);
            }else{
                monster.getHero().setPosition(monster.getHero().getPosition());
            }
        }
    }
}