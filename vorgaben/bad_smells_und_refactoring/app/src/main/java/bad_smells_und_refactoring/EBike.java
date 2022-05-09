package bad_smells_und_refactoring;

public class EBike extends Bike {
    private Integer batteryCapacity;

    public EBike(String pn, double p, int ms, int rgc, int fgc, int batteryCapacity) {
        super(pn, p, ms, rgc, fgc);
        this.batteryCapacity = batteryCapacity;
    }

    public void setBatteryCapacity(Integer batteryCapacity){
        this.batteryCapacity = batteryCapacity;
    }

    public Integer getBatteryCapacity() {
        return this.batteryCapacity;
    }

}

    

