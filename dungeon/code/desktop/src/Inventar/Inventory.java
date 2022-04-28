package Inventar;

import Items.Item;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Inventory {
    //initiates inventory
    private final int storageSize = 3;
    private final Item[] items = new Item[storageSize];
    private Item activeItem;

    //initiates counter and boolean for inventory
    private boolean full = false;
    private int itemCounter = 0;

    //manages logger
    Logger log;

    public Inventory(){
        initLogger();
    }

    /**
     * add items to inventory
     * @param item item added to inventory
     * @return return true if added, false it not
     */
    public boolean add(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                log.info("Item added: " + item.getName());
                itemCounter++;
                if(itemCounter==3){
                    full = true;
                }
                return true;
            }
        }
        log.warning("Inventory full!");
        return false;
    }

    /**
     * removes item from inventory
     * @param item item removed from inventory
     */
    public void removeItem(Item item) {
        if(item.getName().equals("Sword")){
            log.warning("Standarditem kann nicht gedroppt werden");
            return;
        }
        for (int i = 0; i < items.length; i++) {
            if (items[i] == item) {
                log.info("Gedroppt/Benutzt: " + item.getName());
                items[i] = null;
                itemCounter--;
                full = false;
                return;
            }
        }
    }

    /**
     * logs inventory
     */
    public void showInventory(){
        for (Item item : items) {
            if (item != null) {
                log.info("Item: "+ item.getName());
            } else {
                log.info("Leer");
            }

        }
    }

    /**
     * equips item on character
     * @param i equip at item slot
     */
    public void equipped(int i){
        if(i-1<items.length){
            if(items[i-1]!=null){
                activeItem = items[i-1];
                log.info("Equipped: " + activeItem.getName());
            }
        }
    }

    /**
     * initiates the logger
     */
    private void initLogger(){
        log = Logger.getLogger("Inventar");
        log.setUseParentHandlers(false);
        log.setLevel(Level.ALL);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        ch.setFormatter(new ColumnFormatter());
        log.addHandler(ch);
    }

    /**
     * inventory full or not
     * @return return true or false
     */
    public boolean isFull(){
        return full;
    }

    /**
     * gets active item
     * @return returns active item
     */
    public Item getActiveItem(){
        return activeItem;
    }

    /**
     * sets active item
     * @param item item thats active
     */
    public void setActiveItem(Item item){
        this.activeItem = item;
    }


}
