package EntityController.MovementBehaviour.SimpleMovementBehaviour;

import EntityController.Fight.Fight;
import EntityController.Hero.MyHero;
import EntityController.Monster.Monster;
import EntityController.MovementBehaviour.IMovementBehaviour;
import EntityController.Statuswerte.StatusValues;
import helper.PointAndBoolean;
import level.elements.Level;
import tools.Point;

public class PatrolY implements IMovementBehaviour {
    boolean runDirectionY;
    boolean collision;
    public PatrolY(){
        this.runDirectionY = true;
        this.collision = false;
    }

    @Override
    public PointAndBoolean getMovementBehaviour(Point currentPosition, StatusValues stats, Level currentLevel, MyHero hero, Monster monster) {
        Point newPosition = new Point(currentPosition);
        if(this.runDirectionY){
            newPosition.y += stats.getMovementspeed();

            if(currentLevel.getTileAt(newPosition.toCoordinate()) == hero.getLevel().getTileAt(hero.getPosition().toCoordinate())){
                newPosition.y -= 1.0f;
                this.collision = true;
                new Fight(monster, hero).fight();
            }

            if(currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()){
                return new PointAndBoolean(this.runDirectionY, this.collision, newPosition);
            }else{
                this.runDirectionY = false;
                return new PointAndBoolean(this.runDirectionY,this.collision, currentPosition);
            }
        }else{
            newPosition.y -= stats.getMovementspeed();

            if(currentLevel.getTileAt(newPosition.toCoordinate()) == hero.getLevel().getTileAt(hero.getPosition().toCoordinate())){
                newPosition.y += 1.0f;
                this.collision = true;
                new Fight(monster, hero).fight();
            }

            if(currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()){
                return new PointAndBoolean(this.runDirectionY,this.collision, newPosition);
            }else{
                this.runDirectionY = true;
                return new PointAndBoolean(this.runDirectionY,this.collision, currentPosition);
            }
        }
    }
    
}
