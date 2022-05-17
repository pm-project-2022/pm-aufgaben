package type_object_pattern;

/**
 * Die Klasse stellt ein Monster da, das während der Lebensdauer die Xp
 * eigenständig verwaltet. Die Werte für variety, magic und noise befinden sich
 * in der MosterType-Klasse, da diese während der Lebensdauer des Monsters
 * konstant sein sollen.
 */

public class Monster implements IMonster {
    private MonsterTypeClass monsterType;
    private int xp;

    public Monster(MonsterTypeClass monsterType) {
        this.monsterType = monsterType;
        this.xp = this.monsterType.getXp();
    }

    public Monster(MonsterTypesEnum monsterTypesEnum) {
        switch (monsterTypesEnum) {
            case RAT:
                this.monsterType = new MonsterTypeClass(monsterTypesEnum.getVariety(), monsterTypesEnum.getXp(),
                        monsterTypesEnum.getMagic(), monsterTypesEnum.getNoise());
                break;
            case KERBEROS:
                this.monsterType = new MonsterTypeClass(monsterTypesEnum.getVariety(), monsterTypesEnum.getXp(),
                        monsterTypesEnum.getMagic(), monsterTypesEnum.getNoise());
                break;
            case HELLCAT:
                this.monsterType = new MonsterTypeClass(monsterTypesEnum.getVariety(), monsterTypesEnum.getXp(),
                        monsterTypesEnum.getMagic(), monsterTypesEnum.getNoise());
                break;
            default:
                this.monsterType = null;
                break;
        }
        this.xp = this.monsterType.getXp();
    }

    /**
     * gibt den typen eines monsters zurück
     * 
     * @return monstertyp
     */
    public MonsterTypeClass getMonsterType() {
        return this.monsterType;
    }

    @Override
    public String getVariety() {
        return this.monsterType.getVariety();
    }

    @Override
    public int getXp() {
        return this.xp;
    }

    @Override
    public int getMagic() {
        return this.monsterType.getXp();
    }

    @Override
    public String makeNoise() {
        return this.monsterType.getNoise();
    }

    @Override
    public String toString() {
        return "\nVariety: " + this.monsterType.getVariety() + "\nXP: " + this.xp + "\nMagic: "
                + this.monsterType.getMagic() + "\nNoise: " + this.monsterType.getNoise();
    }

}
