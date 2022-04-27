package EntityController.MovementBehaviour.SimpleMovementBehaviour;

import EntityController.MovementBehaviour.IMovementBehaviour;
import EntityController.Statuswerte.StatusValues;
import helper.PointAndBoolean;
import level.elements.Level;
import tools.Point;

public class PatrolX implements IMovementBehaviour {
    boolean runDirectionX;
    
    public PatrolX(){
        this.runDirectionX = true;
    }

    @Override
    public PointAndBoolean getMovementBehaviour(Point currentPosition, StatusValues stats, Level currentLevel) {
        Point newPosition = new Point(currentPosition);
        if(this.runDirectionX){
            newPosition.x += stats.getMovementspeed();
            if(currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()){
                return new PointAndBoolean(this.runDirectionX, newPosition);
            }else{
                this.runDirectionX = false;
                return new PointAndBoolean(this.runDirectionX, currentPosition);
            }
        }else{
            newPosition.x -= stats.getMovementspeed();
            if(currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()){
                return new PointAndBoolean(this.runDirectionX, newPosition);
            }else{
                this.runDirectionX = true;
                return new PointAndBoolean(this.runDirectionX, currentPosition);
            }
        }
    }
}
