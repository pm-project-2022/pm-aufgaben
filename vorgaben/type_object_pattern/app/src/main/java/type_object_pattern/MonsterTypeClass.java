package type_object_pattern;

/**
 * Definiert den Typ der Monsterklasse
 */

public class MonsterTypeClass {
    private final String variety;
    private final int xp;
    private final int magic;
    private final String noise;

    public MonsterTypeClass(String variety, int xp, int magic, String noise){
        this.variety = variety;
        this.xp = xp;
        this.magic = magic;
        this.noise = noise;
    }

    public MonsterTypeClass(MonsterTypeClass parent, int xp, int magic){
        this.variety = parent.getVariety();
        this.xp = xp;
        this.magic = magic;
        this.noise = parent.getNoise();
    }

    /**
     * Factory Methode, die ein neues Monster mit den Werten des Monstertypes erstellt.
     * @return neues Monster
     */
    public Monster monFac(){
        return new Monster(this);
    }

    /**
     * Getter für variety
     * @return variety 
     */
    public String getVariety() {
        return this.variety;
    }

    /**
     * getter für xp
     * @return xp
     */
    public int getXp() {
        return this.xp;
    }

    /**
     * getter für magic
     * @return magic
     */
    public int getMagic() {
        return this.magic;
    }

    /**
     * getter für noise
     * @return noise
     */
    public String getNoise() {
        return this.noise;
    }    
}
