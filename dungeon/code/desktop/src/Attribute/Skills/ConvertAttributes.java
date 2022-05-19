package Attribute.Skills;

import Attribute.BasicAttributes;

public class ConvertAttributes extends BasicAttributes {
    private final int cooldown;
    private final int mana;
    private final int hp;
    private final int skillLevel;
    private final float movementSpeed;


    public ConvertAttributes(int hp, int mana, int skillLevel,int cooldown, float movementSpeed){
        this.hp = hp;
        this.mana = mana; 
        this.skillLevel = skillLevel;
        this.cooldown = cooldown;
        this.movementSpeed = movementSpeed;

    }


    public int getCooldown() {
        return this.cooldown;
    }


    public int getMana() {
        return this.mana;
    }


    public int getHp() {
        return this.hp;
    }


    public int getSkillLevel() {
        return this.skillLevel;
    }


    public float getMovementSpeed() {
        return this.movementSpeed;
    }



}
