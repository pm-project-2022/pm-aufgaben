package Entities.Moveable.Monster.Monsterstate;

import Entities.Moveable.Monster.Monster;


/**
 * EscapeState eines Monsters. In Diesem State hört das Monster auf anzugreifen und  flüchtet mit einem unverhersehbaren Movement vor dem Helden. 
 */

public class EscapeState extends State implements IState {

    public EscapeState(){
        super(EscapeState.class.getName());
    }

    @Override
    public void movement(Monster monster) {
        if(!this.setBehaviour){
            monster.setMovementBehaviour(monster.getEscapeBehavior());
            STATELOGGER.info("Monster befindet sich im EscapeState");
            this.setBehaviour = true;
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
            STATELOGGER.info("Monster verlässt den EscapeState und wechselt in den HelpState.");
            this.setBehaviour = false;
        }else if(hpThresholdDeathState(monster)){
            monster.setCurrentState(monster.getDeathState());
            STATELOGGER.info("Monster verlässt den PatrolState und wechselt in den DeathState.");
            this.setBehaviour = false;
        }
    }
    
}
