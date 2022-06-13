package Testfallermittlung_AB9;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BankTest {
    private Bank testBank;
    private Konto userKonto;
    
    @Before
    public void setUp(){
        this.testBank = new Bank();
    }

    @Test
    public void getBank(){
        assertNotNull(this.testBank);
    }

    @Test
    public void checkCorrectAge(){
        this.userKonto = new Konto("Max", 15, 15);
        assertTrue(this.testBank.checkAge(this.userKonto));
    }

    @Test
    public void checkIncorrectAge(){
        this.userKonto = new Konto("Max", -1, 15);
        assertFalse(this.testBank.checkAge(this.userKonto));
    }

    @Test
    public void checkCorrectAccBalance(){
        this.userKonto = new Konto("Max", 15, 15);
        assertTrue(this.testBank.checkAccBalance(this.userKonto));
    }

    @Test
    public void checkInCorrectAccBalance(){
        this.userKonto = new Konto("Max", 15, -15);
        assertFalse(this.testBank.checkAccBalance(this.userKonto));
    }

    @Test
    public void checkBonusKind(){
        this.userKonto = new Konto("Max", 2, 100);
        assertEquals(0.5, this.testBank.getBonus(userKonto), 0.5f);
    }

    @Test
    public void checkBonusRentner(){
        this.userKonto = new Konto("Max", 67, 100);
        assertEquals(1f, this.testBank.getBonus(userKonto), 0.5f);
    }

    @Test
    public void checkBonusNormal(){
        this.userKonto = new Konto("Max", 35, 100);
        assertEquals(0f, this.testBank.getBonus(userKonto), 0.5f);
    }
    

    @Test
    public void checkAccBalanceUnder101(){
        this.userKonto = new Konto("Max", 35, 100);
        assertEquals(1f, this.testBank.getZinsatz(userKonto), 0.5f);
    }

    @Test
    public void checkAccBalanceUnder1001(){
        this.userKonto = new Konto("Max", 35, 1000);
        assertEquals(3f, this.testBank.getZinsatz(userKonto), 0.5f);
    }

    @Test
    public void checkAccBalanceOver1000(){
        this.userKonto = new Konto("Max", 35, 10000);
        assertEquals(5f, this.testBank.getZinsatz(userKonto), 0.5f);
    }

    @Parameter(0)
    public float age;

    @Parameter(1)
    public float accBalance;

    @Parameter(2)
    public float erg;

    @Parameters
    public static Collection<Object[]> values(){
        return Arrays.asList(new Object[][] {{0f,1f,1.5f}, {5f,1f,1.5f}, {0f,1000f,3.5f}, {5f,1000f,3.5f}, {0f,10000f,5.5f}, {5f,10000f,5.5f}, {6f,1f,1f}, {64f,1f,1f}, {6f,1000f,3f} , {64f,1000f,3f}, {6f,10000f,5f}, {64f,10000f,5f}, {65f,1f,2f}, {65f,1000f,4f}, {65f,10000f,6f} });
    }


    @Test
    public void checkResult(){
        int newAge = (int) age;
        int newAccBalance = (int) accBalance;
        this.userKonto = new Konto("max", newAge, newAccBalance);
        assertEquals(erg, this.testBank.getResult(userKonto), 0.5f);
    }

}
