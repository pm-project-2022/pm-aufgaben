package Entities.Moveable.Monster.Monsterstate;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entities.Moveable.Monster.Monster;
import Logger.ColumnFormatter;

/**
 * PatrolState eines Monsters. In Diesem State patrolliert ein Monster entlang der X oder Y Achse.
 */

public class PatrolState extends State implements IState{
    private Logger log;

    public PatrolState(){
        initLogger();
        
    }

     /**
     * initiiert den logger
     */
    private void initLogger(){
        log = Logger.getLogger("PatrolState Logger");
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        ch.setFormatter(new ColumnFormatter());
        log.addHandler(ch);
        log.setLevel(Level.ALL);
        log.setUseParentHandlers(false);
    }

    @Override
    public void movement(Monster monster) {
        if(!monster.getMovementBehaviour().equals(monster.getPatrolMovement())){
            monster.setMovementBehaviour(monster.getPatrolMovement());
            log.info("Monster befindet sich im PatrolState");
        }
        exit(monster);    
    }

    /**
     * Bestimmt die exit-Condition des States
     * @param monster current monster
     */
    private void exit(Monster monster){
        if(heroInRoom(monster) && hpThresholdAggresivePatrolState(monster)){
            monster.setCurrentState(monster.getAttackState());
            log.info("Monster verlässt den PatrolState und wechselt in den AggressiveState.");
        }else if(hpThresholdEscapeState(monster)){
            monster.setCurrentState(monster.getEscapeState());
            log.info("Monster verlässt den PatrolState und wechselt in den EscapeState.");
        }else if(hpThresholdHelpState(monster)){
            monster.setCurrentState(monster.getHelpState());
            log.info("Monster verlässt den PatrolState und wechselt in den HelpState.");
        }else if(hpThresholdDeathState(monster)){
            monster.setCurrentState(monster.getDeathState());
            log.info("Monster verlässt den PatrolState und wechselt in den DeathState.");
        }   
    }
}
