package EntityController.Monster;

import java.util.Random;

/**
 * Abstract class to get random monster quantity of a level
 */

public abstract class MonsterQuantity {
    //monster quantity
    protected int quantity;

    //min and max monster quantity for each level
    protected int monsterQuantityMin;
    protected int monsterQuantityMax;

    //radomizer for the monster quantity
    protected Random random;

    /**
     * Construcor
     */
    public MonsterQuantity(){
        this.random = new Random();
    }

    /**
     * getter for the monster quanity
     * @return monster quantyty
     */
    public int getQuantity(){
        return this.quantity;
    }

}
