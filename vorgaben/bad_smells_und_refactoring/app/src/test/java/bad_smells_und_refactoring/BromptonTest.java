package bad_smells_und_refactoring;

import static org.junit.Assert.assertNotNull;


import org.junit.BeforeClass;
import org.junit.Test;

public class BromptonTest {
    
    public static Brompton brompton;

    @BeforeClass
    public static void setUp(){
        brompton = new Brompton("Brompton B1", 599.99, 20, 3, 1);
    }

    /**
     * Laesst sich ein Brompton anlegen
     */
    @Test
    public void testBrompton(){
        assertNotNull(brompton);
    }

}
