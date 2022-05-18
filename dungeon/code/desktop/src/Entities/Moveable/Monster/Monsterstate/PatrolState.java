package Entities.Moveable.Monster.Monsterstate;

import Entities.Moveable.Monster.Monster;


/**
 * PatrolState eines Monsters. In Diesem State patrolliert ein Monster entlang der X oder Y Achse.
 */

public class PatrolState extends State implements IState{
    public PatrolState(){
        super(PatrolState.class.getName());
    }


    @Override
    public void movement(Monster monster) {
        if(!this.setBehaviour){
            monster.setMovementBehaviour(monster.getPatrolMovement());
            STATELOGGER.info("Monster befindet sich im PatrolState");
            this.setBehaviour = true;
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
            STATELOGGER.info("Monster verlässt den PatrolState und wechselt in den AggressiveState.");
            this.setBehaviour = false;
        }else if(hpThresholdEscapeState(monster)){
            monster.setCurrentState(monster.getEscapeState());
            STATELOGGER.info("Monster verlässt den PatrolState und wechselt in den EscapeState.");
            this.setBehaviour = false;
        }else if(hpThresholdHelpState(monster)){
            monster.setCurrentState(monster.getHelpState());
            STATELOGGER.info("Monster verlässt den PatrolState und wechselt in den HelpState.");
            this.setBehaviour = false;
        }else if(hpThresholdDeathState(monster)){
            monster.setCurrentState(monster.getDeathState());
            STATELOGGER.info("Monster verlässt den PatrolState und wechselt in den DeathState.");
            this.setBehaviour = false;
        }   
    }
}
