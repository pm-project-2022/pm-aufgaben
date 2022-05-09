package bad_smells_und_refactoring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class ArticleTest {
    private Article article;

    @Before
    public void setUp(){
        this.article = new Article(new Bike("Bike1", 200.99, 20 ,3,1,"Bike"), 1);
    }

    /**
     * Lässt sich ein Artikel erstellen
     */
    @Test
    public void getArticle(){
        assertNotNull(this.article);
    }

    /**
     * Lässt sich das Fahrad zurückgeben
     */
    @Test
    public void getBike(){
        assertNotNull(this.article.getBike());
    }

    /**
     * Lässt sich ein Fahrrad bearbeiten 
     */
    @Test
    public void setBike(){
        Bike oldBike = this.article.getBike();
        this.article.setBike(new Bike("Bike2", 200.99, 20 ,3,1, "Bike"));
        assertNotEquals(oldBike, this.article.getBike());
    }

    @Test
    public void getPurchaseAmount(){
        assertEquals(1, this.article.getPurchaseAmount());
    }

    @Test
    public void setPurchaseAmount(){
        int oldPurchaseAmount = this.article.getPurchaseAmount();
        this.article.setPurchaseAmount(20);
        assertNotEquals(oldPurchaseAmount, this.article.getPurchaseAmount());
    }
}
