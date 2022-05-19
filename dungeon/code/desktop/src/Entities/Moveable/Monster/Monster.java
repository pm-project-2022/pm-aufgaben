package Entities.Moveable.Monster;

import Entities.Moveable.Hero.Hero;
import Entities.Moveable.Monster.MonsterMovement.IMovement;
import Entities.Moveable.Monster.MonsterMovement.ComplexBehaviour.AggressiveMovement;
import Entities.Moveable.Monster.MonsterMovement.ComplexBehaviour.Escape;
import Entities.Moveable.Monster.MonsterMovement.ComplexBehaviour.Help;
import Entities.Moveable.Monster.MonsterMovement.SimpleMonsterMovement.Idle;
import Entities.Moveable.Monster.Monsterstate.AttackState;
import Entities.Moveable.Monster.Monsterstate.DeathState;
import Entities.Moveable.Monster.Monsterstate.EscapeState;
import Entities.Moveable.Monster.Monsterstate.HelpState;
import Entities.Moveable.Monster.Monsterstate.IState;
import Entities.Moveable.Monster.Monsterstate.PatrolState;
import Entities.Moveable.Moveable;
import Helper.PointBooleanTransmitter;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

/**
 * Verwaltet die allgemeinen Attribute und Eigenschaften der monster
 */

public class Monster extends Moveable {
    private Hero hero;

    //arraylist mit allen monster auf der ebene
    private ArrayList<Monster> monster;

    //monster verhalten
    private IMovement movementBehaviour;
    private IMovement patrolBehaviour;
    private IMovement attackBehaviour;
    private IMovement escapeBehavior;
    private IMovement helpBehaviour;
    
    //monster states
    private IState currentState;
    private IState patrolState;
    private IState attackState;
    private IState escapeState;
    private IState helpState;
    private IState deathState;

    //hilfsattribute um die position und animationen des monsters zu setzen
    private PointBooleanTransmitter pointBooleanTransmitter;
    

    public Monster(Painter painter, SpriteBatch batch, Hero hero, IMovement movementBehaviour) {
        super(painter, batch);
        this.hero = hero;

        //monster behaviour
        this.movementBehaviour = movementBehaviour;
        this.patrolBehaviour = movementBehaviour;
        this.attackBehaviour = new AggressiveMovement();
        this.escapeBehavior = new Escape();
        this.helpBehaviour = new Help(); 

        //monster states
        this.patrolState = new PatrolState();
        this.attackState = new AttackState();
        this.escapeState = new EscapeState();
        this.helpState = new HelpState();
        this.deathState = new DeathState();
        this.currentState = this.patrolState;
    }

    @Override
    public void update() {
        if (!this.hero.getHeroDead()) {
            this.currentState.movement(this);
            if (!(this.currentState == this.deathState)) {
                this.pointBooleanTransmitter = this.movementBehaviour.getMonsterMovement(this);
                this.currentPosition = this.pointBooleanTransmitter.getPoint();
                updateAnimations();
            }
        }
    }

    /**
     * verwaltet die animationen je nach state und behaviour
     */
    private void updateAnimations() {

        if (this.pointBooleanTransmitter.getCollision()) {
            this.movementBehaviour = new Idle(this.pointBooleanTransmitter.getRunDirection()) {

            };

            if (this.pointBooleanTransmitter.getRunDirection()) {
                this.activeAnimation = this.idleAnimation;
            } else {
                this.activeAnimation = this.idleMirroredAnimation;
            }

            return;
        }

        if (pointBooleanTransmitter.getRunDirection()) {
            this.activeAnimation = this.runAnimation;
        } else {
            this.activeAnimation = this.runMirroredAnimation;
        }
    }

    @Override
    public boolean removable() {
        if (this.currentState == this.deathState) {
            this.hero.getAttributes().setExp(this.attributes.getExp() + this.hero.getAttributes().getExp());
            return true;
        } else {
            return false;
        }
    }

    /**
     * getter für den hero
     * @return current hero
     */
    public Hero getHero() {
        return this.hero;
    }

    /**
     * liste enthält alle monster der ebene
     * @param monster arrayliste aller monster
     */
    public void setMonster(ArrayList<Monster> monster) {
        this.monster = monster;
    }

    /**
     * getter für die monsterliste
     * @return monsterliste mit allen monster der ebene
     */
    public ArrayList<Monster> getMonster() {
        return this.monster;
    }

    /**
     * setzt ein neues verhalten für das monster
     * @param movementBehaviour neues monsterverhalten
     */
    public void setMovementBehaviour(IMovement movementBehaviour) {
        this.movementBehaviour = movementBehaviour;
    }

    /**
     * getter für das monsterverhalten
     * @return monsterverhalten
     */
    public IMovement getMovementBehaviour() {
        return movementBehaviour;
    }

    /**
     * getter für das patrollierverhalten
     * @return patrollierverhalten
     */
    public IMovement getPatrolMovement() {
        return this.patrolBehaviour;
    }

    /**
     * getter für das angriffverhalten
     * @return angriffverhalten
     */
    public IMovement getAttackBehaviour() {
        return this.attackBehaviour;
    }

    /**
     * getter für das fluchtverhalten
     * @return fluchtverhalten
     */
    public IMovement getEscapeBehavior() {
        return this.escapeBehavior;
    }

    /**
     * getter für das hilfeverhalten
     * @return hilfeverhalten
     */
    public IMovement getHelpBehaviour() {
        return this.helpBehaviour;
    }

    /**
     * setzt einen neuen monsterstate
     * @param currentState neuer monsterstate
     */
    public void setCurrentState(IState currentState) {
        this.currentState = currentState;
    }

    /**
     * getter für den patrollierstate
     * @return patrollierstate
     */
    public IState getPatrolState() {
        return this.patrolState;
    }

    /**
     * getter für den angriffstate
     * @return angriffstate
     */
    public IState getAttackState() {
        return this.attackState;
    }

    /**
     * getter für den fluchtstate
     * @return fluchtstate
     */
    public IState getEscapeState() {
        return this.escapeState;
    }

    /**
     * getter für hilfestate
     * @return hilfestate
     */
    public IState getHelpState() {
        return this.helpState;
    }

    /**
     * getter für den todstate
     * @return todstate
     */
    public IState getDeathState() {
        return this.deathState;
    }

    /**
     * setzt die aktuelle ebene und platziert das monster on level-load an eine random stelle der ebene 
     * @param currentFloor aktuelle ebene
     */
    public void setLevel(Level currentFloor) {
        this.currentFloor = currentFloor;
        this.currentPosition = this.currentFloor.getRandomRoom().getRandomFloorTile().getCoordinate().toPoint();
    }

    /**
     * setzt eine neue position des monster
     * @param point neue position
     */
    public void setCurrentPosition(Point point) {
        this.currentPosition = point;
    }

    @Override
    public Animation getActiveAnimation() {
        return this.activeAnimation;
    }

    @Override
    public Point getPosition() {
        return this.currentPosition;
    }   

}
