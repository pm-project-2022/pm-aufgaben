package EntityController.MovementBehaviour;

import EntityController.Hero.MyHero;
import EntityController.Monster.Monster;
import EntityController.Statuswerte.StatusValues;
import helper.PointAndBoolean;
import level.elements.Level;
import tools.Point;

public interface IMovementBehaviour {
    
    PointAndBoolean getMovementBehaviour(Point currentPosition, StatusValues stats, Level currentLevel, MyHero hero, Monster monster);
}
