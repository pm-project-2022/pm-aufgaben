package multithreading;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Bank{
    /**
     * Ueberweise Geld von einem Konto auf das andere
     *
     * @param von Konto von dem das Geld gesendet wird
     * @param rechnung Die Rechnung die bezahlt werden muss
     */
    public static void ueberweisen(Konto von, Rechnung rechnung) {
        Transaktion transaktion = new Transaktion(von, rechnung);
        Executor executor = new ScheduledThreadPoolExecutor(5);
        executor.execute(transaktion);
    }

}
