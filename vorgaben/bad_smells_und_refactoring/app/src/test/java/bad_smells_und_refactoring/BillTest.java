package bad_smells_und_refactoring;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class BillTest {
    private Bill bill;
    private CustomerData customerData;
    private Calendar myCal;
    private Date myDate;
    private ArrayList<Article> articles; 
    
    @Before
    public void setUp(){
        this.myCal = new GregorianCalendar(2000, Calendar.JANUARY, 1);
        this.myDate = myCal.getTime();
        this.customerData = new CustomerData("Max", "Maxi", myDate, "max@max.de", "musterstraße", "8", 01234, "Musterstadt");
        this.articles = new ArrayList<>();
        this.articles.add(new Article(new EBike("Ebike", 500, 30, 1, 1, 80), 1));
        this.articles.add(new Article(new Mountainbike("Mountain", 500, 30, 1, 1), 5));
        this.bill = new Bill(customerData, articles);
    }

    /**
     * lässt sich eine rechnung erstellen
     */
    @Test
    public void getBill(){
        assertNotNull(this.bill);
    }

    @Test
    public void testAddArticle(){
        assertTrue(this.bill.addArticle(new Article(new Brompton("Brompton", 500, 50, 1, 1), 1)));
    }
    
}
