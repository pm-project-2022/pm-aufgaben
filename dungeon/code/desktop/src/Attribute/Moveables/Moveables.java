package Attribute.Moveables;

import Attribute.BasicAttributes;

public class Moveables extends BasicAttributes{
    

    /**
     * set attack power
     * @param attackPower new value for attack power
     */
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    /**
     * set defense power
     * @param defensePower new value for defense power
     */
    public void setDefensePower(int defensePower) {
        this.defensePower = defensePower;
    }

    /**
     * set accuracy
     * @param accuracy new value for accuracy
     */
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * set evasion
     * @param evasion new value for evasion
     */
    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }

    /**
     * get current monster or hero level
     * @return current level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * get current monster or hero xp
     * @return current xp
     */
    public int getExp() {
        return this.currentExp;
    }

    /**
     * set movementSpeed
     * @param movementSpeed new value for movementSpeed
     */
    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
}
