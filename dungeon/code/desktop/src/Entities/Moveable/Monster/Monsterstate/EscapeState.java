package Entities.Moveable.Monster.Monsterstate;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entities.Moveable.Monster.Monster;
import Logger.ColumnFormatter;

/**
 * EscapeState eines Monsters. In Diesem State hört das Monster auf anzugreifen und  flüchtet mit einem unverhersehbaren Movement vor dem Helden. 
 */

public class EscapeState extends State implements IState {

    private Logger log;

    public EscapeState(){
        initLogger();
    }

    /**
     * initiiert den logger
     */
    private void initLogger(){
        log = Logger.getLogger("EscapeState Logger");
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        ch.setFormatter(new ColumnFormatter());
        log.addHandler(ch);
        log.setLevel(Level.ALL);
        log.setUseParentHandlers(false);
    }

    @Override
    public void movement(Monster monster) {
        if(!monster.getMovementBehaviour().equals(monster.getEscapeBehavior())){
            monster.setMovementBehaviour(monster.getEscapeBehavior());
            log.info("Monster befindet sich im EscapeState");
        }
        exit(monster);
    }

     /**
     * Bestimmt die exit-Condition des States
     * @param monster current monster
     */
    private void exit(Monster monster){
        if(hpThresholdHelpState(monster)){
            monster.setCurrentState(monster.getHelpState());
            log.info("Monster verlässt den EscapeState und wechselt in den HelpState.");
        }else if(hpThresholdDeathState(monster)){
            monster.setCurrentState(monster.getDeathState());
            log.info("Monster verlässt den PatrolState und wechselt in den DeathState.");
        }
    }
    
}
