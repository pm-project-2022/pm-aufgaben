package bad_smells_und_refactoring;

/**
 * Bündelt die Bike Auswahl und die Anzahl der Bikes
 */

public class Article {

    private Bike bike;
    private int purchaseAmount;

    /**
     * Konstruktor
     * @param bike ausgewähltes bike-modell des kunden
     * @param purchaseAmount Anzahl der bike-modelle die der kunde kaufen will
     */

    public Article(Bike bike, int purchaseAmount) {
        this.bike = bike;
        this.purchaseAmount = purchaseAmount;
    }

    /**
     * Getter für das bike
     * @return das ausgewählte bike 
     */
    public Bike getBike() {
        return this.bike;
    }

    /**
     * Setter für das bike
     * @param bike ersetzt das bestehende bike durch ein neues
     */
    public void setBike(Bike bike) {
        this.bike = bike;
    }

    /**
     * getter für die anzahl der gekauften bikes
     * @return anzahl der gekauften bikes 
     */
    public int getPurchaseAmount() {
        return this.purchaseAmount;
    }

    /**
     * Setter für die anzahl der gekauften bikes
     * @param purchaseAmount ändert die anzahl der gekauften bikes
     */
    public void setPurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }


}
