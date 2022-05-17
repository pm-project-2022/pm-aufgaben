package type_object_pattern;

public enum MonsterTypesEnum {
    RAT("Rat", 20, 20, "piep"), 
    KERBEROS("Kerberos", 1000, 500, "grr"), 
    HELLCAT("Hellcat", 500, 250, "fauch");

    private final String variety;
    private final int xp;
    private final int magic;
    private final String noise;

    MonsterTypesEnum(String variety, int xp, int magic, String noise){
        this.variety = variety;
        this.xp = xp;
        this.magic = magic;
        this.noise = noise;
    }

    public String getVariety() {
        return variety;
    }

    public int getXp() {
        return xp;
    }

    public int getMagic() {
        return magic;
    }

    public String getNoise() {
        return noise;
    }
}
