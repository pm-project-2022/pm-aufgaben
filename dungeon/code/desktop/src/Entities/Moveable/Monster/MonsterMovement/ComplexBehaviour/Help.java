package Entities.Moveable.Monster.MonsterMovement.ComplexBehaviour;

import java.util.Random;

import Entities.Fight.Melee.Fight;
import Entities.Moveable.Monster.Monster;
import Entities.Moveable.Monster.MonsterMovement.IMovement;
import Helper.Booleans;
import Helper.PointBooleanTransmitter;
import tools.Point;

/**
 * Wenn dieses Verhalten ausgelöst wird, ruft das Monster nach Hilfe. Mit einer gewissen Wahrscheinlichkeit kommen ihm daraufhin alle kampffähigen Monster zur Hilfe.
 */

public class Help implements IMovement {
    private boolean helpCry;
    private Point newPosition;
    private Point expectedNewPosition;
    private Random random;
    private boolean collision;
    private boolean runDirection;

    public Help() {
        this.helpCry = true;
        this.random = new Random();
        this.runDirection = true;
        this.collision = true;
    }

    @Override
    public PointBooleanTransmitter getMonsterMovement(Monster monster) {
        this.newPosition = new Point(monster.getPosition());
        this.expectedNewPosition = new Point(monster.getPosition());

        if (helpCry) {
            spawnHelp(monster);
            this.helpCry = false;
        }

        if(monster.getCurrentFloor().getTileAt(monster.getPosition().toCoordinate()) == monster.getHero().getCurrentFloor().getTileAt(monster.getHero().getPosition().toCoordinate())){
            startFight(monster);
        }

        return new PointBooleanTransmitter(this.runDirection, this.collision, this.newPosition);
    }

    private int helpChance(Monster monster) {
        return (this.random.nextInt(3+1) + 1) % 3;
    }

    private void spawnHelp(Monster monster) {
        if (helpChance(monster) == 0) {
            for (Monster mon : monster.getMonster()) {
                if (mon.getAttributes().getCurrentHP() > mon.getAttributes().getMaxHP() / 2) {
                    mon.setCurrentPosition(
                            monster.getCurrentFloor().getRoomToPoint(monster.getPosition().toCoordinate())
                                    .getRandomFloorTile().getCoordinate().toPoint());
                }
            }
        }
    }

    /**
     * initiates a fight between the current monster and the hero
     * @param monster
     */
    private void startFight(Monster monster){
        Fight fight = new Fight(monster);
        Booleans fightResult = fight.fight();
        if(fightResult.getMonsterDmg()){
            if(this.runDirection){
                this.expectedNewPosition.x -= 1.0f;
            }else{
                this.expectedNewPosition.x += 1.0f;
            }
            if (monster.getCurrentFloor().getTileAt(this.expectedNewPosition.toCoordinate()).isAccessible()) {
                this.newPosition = this.expectedNewPosition;
            }
        }
    }

}
