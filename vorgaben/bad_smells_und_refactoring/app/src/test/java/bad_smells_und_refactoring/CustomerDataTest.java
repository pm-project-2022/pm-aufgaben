package bad_smells_und_refactoring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class CustomerDataTest {
    private CustomerData customerData;
    private Calendar myCal;
    private Date myDate; 

    @Before
    public void setUp(){
        this.myCal = new GregorianCalendar(2000, Calendar.JANUARY, 1);
        this.myDate = myCal.getTime();
        this.customerData = new CustomerData("Max", "Maxi", myDate, "max@max.de", "musterstraße", "8", 01234, "Musterstadt");
    }

    /**
     * lassen sich kundendaten erstellen
     */
    @Test
    public void testCustomerData(){
        assertNotNull(this.customerData);
    }

    /**
     * lässt sich der kundenname zurückgeben
     */
    @Test
    public void testGetCustomerName(){
        assertEquals("Max", this.customerData.getCustomerName());
    }

    /**
     * lässt sich der kundenname ändern
     */
    @Test
    public void testSetCustomerName(){
        String oldCustomer = this.customerData.getCustomerName();
        this.customerData.setCustomerName("Maximilian");
        assertNotEquals(oldCustomer, this.customerData.getCustomerName());
    }

    /**
     * lässt sich der nickname zurückgeben
     */
    @Test
    public void testGetNickname(){
        assertEquals("Maxi", this.customerData.getNickname());
    }

    /**
     * lässt sich der nickname ändern
     */
    @Test
    public void testSetNickname(){
        String oldNickname = this.customerData.getNickname();
        this.customerData.setNickname("Max");
        assertNotEquals(oldNickname, this.customerData.getNickname());
    }

    /**
     * lässt sich das geburtsdatum zurückgeben
     */
    @Test
    public void testGetBirthday(){
        assertEquals(this.myDate, this.customerData.getBirthday());
    }

    /**
     * lässt sich das geburtsdatum ändern
     */
    @Test
    public void testSetBirthday(){
        this.myDate = this.customerData.getBirthday();
        this.customerData.setBirthday(new Date());
        assertNotEquals(this.myDate, this.customerData.getBirthday());
    }

    /**
     * lässt sich die email adresse zurückgeben
     */
    @Test
    public void testGetEmail(){
        assertEquals("max@max.de", this.customerData.getEmail());
    }

    /**
     * lässt sich die email adresse ändern 
     */
    @Test
    public void testSetEmail(){
        String oldEmail = this.customerData.getEmail();
        this.customerData.setEmail("test");
        assertNotEquals(oldEmail, this.customerData.getEmail());
    }

    /**
     * lässt sich eine straße zurückgeben
     */
    @Test
    public void testGetStreet(){
        assertEquals("musterstraße", this.customerData.getStreet());
    }

    /**
     * lässt sich eine straße ändern
     */
    @Test
    public void testSetStreet(){
        String oldStreet = this.customerData.getStreet();
        this.customerData.setStreet("new Street");
        assertNotEquals(oldStreet, this.customerData.getStreet());
    }

    /**
     * lässt sich die hausnummer zurückgeben
     */
    @Test
    public void testGetStreetNumber(){
        assertEquals("8", this.customerData.getStreetNumber());
    }

    /**
     * lässt sich die Hausnummer ändern
     */
    @Test
    public void testSetStreetNumber(){
        String oldStreetNumber = this.customerData.getStreetNumber();
        this.customerData.setStreetNumber("9");
        assertNotEquals(oldStreetNumber, this.customerData.getStreetNumber());
    }

    /**
     * lässt sich die postleitzahl zurückgeben
     */
    @Test
    public void testGetPostalCode(){
        assertEquals(01234, this.customerData.getPostalCode());
    }

    /**
     * lässt sich die postleitzahl ändern
     */
    @Test
    public void testSetPostalCode(){
        int oldPostalCode = this.customerData.getPostalCode();
        this.customerData.setPostalCode(12345);
        assertNotEquals(oldPostalCode, this.customerData.getPostalCode());
    }

    /**
     * lässt sich die stadt zurückgeben
     */
    @Test
    public void testGetCity(){
        assertEquals("Musterstadt", this.customerData.getCity());
    }

    /**
     * lässt sich die Stadt ändern
     */
    @Test 
    public void testSetCity(){
        String oldCity = this.customerData.getCity();
        this.customerData.setCity("city");
        assertNotEquals(oldCity, this.customerData.getCity());
    }

}
