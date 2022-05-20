package Entities.Moveable.Monster.MonsterMovement.ComplexBehaviour;

import com.badlogic.gdx.ai.pfa.GraphPath;

import Entities.Fight.Melee.Fight;
import Entities.Moveable.Monster.Monster;
import Entities.Moveable.Monster.MonsterMovement.IMovement;
import Helper.Booleans;
import Helper.PointBooleanTransmitter;
import level.elements.room.Tile;
import tools.Point;


/**
 * Wenn dieses Verhalten ausgelöst wird, läuft das Monster auf den Helden zu und greift es an.
 */

public class AggressiveMovement implements IMovement {
    private Point expectedNewPosition;
    private Point newPosition;
    private boolean runDirection;
    private boolean collision;
    private int counter;

    public AggressiveMovement() {
        this.runDirection = true;
        this.collision = false;
        this.counter = 0;
    }

    /**
     * if the monster and the hero are on the same tile, a fight starts
     */

    @Override
    public PointBooleanTransmitter getMonsterMovement(Monster monster) {
        this.newPosition = new Point(monster.getPosition());
        this.expectedNewPosition = new Point(monster.getPosition());
        
        this.counter++;
        if (this.counter % 6 == 0) {
            buildEscapePatch(monster);
        }
        
        if(monster.getCurrentFloor().getTileAt(monster.getPosition().toCoordinate()) == monster.getHero().getCurrentFloor().getTileAt(monster.getHero().getPosition().toCoordinate())){
            startFight(monster);
        }

        return new PointBooleanTransmitter(this.runDirection, this.collision, this.newPosition);
    }

    /**
     * builds attack path with current monster position and hero position
     * 
     * @param monster current monster
     */
    private void buildEscapePatch(Monster monster) {
        GraphPath<Tile> attackPath = monster.getCurrentFloor().findPath(
                monster.getCurrentFloor().getTileAt(monster.getPosition().toCoordinate()),
                monster.getHero().getCurrentFloor().getTileAt(monster.getHero().getPosition().toCoordinate()));

        if (attackPath.getCount() > 1) {
            this.newPosition = attackPath.get(1).getCoordinate().toPoint();
            if (this.newPosition.x < monster.getPosition().x) {
                this.runDirection = true;
            } else {
                this.runDirection = false;
            }
        } else {
            this.newPosition = monster.getHero().getPosition();
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
