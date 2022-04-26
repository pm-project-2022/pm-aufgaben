package bundesliga.generic;

import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import bundesliga.Formatter.ConsoleFormatter;

public class Mannschaft<E extends Spieler> implements IMannschaft<E> {
    private ArrayList<E> mannschaft;
    private Logger logger;
    private ConsoleHandler consoleHandler;

    public Mannschaft() {
        this.mannschaft = new ArrayList<>();
        initLogger();
    }

    private void initLogger() {
        this.logger = Logger.getLogger(Mannschaft.class.getName());
        this.consoleHandler = new ConsoleHandler();
        this.consoleHandler.setLevel(Level.ALL);
        this.consoleHandler.setFormatter(new ConsoleFormatter());
        this.logger.addHandler(consoleHandler);
        this.logger.setLevel(Level.ALL);
        this.logger.setUseParentHandlers(false);
    }

    @Override
    public boolean aufnehmen(E spieler) {
        for (E e : mannschaft) {
            if (e.toString().equals(spieler.toString())) {
                this.logger.warning("'" + spieler.getName() + "' ist bereits in Mannschaft");
                return false;
            }
        }

        this.mannschaft.add(spieler);
        this.logger.info("'" + spieler.getName() + "' konnte erfolgreich der Mannschaft hinzugefuegt werden.");
        return true;
    }

    @Override
    public boolean rauswerfen(E spieler) {
        for (E e : mannschaft) {
            if (e.toString().equals(spieler.toString())) {
                this.mannschaft.remove(e);
                this.logger.info("'" + spieler.getName() + "' wurde erfolgreich aus der Mannschaft entfernt.");
                return true;
            }
        }

        this.logger.warning("'" + spieler.getName() + "' konnte nicht gefunden werden.");
            return false;
    }
}
