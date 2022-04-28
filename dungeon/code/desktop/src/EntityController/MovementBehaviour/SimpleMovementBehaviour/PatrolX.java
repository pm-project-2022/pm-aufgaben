package EntityController.MovementBehaviour.SimpleMovementBehaviour;

import EntityController.Fight.Fight;
import EntityController.Monster.Monster;
import EntityController.MovementBehaviour.IMovementBehaviour;
import helper.PointAndBoolean;
import tools.Point;

/**
 * Makes the monster run along the Y axis
 */

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
                
                if (new Fight(monster).fight()) {
                    newPosition.x -= 1.0f;
                    this.collision = true;
                }
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
                if (new Fight(monster).fight()) {
                    newPosition.x += 1.0f;
                    this.collision = true;
                }
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
