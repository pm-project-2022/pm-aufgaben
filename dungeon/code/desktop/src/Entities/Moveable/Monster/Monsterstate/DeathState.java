package Entities.Moveable.Monster.Monsterstate;

import Entities.Moveable.Monster.Monster;


/**
 * Deathstate eines Monsters. Befindet sich das Monster in diesen State, ist es tot und wird aus dem Entity-Controller entfernt
 */

public class DeathState extends State implements IState {

    public DeathState(){
        super(DeathState.class.getName());
    }

    @Override
    public void movement(Monster monster) {
        STATELOGGER.info("Monster befindet sich im Deathstate und wird aus dem Entitycontroller entfernt");
    }
    
}
