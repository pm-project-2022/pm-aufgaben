package Entities.Moveable.Monster.Monsterstate;

import Entities.Moveable.Monster.Monster;


/**
 * HelpState eines Monsters. Befindet sich ein Monster in diesem State, ist es zu schwach um sich bewegen oder zu verteidigen. Mit letzter Kraft versucht es nach Hilfe zu rufen.
 */

public class HelpState extends State implements IState {

    public HelpState(){
        super(HelpState.class.getName());
    }

    @Override
    public void movement(Monster monster) {
        if(!this.setBehaviour){
            monster.setMovementBehaviour(monster.getHelpBehaviour());
            STATELOGGER.info("Monster befindet sich im HelpState");
            this.setBehaviour = true;
        }
        exit(monster);
    }

    private void exit(Monster monster){
        if(hpThresholdDeathState(monster)){
            monster.setCurrentState(monster.getDeathState());
            STATELOGGER.info("Monster verlässt den HelpState und wechselt in den DeathState.");
            this.setBehaviour = false;
        }
    }
    
}
