package bad_smells_und_refactoring;


import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class BillTest {
    
    private Bill bill;

    @Before
    public void setUp(){
        this.bill = new Bill("Max Mustermann", "MaMu", "Musterstrasse", "5", 01234, new Date() , "max@mustermann.de", "Musterstadt");
    }

    @Test
    public void getBill(){
        assertNotNull(this.bill);
    }
    
}
