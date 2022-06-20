package multithreading;

import java.util.*;
import java.util.logging.Logger;

public class Kunde extends Thread{
    private Konto konto;
    private Queue<Rechnung> offeneRechnungen;
    private final Logger logger = Logger.getLogger("Kunde-Logger");

    /** Erstelle einen neune Kunden, der sich ein Konto bei der Bank erstellt. */
    public Kunde() {
        konto = new Konto();
        offeneRechnungen = new LinkedList<>();
    }

    /**
     * Empfange eine Rechnung zum bezahlen.
     *
     * @param rechnung Die Rechnung.
     */
    public synchronized void empfangeRechnung(Rechnung rechnung) {
        offeneRechnungen.add(rechnung);
        notifyAll();
    }

    public Konto getKonto() {
        return konto;
    }

    private synchronized void rechnungZahlen(){
        while(offeneRechnungen.peek() == null){
            try {
                logger.warning("Es gibt keine zu bezahlende Rechnung in Thread " + Thread.currentThread().getName() + ". Es wird auf eine neue Rechnung gewartet.\n");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("Die oberste Rechnung von Thread " + Thread.currentThread().getName() + " wird bezahlt.\n");
        Bank.ueberweisen(this.konto, this.offeneRechnungen.poll());
        rechnungZahlen();
    }

    @Override
    public void run() {
        rechnungZahlen();
    }
}
