package EntityController.MovementBehaviour.SimpleMovementBehaviour;

import EntityController.Fight.Fight;
import EntityController.Hero.MyHero;
import EntityController.Monster.Monster;
import EntityController.MovementBehaviour.IMovementBehaviour;
import EntityController.Statuswerte.StatusValues;
import helper.PointAndBoolean;
import level.elements.Level;
import tools.Point;

public class PatrolX implements IMovementBehaviour {
    boolean runDirectionX;
    boolean collision;
    public PatrolX(){
        this.runDirectionX = true;
        this.collision = false;
    }

    @Override
    public PointAndBoolean getMovementBehaviour(Point currentPosition, StatusValues stats, Level currentLevel, MyHero hero, Monster monster) {
        Point newPosition = new Point(currentPosition);
        if(this.runDirectionX){
            newPosition.x += stats.getMovementspeed();

            if(currentLevel.getTileAt(newPosition.toCoordinate()) == hero.getLevel().getTileAt(hero.getPosition().toCoordinate())){
                newPosition.x -= 1.0f;
                this.collision = true;
                new Fight(monster, hero).fight();
            }

            if(currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()){
                return new PointAndBoolean(this.runDirectionX, this.collision,newPosition);
            }else{
                this.runDirectionX = false;
                return new PointAndBoolean(this.runDirectionX, this.collision, currentPosition);
            }
        }else{
            newPosition.x -= stats.getMovementspeed();
            if(currentLevel.getTileAt(newPosition.toCoordinate()) == hero.getLevel().getTileAt(hero.getPosition().toCoordinate())){
                this.collision = true;
                newPosition.x += 1.0f;
                new Fight(monster, hero).fight();
            }

            if(currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()){
                return new PointAndBoolean(this.runDirectionX, this.collision, newPosition);
            }else{
                this.runDirectionX = true;
                return new PointAndBoolean(this.runDirectionX, this.collision, currentPosition);
            }
        }
    }
}
