package Inventory;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import Entities.Items.Item;
import Entities.Moveable.Hero.Hero;

/**
 * Inventory of the hero
 */

public class Inventory {
    // initiates inventory
    private Item[] inventory;
    private int[] amount;
    private int elements;
    private Item activeItem;
    Logger log;

    public Inventory() {
        inventory = new Item[3];
        amount = new int[3];
        this.elements = 0;
        initLogger();
    }

    /**
     * initiates the logger
     */
    private void initLogger() {
        log = Logger.getLogger("Inventar");
        log.setUseParentHandlers(false);
        log.setLevel(Level.ALL);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.ALL);
        ch.setFormatter(new ColumnFormatter());
        log.addHandler(ch);
    }

    /**
     * add items to inventory
     *
     * @param item item added to inventory
     * @return return true if added, false it not
     */
    public boolean addItem(Item item) {
        if (this.elements < 3 || isItemAlreadyInInventory(item)) {
            for (int i = 0; i < inventory.length; i++) {
                if (this.inventory[i] == null) {
                    this.inventory[i] = item;
                    this.amount[i] = 1;
                    log.info("\nItem added: " + item.getItemName() + "\nAmount: " + amount[i]);
                    this.elements++;
                    return true;
                } else if (this.inventory[i].getItemName() == item.getItemName()) {
                    this.amount[i] += 1;
                    item.setRemoveOrConsume(true);
                    log.info("\nItem added: " + item.getItemName() + "\nAmount: " + this.amount[i]);
                    return true;
                }
            }
        } else {
            log.warning("Inventory is full");
            return false;
        }
        return false;
    }

    /**
     * checks if item is already in inventar
     *
     * @param item item checked
     * @return true if already in inventar, else if
     */
    private boolean isItemAlreadyInInventory(Item item) {
        for (int i = 0; i < this.inventory.length; i++) {
            if (this.inventory[i] != null && this.inventory[i].getItemName().equals(item.getItemName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * equips an item in the inventar
     *
     * @param i index of the inventory array
     */
    public void equipItem(int i) {
        if (this.inventory[i] != null) {
            for (Item item : inventory) {
                if (item != null) {
                    item.setEquipped(false);
                }
            }
            this.inventory[i].setEquipped(true);
            log.info("Item equipped: " + this.inventory[i].getItemName());
        }
    }

    /**
     * drop item from inventory
     *
     * @return true if successful, false otherwise
     */
    public boolean dropItem() {
        for (int i = 0; i < this.inventory.length; i++) {
            if (this.inventory[i] != null && this.inventory[i].getIsEquipped()) {
                log.info("Item dropped: " +  this.inventory[i].getItemName());
                this.inventory[i].setRemoveOrConsume(true);
                this.inventory[i] = null;
                this.amount[i] = 0;
                this.elements--;
                return true;
            }
        }
        return false;
    }

    /**
     * consumes a item from the inventory
     *
     * @param hero current hero
     */
    public void consumeItem(Hero hero) {
        for (int i = 0; i < this.inventory.length; i++) {
            if (this.inventory[i] != null && this.inventory[i].getIsEquipped() && this.amount[i] > 0) {
                if (this.amount[i] == 1) {
                    giveStats(hero, i);
                    this.inventory[i].setRemoveOrConsume(true);
                    log.info("Item benutzt: " + this.inventory[i].getItemName());
                    this.inventory[i] = null;
                    this.elements--;
                } else {
                    giveStats(hero, i);
                }

                this.amount[i] -= 1;
            }
        }

    }

    /**
     * give stats to hero when consume an item
     *
     * @param hero  current hero
     * @param index consumed item
     */
    private void giveStats(Hero hero, int index) {
        if (this.inventory[index].getItemName().equals("Health Potion")) {
            int hp = this.inventory[index].getAttributes().getCurrentHP() + hero.getAttributes().getCurrentHP();
            if (hp > hero.getAttributes().getMaxHP()) {
                hero.getAttributes().setCurrentHP(hero.getAttributes().getMaxHP());
            } else {
                hero.getAttributes().setCurrentHP(hp);
            }
        } else if (this.inventory[index].getItemName().equals("Mana Potion")) {
            int mana = this.inventory[index].getAttributes().getCurrentMana() + hero.getAttributes().getCurrentMana();
            if (mana > hero.getAttributes().getMaxMana()) {
                hero.getAttributes().setCurrentMana(hero.getAttributes().getMaxMana());
            } else {
                hero.getAttributes().setCurrentMana(mana);
            }
        } else if (this.inventory[index].getItemName().equals("Strength Orb")) {
            hero.getAttributes().setAttackPower(hero.getAttributes().getAttackPower() + this.inventory[index].getAttributes().getAttackPower());
        } else if (this.inventory[index].getItemName().equals("Mana Orb")) {
            hero.getAttributes().setMaxMana(hero.getAttributes().getMaxMana() + this.inventory[index].getAttributes().getCurrentMana());
        }
    }

    /**
     * logs inventory
     */
    public void showInventory() {
        for (int i = 0; i < this.inventory.length; i++) {
            if (this.inventory[i] != null) {
                log.info("\nItem added: " + this.inventory[i].getItemName() + "\nAmount: " + amount[i]);
            } else {
                log.info("Leer");
            }
        }
    }

    /**
     * checks if inventory is empty
     *
     * @return true if inventory is empty, false otherwise
     */
    public boolean isEmpty() {
        if (this.elements == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * getter fpr the inventory
     *
     * @return inventory
     */
    public Item[] getInventory() {
        return this.inventory;
    }

    /**
     * gets active item
     *
     * @return returns active item
     */
    public Item getActiveItem() {
        return activeItem;
    }

    /**
     * sets active item
     *
     * @param item item thats active
     */
    public void setActiveItem(Item item) {
        this.activeItem = item;
    }
}
