package Entities.Moveable.Monster.Monsterstate;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entities.Moveable.Monster.Monster;
import Logger.ColumnFormatter;

/**
 * HelpState eines Monsters. Befindet sich ein Monster in diesem State, ist es zu schwach um sich bewegen oder zu verteidigen. Mit letzter Kraft versucht es nach Hilfe zu rufen.
 */

public class HelpState extends State implements IState {
    
    private Logger log;

    public HelpState(){
        initLogger();
    }

    /**
     * initiiert den logger
     */
    private void initLogger(){
        log = Logger.getLogger("HelpState Logger");
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        ch.setFormatter(new ColumnFormatter());
        log.addHandler(ch);
        log.setLevel(Level.ALL);
        log.setUseParentHandlers(false);
    }

    @Override
    public void movement(Monster monster) {
        if(!monster.getMovementBehaviour().equals(monster.getHelpBehaviour())){
            monster.setMovementBehaviour(monster.getHelpBehaviour());
            log.info("Monster befindet sich im HelpState");
        }
        exit(monster);
    }

    private void exit(Monster monster){
        if(hpThresholdDeathState(monster)){
            monster.setCurrentState(monster.getDeathState());
            log.info("Monster verlässt den HelpState und wechselt in den DeathState.");
        }
    }
    
}
