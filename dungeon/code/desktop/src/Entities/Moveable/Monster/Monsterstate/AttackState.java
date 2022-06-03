package Entities.Moveable.Monster.Monsterstate;

import Entities.Moveable.Monster.Monster;


/**
 * AttackState eines Monsters. In Diesem State bewegt sich das Monster, sofern es mehr als 50% der maxHp hat und der Held im Raum ist auf diesen zu um ihn zu attackieren.
 */

public class AttackState extends State implements IState {

    public AttackState(){
        super(AttackState.class.getName());
    }

    @Override
    public void movement(Monster monster) {
        if(!this.setBehaviour){
            monster.setMovementBehaviour(monster.getAttackBehaviour() );
            STATELOGGER.info("Monster befindet sich AttackState");
            this.setBehaviour = true;
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
            STATELOGGER.info("Monster verlässt den AttackState und wechselt in den PatrolState.");
            this.setBehaviour = false;
        }else if(hpThresholdEscapeState(monster)){
            monster.setCurrentState(monster.getEscapeState());
            STATELOGGER.info("Monster verlässt den AttackState und wechselt in den EscapeState.");
            this.setBehaviour = false;
        }else if(hpThresholdHelpState(monster)){
            monster.setCurrentState(monster.getHelpState());
            STATELOGGER.info("Monster verlässt den Attackstate und wechselt in den HelpState.");
            this.setBehaviour = false;
        }else if(hpThresholdDeathState(monster)){
            monster.setCurrentState(monster.getDeathState());
            STATELOGGER.info("Monster verlässt den AttackeState und wechselt in den DeathState.");
            this.setBehaviour = false;
        }
    }
}
