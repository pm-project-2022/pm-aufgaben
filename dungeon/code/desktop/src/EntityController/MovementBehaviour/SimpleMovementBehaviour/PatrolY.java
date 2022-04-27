package EntityController.MovementBehaviour.SimpleMovementBehaviour;

import EntityController.MovementBehaviour.IMovementBehaviour;
import EntityController.Statuswerte.StatusValues;
import helper.PointAndBoolean;
import level.elements.Level;
import tools.Point;

public class PatrolY implements IMovementBehaviour {
    boolean runDirectionY;

    public PatrolY(){
        this.runDirectionY = true;
    }

    @Override
    public PointAndBoolean getMovementBehaviour(Point currentPosition, StatusValues stats, Level currentLevel) {
        Point newPosition = new Point(currentPosition);
        if(this.runDirectionY){
            newPosition.y += stats.getMovementspeed();
            if(currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()){
                return new PointAndBoolean(true, newPosition);
            }else{
                this.runDirectionY = false;
                return new PointAndBoolean(true, currentPosition);
            }
        }else{
            newPosition.y -= stats.getMovementspeed();
            if(currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()){
                return new PointAndBoolean(true, newPosition);
            }else{
                this.runDirectionY = true;
                return new PointAndBoolean(true, currentPosition);
            }
        }
    }
    
}
