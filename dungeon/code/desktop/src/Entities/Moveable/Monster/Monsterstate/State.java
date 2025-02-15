package Entities.Moveable.Monster.Monsterstate;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entities.Moveable.Monster.Monster;
import Logger.ColumnFormatter;

/**
 * Abstrakte State Klasse, die als Vorlage für die Monsterstates agiert. Sie beinhaltet einen Logger und einen Großteil der State exit-Conditions.
 */

public abstract class State {

    protected Logger STATELOGGER;
    protected boolean setBehaviour;

    public State(String classname){
        initLogger(classname);
        this.setBehaviour = false;
    }

     /**
     * initiiert den logger
     */
    private void initLogger(String classname){
        STATELOGGER = Logger.getLogger(classname);
        for (Handler handler : STATELOGGER.getHandlers()) {
            STATELOGGER.removeHandler(handler);
        }
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        ch.setFormatter(new ColumnFormatter());
        STATELOGGER.addHandler(ch);
        STATELOGGER.setLevel(Level.ALL);
        STATELOGGER.setUseParentHandlers(false);
    }

    /**
     * Teil der exit-Condition zum Wechsel zwischen Attack und Patrol State. Ein Monster greift den Helden an, wenn sie sich im selben Raum befinden 
     * @param monster current monster
     * @return true, wenn der Held im selben Raum ist, ansonsten false
     */
    protected boolean heroInRoom(Monster monster){
        return monster.getCurrentFloor().getRoomToPoint(monster.getPosition().toCoordinate()) == monster.getHero().getCurrentFloor().getRoomToPoint(monster.getHero().getPosition().toCoordinate());
    }

    /**
     * hp threshold für den wechsel in den attackstate und patrolstate
     * @param monster current monster
     * @return true, wenn die currenthp das vorrausgesetze Verhältnis mit maxHP hat, ansonsten false
     */
    protected boolean hpThresholdAggresivePatrolState(Monster monster){
        return monster.getAttributes().getCurrentHP() > monster.getAttributes().getMaxHP() / 2 ? true : false;
    }

    /**
     * hp threshold für den wechsel in den escapestate
     * @param monster current monster
     * @return true, wenn die currenthp das vorrausgesetze Verhältnis mit maxHP hat, ansonsten false
     */
    protected boolean hpThresholdEscapeState(Monster monster){
        return monster.getAttributes().getCurrentHP() <= monster.getAttributes().getMaxHP() / 2  && monster.getAttributes().getCurrentHP() > monster.getAttributes().getMaxHP() / 4 ? true : false;
    }

    /**
     * hp threshold für den wechsel in den helpstate
     * @param monster current monster
     * @return true, wenn die currenthp das vorrausgesetze Verhältnis mit maxHP hat, ansonsten false
     */
    protected boolean hpThresholdHelpState(Monster monster){
        return monster.getAttributes().getCurrentHP() <= monster.getAttributes().getMaxHP() / 2  && monster.getAttributes().getCurrentHP() > monster.getAttributes().getMaxHP() / 4 ? true : false;
    }

    /**
     * hp threshold für den wechsel in den deadstate
     * @param monster current monster
     * @return true, wenn die currenthp das vorrausgesetze Verhältnis mit maxHP hat, ansonsten false
     */
    protected boolean hpThresholdDeathState(Monster monster){
        return monster.getAttributes().getCurrentHP() == 0 ? true : false;
    }
}
