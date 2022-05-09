package bad_smells_und_refactoring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class BikeTest {
    private Bike bike;

    @Before
    public void setUp() {
        this.bike = new Bike("Bike1", 499.99, 20, 1, 1);
    }

    /**
     * Laesst sich ein Bike anlegen
     */
    @Test
    public void testBike() {
        assertNotNull(bike);
    }

    /**
     * Lässt sich der Produktname zurückgeben
     */
    @Test
    public void testGetProducName() {
        assertEquals("Bike1", this.bike.getProductName());
    }

    /**
     * Lässt sich einer neuer Produktname setzen
     */
    @Test
    public void testSetProductName() {
        String oldProductName = this.bike.getProductName();
        this.bike.setProductName("Bike2");
        assertNotEquals(oldProductName, this.bike.getProductName());
    }

    /**
     * Lässt sich der Preis zurückgeben
     */
    @Test
    public void testGetPrice() {
        assertEquals(499, 99, this.bike.getPrice());
    }

    /**
     * Lässt sich ein neuer Preis setzen
     */
    @Test
    public void testSetPrice() {
        double oldPrice = this.bike.getPrice();
        this.bike.setPrice(500);
        assertNotEquals(oldPrice, this.bike.getPrice());
    }

    /**
     * Lässt sich der maxSpeed zurückgeben
     */
    @Test
    public void testGetMaxSpeed() {
        assertEquals(20, this.bike.getMaxSpeed());
    }

    /**
     * Lässt sich ein neuer maxSpeed setzen
     */
    @Test
    public void testSetMaxSpeed() {
        int oldMaxSpeed = this.bike.getMaxSpeed();
        this.bike.setMaxSpeed(25);
        assertNotEquals(oldMaxSpeed, this.bike.getMaxSpeed());
    }

    /**
     * Lässt sich die Anzahl der hinteren Gänge zurückgeben
     */
    @Test
    public void testGetRearGearsCount(){
        assertEquals(1, this.bike.getRearGearsCount());
    }

    /**
     * Lässt sich die Anzahl der hinteren Gänge ändern
     */
    @Test
    public void testSetRearGearsCount(){
        int oldRearGearsCount = this.bike.getRearGearsCount();
        this.bike.setRearGearsCount(2);
        assertNotEquals(oldRearGearsCount, this.bike.getRearGearsCount());
    }

    /**
     * Lässt sich die Anzahl der vorderen Gänge zurückgeben
     */
    @Test
    public void testGetFrontGearsCount(){
        assertEquals(1, this.bike.getFrontGearsCount());
    }

    /**
     * Lässt sich die Anzahl der vorderen Gänge ändern
     */
    @Test
    public void testSetFrontGearsCount(){
        int oldFrontGearsCount = this.bike.getFrontGearsCount();
        this.bike.setFrontGearsCount(2);
        assertNotEquals(oldFrontGearsCount, this.bike.getFrontGearsCount());
    }

    /**
     * Lässt sich die Anzahl der Gänge zurückgeben
     */
    @Test
    public void testGetGearsCount(){
        assertEquals(1, this.bike.getGearsCount());
    }
}
