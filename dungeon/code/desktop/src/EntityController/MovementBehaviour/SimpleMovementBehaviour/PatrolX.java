package EntityController.MovementBehaviour.SimpleMovementBehaviour;

import EntityController.Fight.Fight;
import EntityController.Monster.Monster;
import EntityController.MovementBehaviour.IMovementBehaviour;
import helper.PointAndBoolean;
import tools.Point;

public class PatrolX implements IMovementBehaviour {
    boolean runDirectionX;
    boolean collision;
    public PatrolX(){
        this.runDirectionX = true;
        this.collision = false;
    }

    @Override
    public PointAndBoolean getMovementBehaviour(Monster monster) {
        Point newPosition = new Point(monster.getPosition());
        if(this.runDirectionX){
            newPosition.x += monster.getStats().getMovementspeed();

            if(monster.getCurrentLevel().getTileAt(newPosition.toCoordinate()) == monster.getHero().getLevel().getTileAt(monster.getHero().getPosition().toCoordinate())){
                newPosition.x -= 1.0f;
                this.collision = true;
                new Fight(monster).fight();
            }

            if(monster.getCurrentLevel().getTileAt(newPosition.toCoordinate()).isAccessible()){
                return new PointAndBoolean(this.runDirectionX, this.collision,newPosition);
            }else{
                this.runDirectionX = false;
                return new PointAndBoolean(this.runDirectionX, this.collision, monster.getPosition());
            }
        }else{
            newPosition.x -= monster.getStats().getMovementspeed();
            if(monster.getCurrentLevel().getTileAt(newPosition.toCoordinate()) == monster.getHero().getLevel().getTileAt(monster.getHero().getPosition().toCoordinate())){
                this.collision = true;
                newPosition.x += 1.0f;
                new Fight(monster).fight();
            }

            if(monster.getCurrentLevel().getTileAt(newPosition.toCoordinate()).isAccessible()){
                return new PointAndBoolean(this.runDirectionX, this.collision, newPosition);
            }else{
                this.runDirectionX = true;
                return new PointAndBoolean(this.runDirectionX, this.collision, monster.getPosition());
            }
        }
    }
}
