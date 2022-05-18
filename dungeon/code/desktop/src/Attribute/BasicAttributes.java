package Attribute;

import desktop.MyGame;

public abstract class BasicAttributes {
    // Manages current hp for heroes, items and monsters
    protected int currentHP;

    // Manages max hp for heroes
    protected int maxHP;

    // Manages current mana for heroes and items
    protected int currentMana;

    // manages max mana for heroes
    protected int maxMana;
    protected int auraMana;

    // Determines the damage of attacks and resistance to attacks for heroes,
    // monster and items
    protected int attackPower;
    protected int auraAttackPower;
    protected int defensePower;
    protected int auraDefensePower;

    // Determines the accuracy and evasion for heroes, monster and items
    protected int accuracy;
    protected int evasion;
    protected int auraEvasion;

    // Manages the level of the hero and monsters
    protected int level;

    // Stores the current experience of the heroes and the experience you get when
    // killing monsters
    protected int currentExp;

    // Determines how much exp the heroes need for the next level up
    protected int expForLvlUp;

    // Determines the movementspeed of the hero and monsters
    protected float movementSpeed;

    /**
     * get current hp
     *
     * @return current hp
     */
    public int getCurrentHP() {
        return this.currentHP;
    }

    /**
     * set current hp
     * @param currentHP new value for current hp
     */
    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    /**
     * get current mana
     *
     * @return current mana
     */
    public int getCurrentMana() {
        return this.currentMana;
    }

    /**
     * get current attackpower
     *
     * @return current attackpower
     */
    public int getAttackPower() {
        return this.attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    /**
     * getter für aura attackpower
     * @return auraattackpower
     */
    public int getAuraAttackPower() {
        return this.auraAttackPower;
    }

    /**
     * setter für aura attackpower
     * @param auraAttackPower new auraattackpower
     */
    public void setAuraAttackPower(int auraAttackPower) {
        this.auraAttackPower = auraAttackPower;
    }


    /**
     * get current defense power
     *
     * @return current defense power
     */
    public int getDefensePower() {
        return this.defensePower;
    }

    public void setDefensePower(int defensePower) {
        this.defensePower = defensePower;
    }

    /**
     * get current accuracy
     *
     * @return current accuracy
     */
    public int getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * getter für aura defense power
     * @return aura defense power
     */
    public int getAuraDefensePower() {
        return this.auraDefensePower;
    }

    /**
     * setter für aura defense power
     * @param auraDefensePower neue aura defense power
     */
    public void setAuraDefensePower(int auraDefensePower) {
        this.auraDefensePower = auraDefensePower;
    }

    /**
     * get current evasion
     *
     * @return current evasion
     */
    public int getEvasion() {
        return this.evasion;
    }

    public void setEvasion(int evasion){
        this.evasion = evasion;
    }

    /**
     * getter für aura evasion
     * @return aura evasion
     */
    public int getAuraEvasion() {
        return this.auraEvasion;
    }

    /**
     * setter für aura evasion
     * @param auraEvasion neue aura evasion
     */
    public void setAuraEvasion(int auraEvasion) {
        this.auraEvasion = auraEvasion;
    }

    /**
     * get movementspeed of the monster or hero
     *
     * @return
     */
    public float getMovementSpeed() {
        return this.movementSpeed;
    }

    /**
     * get max hp
     *
     * @return max hp
     */
    public int getMaxHP() {
        return this.maxHP;
    }

    /**
     * set max hp
     *
     * @param maxHP new value for max hp
     */
    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    /**
     * set current mana
     *
     * @param currentMana new value for current mana
     */
    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    /**
     * get max mana
     *
     * @return current mana
     */
    public int getMaxMana() {
        return this.maxMana;
    }

    /**
     * set max hp
     *
     * @param maxMana new value for max hp
     */
    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    /**
     * set mana when aura switches states
     * @param auraMana new value for aura mana
     */
    public void setAuraMana(int auraMana) {
        this.auraMana = auraMana;
    }
    
    /**
     * get aura mana
     * @return aura mana;
     */
    public int getAuraMana() {
        return this.auraMana;
    }

    /**
     * set new level
     *
     * @param level new level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * set current xp
     *
     * @param exp new value for current xp
     */
    public void setExp(int exp) {
        this.currentExp = exp;
    }

    /**
     * get current xp for level up
     *
     * @return current xp
     */
    public int getExpForLvlUp() {
        return this.expForLvlUp;
    }

    /**
     * set xp for level up
     *
     * @param expForLvlUp new value for level up xp
     */
    public void setExpForLvlUp(int expForLvlUp) {
        this.expForLvlUp = expForLvlUp;
    }

    public int getLevel(){
        return level;
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

    public void updateHeroLevelAndStats(){
        this.level += 1;
        this.maxHP +=20;
        this.maxMana +=20;
        this.auraMana += 20;
        this.attackPower += 5;
        this.auraAttackPower += 5;
        this.defensePower += 5;
        this.auraDefensePower += 5;
        this.accuracy += 5;
        this.evasion += 5;
        this.auraEvasion += 5;
        this.currentExp = this.currentExp - this.expForLvlUp;
        this.expForLvlUp *= 2;
        MyGame.lvlUp.play(0.1f);
    }

    @Override
    public String toString() {
        return "HP: " + this.currentHP + "\nMaxHP: " + this.maxHP +  "\nMANA: " + this.currentMana + "\nMaxMana: " + this.maxMana + "\nAttackpower: " + this.attackPower
                + "\nDefenses: " + this.defensePower + "\nAccuracy" + this.accuracy + "\nEvasion"
                + this.evasion + "\nLEVEL: " + this.level + "\nEXP: " + this.currentExp + "\n";
    }

}
