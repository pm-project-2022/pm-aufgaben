package bad_smells_und_refactoring;

public class Bike {
    private String productName;
    private double price;
    private int maxSpeed;
    private int rearGearsCount;
    private int frontGearsCount;
    private final String biketyp;
    

    public Bike(String pn, double p, int ms, int rgc, int fgc, String bikeType){
        this.productName = pn;
        this.price = p;
        this.maxSpeed = ms;
        this.rearGearsCount = rgc;
        this.frontGearsCount = fgc;
        this.biketyp = bikeType;
    }


    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getRearGearsCount() {
        return this.rearGearsCount;
    }

    public void setRearGearsCount(int rearGearsCount) {
        this.rearGearsCount = rearGearsCount;
    }

    public int getFrontGearsCount() {
        return this.frontGearsCount;
    }

    public void setFrontGearsCount(int frontGearsCount) {
        this.frontGearsCount = frontGearsCount;
    } 

    public String getBikeType(){
        return this.biketyp;
    }

    public int getGearsCount() {
        return this.rearGearsCount * this.frontGearsCount;
    }
}
