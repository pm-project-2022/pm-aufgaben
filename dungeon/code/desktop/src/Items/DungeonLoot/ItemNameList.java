package Items.DungeonLoot;

import java.util.ArrayList;

public abstract class ItemNameList {
    protected ArrayList<String> itemList;

    /**
     * superclass for item name list
     */
    public ItemNameList(){
        this.itemList = new ArrayList<>();
    }

    /**
     * getter for itemlist
     * @return returns itemlist
     */
    public ArrayList<String> getItemList(){
        return this.itemList;
    }
}
