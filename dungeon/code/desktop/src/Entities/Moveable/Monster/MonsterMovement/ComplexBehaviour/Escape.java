package Entities.Moveable.Monster.MonsterMovement.ComplexBehaviour;

import com.badlogic.gdx.ai.pfa.GraphPath;

import Entities.Fight.Fight;
import Entities.Moveable.Monster.Monster;
import Entities.Moveable.Monster.MonsterMovement.IMovement;
import Helper.Booleans;
import Helper.PointBooleanTransmitter;
import level.elements.room.Tile;
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
        this.newPosition = new Point(monster.getPosition());
        GraphPath<Tile> path = monster.getCurrentFloor().findPath(
                monster.getCurrentFloor().getTileAt(monster.getPosition().toCoordinate()),
                monster.getCurrentFloor().getTileAt(monster.getEscapeRoom().toCoordinate()));
        
        if (path.getCount() > 1) {
            this.newPosition = path.get(1).getCoordinate().toPoint();
            if (this.newPosition.x < monster.getPosition().x) {
                this.runDirection = true;
            } else {
                this.runDirection = false;
            }
        } else {
            this.newPosition = monster.getEscapeRoom();
        }

        if (monster.getCurrentFloor().getTileAt(monster.getPosition().toCoordinate()) == monster.getHero().getCurrentFloor().getTileAt(monster.getHero().getPosition().toCoordinate())) {
            new Fight(monster).fight();
        }

        if (monster.getCurrentFloor().getTileAt(newPosition.toCoordinate()).isAccessible()) {
            return new PointBooleanTransmitter(this.runDirection, false, this.newPosition);
        } else {
            return new PointBooleanTransmitter(this.runDirection, false, monster.getPosition());
        }
    }

    /*private void fightTrigger(Monster monster) {
        Fight fight = new Fight(monster);
        Booleans fightresult = fight.fight();
        if (fightresult.getMonsterDmg()) {
            if (this.runDirection) {
                this.newPosition.x -= 1.0f;
            } else {
                this.newPosition.x += 1.0f;
            }
        }

    }*/
}
