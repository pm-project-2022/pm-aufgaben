package EntityController.Statuswerte;

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
     * Stat constructor for heros and monster
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

    public int getHealhtPoints() {
        return this.healhtPoints;
    }

    public void setHealhtPoints(int healhtPoints) {
        this.healhtPoints = healhtPoints;
    }

    public int getManaPoints() {
        return this.manaPoints;
    }

    public void setManaPoints(int manaPoints) {
        this.manaPoints = manaPoints;
    }

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getdefense() {
        return this.defense;
    }

    public void setdefense(int defense) {
        this.defense = defense;
    }

    public int getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getEvasion() {
        return this.evasion;
    }

    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }

    public int getExp() {
        return this.exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getMovementspeed() {
        return this.movementSpeed;
    }

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
