package multithreading;

import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class Kunde implements Runnable {
    private Konto konto;
    private Queue<Rechnung> offeneRechnungen;
    private Logger kundenLogger = Logger.getLogger(Kunde.class.getSimpleName());

    /** Erstelle einen neune Kunden, der sich ein Konto bei der Bank erstellt. */
    public Kunde() {
        konto = new Konto(0);
        offeneRechnungen = new LinkedList<>();
    }

    public Kunde(int id) {
        konto = new Konto(id);
        offeneRechnungen = new LinkedList<>();
        for (Handler handler : kundenLogger.getHandlers()) {
            kundenLogger.removeHandler(handler);
        }
        kundenLogger.setUseParentHandlers(false);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setFormatter(new LoggerFormatter());
        kundenLogger.addHandler(ch);
    }

    /**
     * Empfange eine Rechnung zum bezahlen.
     *
     * @param rechnung Die Rechnung.
     */
    public void empfangeRechnung(Rechnung rechnung) {
        offeneRechnungen.add(rechnung);
        kundenLogger.info("Konto-ID " + this.konto.getId() + ": Rechnung von Geldeintreiber "
                + rechnung.empfaenger().getId() + " bekommen.\nBetrag: " + rechnung.betrag() + " Euro");
        invokeNotify();
    }

    /**
     * getter für das konto
     * 
     * @return konto des kunden
     */
    public Konto getKonto() {
        return konto;
    }

    /**
     * zahlt zahlt die oberste offene rechnung aus der queue offeneRechnung. ist die
     * queue leer, wartet der thread
     */
    private synchronized void rechnungZahlen() {
        while (offeneRechnungen.peek() == null) {
            try {
                kundenLogger.info("Alle Rechnung in Konto-ID " + this.konto.getId() + " bezahlt. Wait");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        kundenLogger.info("Rechnung in Konto-ID " + this.konto.getId() + " wird versucht zu bezahlen");
        Bank.ueberweisen(this.konto, this.offeneRechnungen.poll());
    }

    /**
     * entfernt alle threads aus der warteschlange
     */
    private synchronized void invokeNotify() {
        notifyAll();
    }

    @Override
    public void run() {
        while(true){
            rechnungZahlen();
        }
    }
}
