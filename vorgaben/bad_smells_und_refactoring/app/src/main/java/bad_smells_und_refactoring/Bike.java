package bad_smells_und_refactoring;

/**
 * Stellt ein Fahrrad da
 */

public class Bike {
    private String productName;
    private double price;
    private int maxSpeed;
    private int rearGearsCount;
    private int frontGearsCount;
    private final String biketyp;
    

    /**
     * Konstruktor
     * @param productName Bezeichnung des Fahrrads
     * @param price Preis des Fahrrads
     * @param maxSpeed maximale Geschwingidkeit
     * @param rearGearsCount Anzahl der Gänge an der hintere Gangschaltung
     * @param frontGearsCount Anzahl der Gänge an der vordere Gangschaltung
     * @param bikeType Typ des Fahhrads
     */
    public Bike(String productName, double price, int maxSpeed, int rearGearsCount, int frontGearsCount, String bikeType){
        this.productName = productName;
        this.price = price;
        this.maxSpeed = maxSpeed;
        this.rearGearsCount = rearGearsCount;
        this.frontGearsCount = frontGearsCount;
        this.biketyp = bikeType;
    }

    /**
     * getter für den Produktnamen
     * @return Produktnamen
     */
    public String getProductName() {
        return this.productName;
    }

    /**
     * setter für den Produktnamen
     * @param productName neuer Produktnamen
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * getter für den Preis
     * @return Preis
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * setter für den preis
     * @param price neuer preis
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * getter für maxSpeed
     * @return maxspeed
     */
    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    /**
     * setter für maxspeed
     * @param maxSpeed neuer maxspeed
     */
    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * getter für die hintere gangschaltung
     * @return
     */
    public int getRearGearsCount() {
        return this.rearGearsCount;
    }

    /**
     * setter für die hintere ganzschaltung
     * @param rearGearsCount neue anzahl für die hinteren gänge
     */
    public void setRearGearsCount(int rearGearsCount) {
        this.rearGearsCount = rearGearsCount;
    }

    /**
     * getter für die vordere gangschaltung
     * @return anzahl der vorderen gänge
     */
    public int getFrontGearsCount() {
        return this.frontGearsCount;
    }

    /**
     * setter für die vordere Gangschaltung
     * @param frontGearsCount neue anzahl für die forderen Gänge
     */
    public void setFrontGearsCount(int frontGearsCount) {
        this.frontGearsCount = frontGearsCount;
    } 

    /**
     * getter für den biketypen
     * @return biketyp
     */
    public String getBikeType(){
        return this.biketyp;
    }

    /**
     * gibt die gesamtanzahl der gänge zurück
     * @return gesamtanzahl der gänge
     */
    public int getGearsCount() {
        return this.rearGearsCount * this.frontGearsCount;
    }
}
