package Entities.Moveable.Monster.MonsterMovement;

import Entities.Moveable.Monster.Monster;
import Helper.PointBooleanTransmitter;

/**
 * Implementiert das Movementverhalten eines Monsters
 */

public interface IMovement {

    /**
     * determines the new position of a monster depending on their movement
     * @param monster current monster
     * @return the new monster position
     */
    PointBooleanTransmitter getMonsterMovement(Monster monster);
}
