package multithreading;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

// 
/**
 * Eine Transaktion bearbeitet eine Rechnung
 *
 * @param von Konto von dem aus die Rechnung bezahlt werden soll
 * @param rechnung Die zu bezahlende Rechnung
 */
public record Transaktion(Konto von, Rechnung rechnung) implements Runnable {
    private static final Logger transaktionsLogger = Logger.getLogger(Transaktion.class.getSimpleName());
    private static final Object waechter = new Object();

    public Transaktion(Konto von, Rechnung rechnung){
        this.von = von;
        this.rechnung = rechnung;
        transaktionsLogger.setUseParentHandlers(false);
        for (Handler handler : transaktionsLogger.getHandlers()) {
            transaktionsLogger.removeHandler(handler);
        }
        ConsoleHandler ch = new ConsoleHandler();
        ch.setFormatter(new LoggerFormatter());
        transaktionsLogger.addHandler(ch);
    }

    /**
     * zieht den betrag vom sender konto ab und addiert diesem zum empfänger falls genügend geld vorhanden ist
     */
    private void doTransaktion(){
        synchronized(waechter){
            if(this.von.sendeGeld(this.rechnung.betrag())){
                this.rechnung.empfaenger().empfangeGeld(this.rechnung.betrag());
                transaktionsLogger.info("Schulden von Konto-ID " +  von.getId() + " konnte erfolgreich an Konto-ID " + rechnung.empfaenger().getId()+ " gezahlt werden.");
            }else{
                transaktionsLogger.info("Nicht genügend Geld auf dem Konto von Gläubiger-ID " + von.getId());
            }
        }
    }

    @Override
    public void run() {
        doTransaktion();
    }

}
