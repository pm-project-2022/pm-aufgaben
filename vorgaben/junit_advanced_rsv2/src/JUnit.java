import org.junit.*;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class JUnit {

    MitgliederVerwaltung m1;

    @Before
    public void setup() {
        m1 = new MitgliederVerwaltung();
    }

    /**
     * Parametrisierter Test mit gültigen Werte
     */

    @Parameter(0)
    public int alter;
    @Parameter(1)
    public int motivation;

    @Parameters
    public static Collection<Object[]> values() {
        return Arrays.asList(new Object[][]{{50, 5}, {16, 4}, {99, 4}, {16, 7}, {99, 7}});
    }


    @Test
    public void beitritt() {
        assertTrue(m1.testBeitritt(alter, motivation));
    }

    /**
     * Test mit Paramater1 gültig, Paramater2 ungültig
     */
    @Test
    public void testValidNotValid() {
        assertFalse(m1.testBeitritt(50, 1));
        assertFalse(m1.testBeitritt(16, 3));
        assertFalse(m1.testBeitritt(99, 3));
    }

    /**
     * Test mit Paramater1 ungültig, Paramater2 gültig
     */
    @Test
    public void testNotValidValid() {
        assertFalse(m1.testBeitritt(12, 5));
        assertFalse(m1.testBeitritt(15, 4));
    }

    /**
     * Tests um Exceptions auszulösen
     */
    @Test(expected = IllegalArgumentException.class)
    public void testException() {
        m1.testBeitritt(50, -5);
        m1.testBeitritt(50, 15);
        m1.testBeitritt(-5, 5);
        m1.testBeitritt(115, 5);
        m1.testBeitritt(100, 4);
    }
}
