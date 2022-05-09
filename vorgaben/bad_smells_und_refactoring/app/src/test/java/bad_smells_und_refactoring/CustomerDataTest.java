package bad_smells_und_refactoring;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class CustomerDataTest {
    private CustomerData customerData;

    @Before
    public void setUp(){
        this.customerData = new CustomerData("Max", "Maxi", new Date(), "max@max.de", "musterstraße", "8", 01234, "Musterstadt");
    }

    /**
     * lassen sich kundendatene erstellen
     */
    @Test
    public void createCustomerData(){
        assertNotNull(this.customerData);
    }

    
}
