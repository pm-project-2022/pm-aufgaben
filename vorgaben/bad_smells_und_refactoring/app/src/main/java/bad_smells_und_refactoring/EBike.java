package bad_smells_und_refactoring;

/**
 * Erbt von Bike und definiert die Klasse genauer
 */

public class EBike extends Bike {
    private Integer batteryCapacity;

    //zusätzlich wird die angabe einer batteriekapazität benötigt
    public EBike(String pn, double p, int ms, int rgc, int fgc, int batteryCapacity) {
        super(pn, p, ms, rgc, fgc, "EBike");
        this.batteryCapacity = batteryCapacity;
    }

    /**
     * setter für die batteriekapazität
     * @param batteryCapacity neuer wert für die batteriekapazität
     */
    public void setBatteryCapacity(Integer batteryCapacity){
        this.batteryCapacity = batteryCapacity;
    }

    /**
     * gibt die batteriekapazität zurück
     * @return batteriekapazität
     */
    public Integer getBatteryCapacity() {
        return this.batteryCapacity;
    }

}

    

