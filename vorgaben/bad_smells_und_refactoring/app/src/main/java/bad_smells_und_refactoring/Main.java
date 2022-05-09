package bad_smells_und_refactoring;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        Calendar myCal = new GregorianCalendar(2000, Calendar.JANUARY, 1);
        Date myDate = myCal.getTime();
        CustomerData customerData = new CustomerData("Max", "Maxi", myDate, "max@max.de", "musterstraße", "8", 01234, "Musterstadt");
        ArrayList<Article> articles = new ArrayList<>();
        articles.add(new Article(new Brompton("Brompton", 250, 5, 5, 5), 2));
        articles.add(new Article(new EBike("Ebikeeee", 250,5 , 5, 5, 5), 3));
        articles.add(new Article(new Mountainbike("Mountainbike", 300, 2, 2, 2), 3));
        Bill bill = new Bill(customerData, articles);
        bill.printBill();
    }
}
