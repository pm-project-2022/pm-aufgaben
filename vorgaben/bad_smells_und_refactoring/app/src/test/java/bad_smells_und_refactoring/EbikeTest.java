package bad_smells_und_refactoring;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

public class EbikeTest {

    public static EBike eBike;

    @BeforeClass
    public static void setUp(){
        eBike = new EBike("Ebike b1", 599.99, 30, 1, 1,20);
    }

    /**
     * Laesst sich ein Brompton anlegen
     */
    @Test
    public void testEBike(){
        assertNotNull(eBike);
    }
    
}
