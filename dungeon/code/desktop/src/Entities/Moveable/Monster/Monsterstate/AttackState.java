package Entities.Moveable.Monster.Monsterstate;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entities.Moveable.Monster.Monster;
import Logger.ColumnFormatter;

/**
 * AttackState eines Monsters. In Diesem State bewegt sich das Monster, sofern es mehr als 50% der maxHp hat und der Held im Raum ist auf diesen zu um ihn zu attackieren.
 */

public class AttackState extends State implements IState {
    
    private Logger log;

    public AttackState(){
        initLogger();
    }

    /**
     * initiiert den logger
     */
    private void initLogger(){
        log = Logger.getLogger("AttackState Logger");
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        ch.setFormatter(new ColumnFormatter());
        log.addHandler(ch);
        log.setLevel(Level.ALL);
        log.setUseParentHandlers(false);
    }

    @Override
    public void movement(Monster monster) {
        if(!monster.getMovementBehaviour().equals(monster.getAttackBehaviour())){
            monster.setMovementBehaviour(monster.getAttackBehaviour() );
            log.info("Monster befindet sich AttackState");
        }
        exit(monster);
    }

    /**
     * Bestimmt die exit-Condition des States
     * @param monster current monster
     */
    private void exit(Monster monster){
        if(!heroInRoom(monster) && hpThresholdAggresivePatrolState(monster)){
            monster.setCurrentState(monster.getPatrolState());
            log.info("Monster verlässt den AttackState und wechselt in den PatrolState.");
        }else if(hpThresholdEscapeState(monster)){
            monster.setCurrentState(monster.getEscapeState());
            log.info("Monster verlässt den AttackState und wechselt in den EscapeState.");
        }else if(hpThresholdHelpState(monster)){
            monster.setCurrentState(monster.getHelpState());
            log.info("Monster verlässt den Attackstate und wechselt in den HelpState.");
        }else if(hpThresholdDeathState(monster)){
            monster.setCurrentState(monster.getDeathState());
            log.info("Monster verlässt den AttackeState und wechselt in den DeathState.");
        }
    }
}
