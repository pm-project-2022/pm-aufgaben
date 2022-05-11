package Entities.Moveable.Monster.MonsterMovement.ComplexBehaviour;

import com.badlogic.gdx.ai.pfa.GraphPath;

import Entities.Fight.Fight;
import Entities.Moveable.Monster.Monster;
import Entities.Moveable.Monster.MonsterMovement.IMovement;
import Helper.Booleans;
import Helper.PointBooleanTransmitter;
import level.elements.room.Tile;
import tools.Point;

public class AggressiveMovement implements IMovement{
    private Point newPosition;
    private Point newHeroPoint;
    private boolean runDirection;
    private boolean collision;

    public AggressiveMovement() {
        this.runDirection = true;
        this.collision = false;
    }

    @Override
    public PointBooleanTransmitter getMonsterMovement(Monster monster) {
        this.newPosition = new Point(monster.getPosition());
        
        GraphPath<Tile> path = monster.getCurrentFloor().findPath(monster.getCurrentFloor().getTileAt(monster.getPosition().toCoordinate()), monster.getHero().getCurrentFloor().getTileAt(monster.getHero().getPosition().toCoordinate()));
            
        if(path.getCount() > 1){
            this.newPosition = path.get(1).getCoordinate().toPoint();
            if(this.newPosition.x < monster.getHero().getPosition().x){
                this.runDirection = true;
            }else{
                this.runDirection = false;
            }
        }else{
            this.newPosition = monster.getHero().getPosition();
        }
        
        if(this.newPosition == monster.getHero().getPosition()){
            fightTrigger(monster);
        }

        if (monster.getCurrentFloor().getTileAt(newPosition.toCoordinate()).isAccessible()) {
            return new PointBooleanTransmitter(this.runDirection, false, this.newPosition);
        } else {
            return new PointBooleanTransmitter(this.runDirection, false, monster.getPosition());
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
        }



        if(fightresult.getHeroDmg()){
            this.newHeroPoint = new Point(monster.getHero().getPosition());
            if(this.runDirection){
                this.newHeroPoint.x += 1.0f;
            }else{
                this.newHeroPoint.y -= 1.0f;
            }

            if(monster.getHero().getCurrentFloor().getTileAt(this.newHeroPoint.toCoordinate()).isAccessible()){
                monster.getHero().setPosition(this.newHeroPoint);
            }else{
                monster.getHero().setPosition(monster.getHero().getPosition());
            }
        }

        
    }
    
}
