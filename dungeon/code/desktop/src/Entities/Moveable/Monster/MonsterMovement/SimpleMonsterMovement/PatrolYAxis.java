package Entities.Moveable.Monster.MonsterMovement.SimpleMonsterMovement;

import Entities.Fight.Fight;
import Entities.Moveable.Monster.Monster;
import Entities.Moveable.Monster.MonsterMovement.IMovement;
import Helper.Booleans;
import Helper.PointBooleanTransmitter;
import tools.Point;

public class PatrolYAxis implements IMovement{

    private boolean runDirectionY;
    private boolean collision;
    private Point newPosition;
    private Point newHeroPoint;

    public PatrolYAxis(){
        this.runDirectionY = true;
        this.collision = false;
    }

    @Override
    public PointBooleanTransmitter getMonsterMovement(Monster monster) {
        this.newPosition = new Point(monster.getPosition());
        if(this.runDirectionY){
            this.newPosition.y += monster.getAttributes().getMovementSpeed();

            if(monster.getCurrentFloor().getTileAt(this.newPosition.toCoordinate()) == monster.getHero().getCurrentFloor().getTileAt(monster.getHero().getPosition().toCoordinate())){
                fightTrigger(monster);
            }

            if(monster.getCurrentFloor().getTileAt(newPosition.toCoordinate()).isAccessible()){
                return new PointBooleanTransmitter(this.runDirectionY, this.collision, this.newPosition);
            }else{
                this.runDirectionY = false;
                return new PointBooleanTransmitter(this.runDirectionY, this.collision, monster.getPosition());
            }
        }else{
            newPosition.y -= monster.getAttributes().getMovementSpeed();
            
            if(monster.getCurrentFloor().getTileAt(this.newPosition.toCoordinate()) == monster.getHero().getCurrentFloor().getTileAt(monster.getHero().getPosition().toCoordinate())){
                fightTrigger(monster);
            }

            if(monster.getCurrentFloor().getTileAt(newPosition.toCoordinate()).isAccessible()){
                return new PointBooleanTransmitter(this.runDirectionY, this.collision, this.newPosition);
            }else{
                this.runDirectionY = true;
                return new PointBooleanTransmitter(this.runDirectionY, this.collision, monster.getPosition());
            }
        }
    }

    private void fightTrigger(Monster monster){
        Fight fight = new Fight(monster);
        Booleans fightresult = fight.fight();
        if(fightresult.getMonsterDmg()){
            if(this.runDirectionY){
                this.newPosition.y -= 1.0f;
            }else{
                this.newPosition.y += 1.0f;
            }
            this.collision = true;
        }

        if(fightresult.getHeroDmg()){
            this.newHeroPoint = new Point(monster.getHero().getPosition());
            if(this.runDirectionY){
                this.newHeroPoint.y += 1.0f;
            }else{
                this.newHeroPoint.y -= 1.0f;
            }

            if(monster.getHero().getCurrentFloor().getTileAt(this.newHeroPoint.toCoordinate()).isAccessible()){
                monster.getHero().setPosition(this.newHeroPoint);
            }
        }

        
    }
    
}