package Entities.Moveable.Monster.Monsterstate;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entities.Moveable.Monster.Monster;
import Logger.ColumnFormatter;

/**
 * Deathstate eines Monsters. Befindet sich das Monster in diesen State, ist es tot und wird aus dem Entity-Controller entfernt
 */

public class DeathState extends State implements IState {

    private Logger log;

    public DeathState(){
        initLogger();
    }

    /**
     * initiiert den logger
     */
    private void initLogger(){
        log = Logger.getLogger("EscapeState Logger");
        log.setUseParentHandlers(false);
        log.setLevel(Level.ALL);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        ch.setFormatter(new ColumnFormatter());
        log.addHandler(ch);
    }

    @Override
    public void movement(Monster monster) {
        log.info("Monster befindet sich im Deathstate und wird aus dem Entitycontroller entfernt");
    }
    
}
