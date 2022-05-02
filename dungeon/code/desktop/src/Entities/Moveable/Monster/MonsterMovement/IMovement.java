package Entities.Moveable.Monster.MonsterMovement;

import Entities.Moveable.Monster.Monster;
import Helper.PointBooleanTransmitter;

public interface IMovement {
    PointBooleanTransmitter getMonsterMovement(Monster monster);
}
