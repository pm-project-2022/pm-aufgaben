package EntityController.Monster;

import java.util.Random;


public abstract class MonsterQuantity {
    protected int quantity;
    protected int monsterQuantityMin;
    protected int monsterQuantityMax;
    protected Random random;

    public MonsterQuantity(){
        this.random = new Random();
    }

    public int getQuantity(){
        return this.quantity;
    }

}
