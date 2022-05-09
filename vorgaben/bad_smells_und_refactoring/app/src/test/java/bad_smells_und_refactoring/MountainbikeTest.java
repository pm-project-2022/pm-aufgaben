package bad_smells_und_refactoring;


import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

public class MountainbikeTest {
    
    public static Mountainbike mountainbike;

    @BeforeClass
    public static void setUp(){
        mountainbike = new Mountainbike("Mountaibike B1", 599.99, 25, 7, 3);
    }

    /**
     * Laesst sich ein Brompton anlegen
     */
    @Test
    public void testMountainBike(){
        assertNotNull(mountainbike);
    }
}
