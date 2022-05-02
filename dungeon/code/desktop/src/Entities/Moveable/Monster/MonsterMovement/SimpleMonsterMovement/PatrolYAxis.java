package Entities.Moveable.Monster.MonsterMovement.SimpleMonsterMovement;

import Entities.Fight.Fight;
import Entities.Moveable.Monster.Monster;
import Entities.Moveable.Monster.MonsterMovement.IMovement;
import Helper.PointBooleanTransmitter;
import tools.Point;

public class PatrolYAxis implements IMovement{

    private boolean runDirectionX;
    private boolean collision;
    Point newPosition;

    public PatrolYAxis(){
        this.runDirectionX = true;
        this.collision = false;
    }

    @Override
    public PointBooleanTransmitter getMonsterMovement(Monster monster) {
        this.newPosition = new Point(monster.getPosition());
        if(this.runDirectionX){
            this.newPosition.y += monster.getAttributes().getMovementSpeed();

            if(monster.getCurrentFloor().getTileAt(this.newPosition.toCoordinate()) == monster.getHero().getCurrentFloor().getTileAt(monster.getHero().getPosition().toCoordinate())){
                fightTrigger(monster);
            }

            if(monster.getCurrentFloor().getTileAt(newPosition.toCoordinate()).isAccessible()){
                return new PointBooleanTransmitter(this.runDirectionX, this.collision, this.newPosition);
            }else{
                this.runDirectionX = false;
                return new PointBooleanTransmitter(this.runDirectionX, this.collision, monster.getPosition());
            }
        }else{
            newPosition.y -= monster.getAttributes().getMovementSpeed();
            
            if(monster.getCurrentFloor().getTileAt(this.newPosition.toCoordinate()) == monster.getHero().getCurrentFloor().getTileAt(monster.getHero().getPosition().toCoordinate())){
                fightTrigger(monster);
            }

            if(monster.getCurrentFloor().getTileAt(newPosition.toCoordinate()).isAccessible()){
                return new PointBooleanTransmitter(this.runDirectionX, this.collision, this.newPosition);
            }else{
                this.runDirectionX = true;
                return new PointBooleanTransmitter(this.runDirectionX, this.collision, monster.getPosition());
            }
        }
    }

    private void fightTrigger(Monster monster){
        if (new Fight(monster).fight()) {
            if(this.runDirectionX){
                this.newPosition.y -= 1.0f;
            }else{
                this.newPosition.y += 1.0f;
            }
            this.collision = true;
        }
    }
    
}
