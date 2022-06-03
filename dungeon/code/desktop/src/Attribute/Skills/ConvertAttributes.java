package Attribute.Skills;

import Attribute.BasicAttributes;

/**
 * /**
 * Attribute Klasse für den convert Ressources skill
 */

public class ConvertAttributes extends BasicAttributes {
    private final int cooldown;
    private final int mana;
    private final int hp;
    private final int skillLevel;
    private final float movementSpeed;
    private final int duration;

    public ConvertAttributes(int hp, int mana, int skillLevel, int cooldown) {
        this.hp = hp;
        this.mana = mana;
        this.skillLevel = skillLevel;
        this.cooldown = cooldown;
        this.movementSpeed = 0;
        this.duration = 0;
    }

    public ConvertAttributes(int hp, int mana, int skillLevel, int cooldown, float movementSpeed, int duration) {
        this.hp = hp;
        this.mana = mana;
        this.skillLevel = skillLevel;
        this.cooldown = cooldown;
        this.movementSpeed = movementSpeed;
        this.duration = duration;

    }

    /**
     * getter für den cooldown
     * 
     * @return cooldown
     */
    public int getCooldown() {
        return this.cooldown;
    }

    /**
     * getter für das mana
     * 
     * @return mana
     */
    public int getMana() {
        return this.mana;
    }

    /**
     * getter für die hp
     * 
     * @return hp
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * getter für das skill level
     * 
     * @return skill level
     */
    public int getSkillLevel() {
        return this.skillLevel;
    }

    /**
     * getter für den movementspeed
     */
    public float getMovementSpeed() {
        return this.movementSpeed;
    }

    /**
     * getter für die duration
     * 
     * @return duration
     */
    public int getDuration() {
        return this.duration;
    }

}
