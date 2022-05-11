package Entities.Moveable.Monster.MonsterMovement.ComplexBehaviour;

import com.badlogic.gdx.ai.pfa.GraphPath;

import Entities.Moveable.Monster.Monster;
import Entities.Moveable.Monster.MonsterMovement.IMovement;
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
        this.collision = true;
    }

    @Override
    public PointBooleanTransmitter getMonsterMovement(Monster monster) {
        this.newPosition = new Point(monster.getPosition());
        
        if(monster.getCurrentFloor().getRoomToPoint(monster.getPosition().toCoordinate()) == monster.getHero().getCurrentFloor().getRoomToPoint(monster.getHero().getPosition().toCoordinate())){
            GraphPath<Tile> path = monster.getCurrentFloor().findPath(monster.getCurrentFloor().getTileAt(monster.getPosition().toCoordinate()), monster.getHero().getCurrentFloor().getTileAt(monster.getHero().getPosition().toCoordinate()));
            
            if(path.getCount() > 1){
                this.newPosition = path.get(1).getCoordinate().toPoint();
            }
            

            return new PointBooleanTransmitter(false, false, this.newPosition);
        }


        return new PointBooleanTransmitter(true, false, this.newPosition);
    }
    
}
