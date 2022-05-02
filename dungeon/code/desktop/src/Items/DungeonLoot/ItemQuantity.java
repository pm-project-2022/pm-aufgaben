package Items.DungeonLoot;

import java.util.Random;

public abstract class ItemQuantity {

    protected int quantity;
    protected Random random;

    /**
     * superclass for item quantity
     */
    public ItemQuantity(){
        this.random = new Random();
    }

    /**
     * getter for itemquantity
     * @return returns itemquantity
     */
    public int getQuantity(){
        return this.quantity;
    }
}
