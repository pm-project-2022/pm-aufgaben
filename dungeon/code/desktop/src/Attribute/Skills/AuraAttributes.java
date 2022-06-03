package Attribute.Skills;

import Attribute.BasicAttributes;

/**
 * Attribute Klasse für die Auraskills
 */

public class AuraAttributes extends BasicAttributes {
    private final int cooldown;
    private final int manaStorage;
    private final int auraLevel;
    private boolean isActive;

    public AuraAttributes(int manaStorage, int cooldown, int level){
        this.manaStorage = manaStorage;
        this.cooldown = cooldown;
        this.auraLevel = level;
        this.isActive = false;
    }

    /**
     * getter für den cooldown des skills
     * @return cooldown
     */
    public int getCooldown() {
        return this.cooldown;
    }

    /**
     * getter für den mana storage
     * @return
     */
    public int getManaStorage() {
        return this.manaStorage;
    }

    /**
     * getter für das aura level
     * @return auralevel
     */
    public int getAuraLevel() {
        return this.auraLevel;
    }

    /**
     * setter für isActive
     * @param isActive true wenn die aura aktiv ist und false wenn sie deaktiviert wurde
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * getter für isActive
     * @return isActive
     */
    public boolean getActive(){
        return this.isActive;
    }
}
