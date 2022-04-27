package EntityController.MovementBehaviour;

import EntityController.Statuswerte.StatusValues;
import level.elements.Level;
import tools.Point;

public interface IMovementBehaviour {
    
    Point getMovementBehaviour(Point currentPosition, StatusValues stats, Level currentLevel);
}
