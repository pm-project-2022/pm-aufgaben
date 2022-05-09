package bad_smells_und_refactoring;

public class Article {

    private Bike bike;
    private int purchaseAmount;

    public Article(Bike b, int pa) {
        bike = b;
        purchaseAmount = pa;
    }


    public Bike getBike() {
        return this.bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public int getPurchaseAmount() {
        return this.purchaseAmount;
    }

    public void setPurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }


}
