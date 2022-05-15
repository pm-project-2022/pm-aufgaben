package Entities.Moveable.Monster.MonsterMovement.ComplexBehaviour;

import Entities.Fight.Fight;
import Entities.Moveable.Monster.Monster;
import Entities.Moveable.Monster.MonsterMovement.IMovement;
import com.badlogic.gdx.ai.pfa.GraphPath;
import Helper.PointBooleanTransmitter;
import level.elements.room.Tile;
import tools.Point;

/**
 * Wenn dieses Verhalten ausgelöst wird, läuft das Monster unvorhersehbar vom dem Helden weg.
 */

public class Escape implements IMovement {
    //startpunkt der fluchtroute
    private Point newPosition;

    //endpunkt der fluchtroute
    private Point escapePosition;
    private Point expectedEscapePosition;
    private boolean escapePositionReached;
    private boolean runDirection;
    private boolean collision;
    private int counter;

    public Escape() {
        this.escapePositionReached = true;
        this.runDirection = true;
        this.collision = false;
        this.counter = 0;
    }

    @Override
    public PointBooleanTransmitter getMonsterMovement(Monster monster) {
        this.newPosition = new Point(monster.getPosition());
        if (this.escapePositionReached) {
            calculateEscapePosition(monster);
        }
        this.counter++;
        if (this.counter % 6 == 0) {
            buildEscapePatch(monster);
        }

        if(monster.getCurrentFloor().getTileAt(monster.getPosition().toCoordinate()) == monster.getHero().getCurrentFloor().getTileAt(monster.getHero().getPosition().toCoordinate())){
            startFight(monster);
        }

        if (monster.getCurrentFloor().getTileAt(escapePosition.toCoordinate()) == monster.getCurrentFloor()
                .getTileAt(this.newPosition.toCoordinate())) {
            this.escapePositionReached = true;
        }
        return new PointBooleanTransmitter(this.runDirection, this.collision, this.newPosition);
    }

    /**
     * erzeugt den endpunkt der fluchtroute
     * @param monster current monster
     */
    private void calculateEscapePosition(Monster monster) {
        do {
            this.expectedEscapePosition = monster.getCurrentFloor().getRandomRoom().getRandomFloorTile().getCoordinate()
                    .toPoint();
        } while (!monster.getCurrentFloor().getTileAt(this.expectedEscapePosition.toCoordinate()).isAccessible());
        this.escapePosition = this.expectedEscapePosition;
        this.escapePositionReached = false;
    }

    /**
     * baut eine fluchtroute ausgehend vom startpunkt und endpunkt
     * @param monster current monster
     */
    private void buildEscapePatch(Monster monster) {
        GraphPath<Tile> escapePath = monster.getCurrentFloor().findPath(
                monster.getCurrentFloor().getTileAt(monster.getPosition().toCoordinate()),
                monster.getCurrentFloor().getTileAt(this.escapePosition.toCoordinate()));

        if (escapePath.getCount() > 1) {
            this.newPosition = escapePath.get(1).getCoordinate().toPoint();
            if (this.newPosition.x < monster.getPosition().x) {
                this.runDirection = true;
            } else {
                this.runDirection = false;
            }
        } else {
            this.newPosition = this.escapePosition;
        }

    }

    /**
     * initiates a fight between the current monster and the hero
     * 
     * @param monster
     */
    private void startFight(Monster monster) {
        Fight fight = new Fight(monster);
        fight.fight();
    }

}
