package EntityController.MovementBehaviour.SimpleMovementBehaviour;

import EntityController.Fight.Fight;
import EntityController.Monster.Monster;
import EntityController.MovementBehaviour.IMovementBehaviour;
import helper.PointAndBoolean;
import tools.Point;

public class PatrolY implements IMovementBehaviour {
    boolean runDirectionY;
    boolean collision;
    public PatrolY(){
        this.runDirectionY = true;
        this.collision = false;
    }

    @Override
    public PointAndBoolean getMovementBehaviour(Monster monster) {
        Point newPosition = new Point(monster.getPosition());
        if(this.runDirectionY){
            newPosition.y += monster.getStats().getMovementspeed();

            if(monster.getCurrentLevel().getTileAt(newPosition.toCoordinate()) == monster.getHero().getLevel().getTileAt(monster.getHero().getPosition().toCoordinate())){
                newPosition.y -= 1.0f;
                this.collision = true;
                new Fight(monster).fight();
            }

            if(monster.getCurrentLevel().getTileAt(newPosition.toCoordinate()).isAccessible()){
                return new PointAndBoolean(this.runDirectionY, this.collision, newPosition);
            }else{
                this.runDirectionY = false;
                return new PointAndBoolean(this.runDirectionY,this.collision, monster.getPosition());
            }
        }else{
            newPosition.y -= monster.getStats().getMovementspeed();

            if(monster.getCurrentLevel().getTileAt(newPosition.toCoordinate()) == monster.getHero().getLevel().getTileAt(monster.getHero().getPosition().toCoordinate())){
                newPosition.y += 1.0f;
                this.collision = true;
                new Fight(monster).fight();
            }

            if(monster.getCurrentLevel().getTileAt(newPosition.toCoordinate()).isAccessible()){
                return new PointAndBoolean(this.runDirectionY,this.collision, newPosition);
            }else{
                this.runDirectionY = true;
                return new PointAndBoolean(this.runDirectionY,this.collision, monster.getPosition());
            }
        }
    }
    
}
