package EntityController.Statuswerte;

/**
 * Class to manage the attributes of the hero and the monster
 */

public class StatusValues {
    // Stores the hp and mana of a hero (monster only have hp)
    private int healhtPoints;
    private int manaPoints;

    // Determines the damage and resistance to attacks
    private int strength;
    private int defense;

    // Determines the hitrate of attacks
    private int accuracy;
    private int evasion;

    // Stores the level of the hero and monsters
    private int level;

    // Determines the current experience and the experience you get when killing
    // monsters
    private int exp;

    // Determines the movementspeed of the hero and monsters
    private float movementSpeed;

    // Differs between hero (true) and monsters (false)
    boolean entity;

    /**
     * Stat constructor for heros and monster. if entity is true, the stats are for the hero, else they are for a monster
     * 
     * @param healhtPoints  startamount of hp
     * @param manaPoints    startamount of mana
     * @param strength      startamount of strength
     * @param defense       startamount of defense
     * @param accuracy      startamount of accuracy
     * @param evasion       startamount of evasion
     * @param movementSpeed startamount of movement
     */
    public StatusValues(boolean entity, int healhtPoints, int manaPoints, int strength, int defense, int accuracy,
            int evasion, int level, int exp, float movementSpeed) {
        this.entity = entity;
        if (this.entity) {
            this.healhtPoints = healhtPoints;
            this.manaPoints = manaPoints;
            this.strength = strength;
            this.defense = defense;
            this.accuracy = accuracy;
            this.evasion = evasion;
            this.exp = exp * level;
            this.movementSpeed = movementSpeed;
        } else {
            this.healhtPoints = healhtPoints * level;
            this.manaPoints = manaPoints * level;
            this.strength = strength * level;
            this.defense = defense * level;
            this.accuracy = accuracy * level;
            this.evasion = evasion * level;
            this.exp = exp * level;
            this.movementSpeed = movementSpeed;
        }
    }

    /**
     * getter for hp
     * @return hp
     */
    public int getHealhtPoints() {
        return this.healhtPoints;
    }

    /**
     * setter for hp
     * @param healhtPoints hp amount
     */
    public void setHealhtPoints(int healhtPoints) {
        this.healhtPoints = healhtPoints;
    }

    /**
     * getter for manapoints
     * @return manapoints
     */
    public int getManaPoints() {
        return this.manaPoints;
    }

    /**
     * setter for mana points
     * @param manaPoints amount of mana points
     */
    public void setManaPoints(int manaPoints) {
        this.manaPoints = manaPoints;
    }

    /**
     * getter for strength
     * @return strength
     */
    public int getStrength() {
        return this.strength;
    }

    /**
     * setter for strength
     * @param strength amount of strength
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * getter for defense
     * @return defense
     */
    public int getdefense() {
        return this.defense;
    }

    /**
     * setter fpr defense
     * @param defense amount of defense
     */
    public void setdefense(int defense) {
        this.defense = defense;
    }

    /**
     * getter for accuracy
     * @return accuracy
     */
    public int getAccuracy() {
        return this.accuracy;
    }

    /**
     * setter for accuracy
     * @param accuracy amount of accuracy
     */
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * getter for evasion
     * @return evasion
     */
    public int getEvasion() {
        return this.evasion;
    }

    /**
     * setter for evasion
     * @param evasion amount of evasion
     */
    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }

    /**
     * getter for exp
     * @return exp
     */
    public int getExp() {
        return this.exp;
    }

    /**
     * setter for exp
     * @param exp amount of exp
     */
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * getter for level
     * @return level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * setter for level
     * @param level level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * getter movementspeed
     * @return movementSpeed
     */
    public float getMovementspeed() {
        return this.movementSpeed;
    }

    /**
     * setter for movementSpeed
     * @param movementSpeed amount of movementSpeed
     */
    public void setMovementspeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    @Override
    public String toString() {
        return "HP: " + this.healhtPoints + "\nMANA: " + this.manaPoints + "\nStrength: " + this.strength
                + "\nDefenses: " + this.defense + "\nAccuracy" + this.accuracy + "\nEvasion"
                + this.evasion + "\nLEVEL: " + this.level + "\nEXP: " + this.exp + "\n";
    }
}
