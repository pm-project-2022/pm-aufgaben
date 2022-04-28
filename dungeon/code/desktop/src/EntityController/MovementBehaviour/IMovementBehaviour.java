package EntityController.MovementBehaviour;

import EntityController.Hero.MyHero;
import EntityController.Monster.Monster;
import helper.PointAndBoolean;


/**
 * interface for the movement behaviour
 */

public interface IMovementBehaviour {
    
    /**
     * template methode for the movement behaviour
     * @param currentPosition of the monster
     * @param stats stats of the monster
     * @param currentLevel
     * @param hero
     * @param monster
     * @return
     */
    PointAndBoolean getMovementBehaviour(Monster monster);
}
