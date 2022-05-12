package Entities.Moveable.Monster.MonsterMovement.ComplexBehaviour;

import Entities.Moveable.Monster.Monster;
import Entities.Moveable.Monster.MonsterMovement.IMovement;
import Helper.PointBooleanTransmitter;
import tools.Point;

public class Escape implements IMovement {
    private Point newPosition;
    private Point escapePositio;
    private boolean runDirection;
    private boolean collision;

    public Escape() {
        this.runDirection = true;
        this.collision = false;
    }

    @Override
    public PointBooleanTransmitter getMonsterMovement(Monster monster) {
        return null;
    }

}
