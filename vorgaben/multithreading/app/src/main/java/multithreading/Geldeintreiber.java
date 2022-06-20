package multithreading;


import java.util.List;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class Geldeintreiber extends Thread{
    private Kunde k;
    private int id;
    private List<Kunde> kunden;
    private Random random;
    private Logger geldeintreiberLogger = Logger.getLogger(Geldeintreiber.class.getSimpleName());
    private final long sleepDuration = 5000;

    public Geldeintreiber(Kunde k, List<Kunde> kunden){
        this.k = k;
        this.kunden = kunden;
        this.random = new Random();
        this.id = k.getKonto().getId();
        geldeintreiberLogger.setUseParentHandlers(false);
        for (Handler handler : geldeintreiberLogger.getHandlers()) {
            geldeintreiberLogger.removeHandler(handler);
        }
        ConsoleHandler ch = new ConsoleHandler();
        ch.setFormatter(new LoggerFormatter());
        geldeintreiberLogger.addHandler(ch);
    }

    /**
     * generiert eine rechnung sendet diese an einen zufälligen kunden aus der kundenliste
     */
    private void generiereRechnung(){
        int randomKunde;
        do{
            randomKunde = random.nextInt(this.kunden.size());
        }while(this.k.equals(this.kunden.get(randomKunde)));
        geldeintreiberLogger.info("Geldeintreiber " + this.id + ": Rechnung wird an Kunden-ID " + randomKunde + " geschickt." );
        this.kunden.get(randomKunde).empfangeRechnung(new Rechnung(500, this.k.getKonto()));
    };

    /**
     * ruft die generiereRechnung Methode auf und lässt den thread danach 5 sekunden schlafen
     */
    @Override
    public void run() {
        while(true){
            try {
                generiereRechnung();
                geldeintreiberLogger.info("Geldeintreiber " + this.id + " schlaeft fuer 5 Sekunden.");
                Thread.sleep(this.sleepDuration);
            } catch (InterruptedException e) {
                geldeintreiberLogger.warning("Thread wurde interrupted");
            }
        }
    }
}
