package Testfallermittlung_AB9;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class KontoTest {
    
    private Konto testKonto;

    /**
     * initialisiert vor jedem test das testkonto
     */
    @Before
    public void setUp(){
        this.testKonto = new Konto("Max", 15, 100);
    }

    /**
     * testet ob ein konto erstellt wird
     */
    @Test
    public void getKonto(){
        assertNotNull(this.testKonto);
    }

    /**
     * testet der expected name = kontoname ist
     */
    @Test
    public void getCorrectName(){
        assertEquals("Max", this.testKonto.name());
    }

    /**
     * testet ob unexpected name != kontoname ist
     */
    @Test
    public void getIncorrectName(){
        assertNotEquals("Maximilian", this.testKonto.name());
    }

    /**
     * testet ob expected age = kontoage ist
     */
    @Test
    public void getCorrectAge(){
        assertEquals(15, this.testKonto.age());
    }

    /**
     * testet ob unexpected age != kontoage ist
     */
    @Test
    public void getIncorrectAge(){
        assertNotEquals(14, this.testKonto.age());
    }

    /**
     * testet ob expected accbalance = kontoaccbalance ist
     */
    @Test
    public void getCorrectAccBalance(){
        assertEquals(100, this.testKonto.accBalance());
    }

    /**
     * testet ob unexpected accbalance != kontoaccbalance ist
     */
    @Test
    public void getIncorrectAccBalance(){
        assertNotEquals(99, this.testKonto.accBalance());
    }
}
