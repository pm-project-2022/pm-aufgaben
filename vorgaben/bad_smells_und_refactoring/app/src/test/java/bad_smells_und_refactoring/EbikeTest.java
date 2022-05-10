package bad_smells_und_refactoring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class EbikeTest {

    public EBike eBike;

    @Before
    public void setUp(){
        this.eBike = new EBike("Ebike b1", 599.99, 30, 1, 1,20);
    }

    /**
     * Laesst sich ein Brompton anlegen
     */
    @Test
    public void testEBike(){
        assertNotNull(eBike);
    }

    /**
     * Lässt sich die Batteriekapazität zurückgeben
     */
    @Test
    public void testGetBatteryCapacity(){
        Integer expected = 20;
        assertEquals(expected, this.eBike.getBatteryCapacity());
    }

    /**
     * Lässt sich ein neuer Wert für die Batteriekapazität setzen
     */
    @Test
    public void testSetBatteryCapacity(){
        Integer oldBatteryCapacity = this.eBike.getBatteryCapacity();
        this.eBike.setBatteryCapacity(25);
        assertNotEquals(oldBatteryCapacity, this.eBike.getBatteryCapacity());
    }
    
}
