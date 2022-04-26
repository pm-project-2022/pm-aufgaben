package zoo;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import zoo.Formatter.ConsoleFormatter;
import zoo.tiere.fische.Salzwasserfisch;
import zoo.tiere.fische.Sueszwasserfisch;
import zoo.tiere.reptil.Krokodil;
import zoo.tiere.saeuger.affen.Gibbon;

public class Main {
    private static Logger log;
    private static ConsoleHandler consoleHandler;

    public static void setupLog() {
        log = Logger.getLogger(Main.class.getName());
        log.setUseParentHandlers(false);
        log.setLevel(Level.ALL);
        consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new ConsoleFormatter());
        log.addHandler(consoleHandler);
    }

    public static void main(String[] args) {
        setupLog();

        Gehege<Gibbon> gibbonGehege = new Gehege<>("Gibbongehege");
        log.info(gibbonGehege.getName() + " wurde angelegt");

        Gehege<Krokodil> krokoGehege = new Gehege<>("Reptiliengehege");
        log.info(krokoGehege.getName() + " wurde angelegt");

        Gehege<Salzwasserfisch> salzwasserfisch = new Gehege<>("Salzwasserfisch");

        Gehege<Sueszwasserfisch> sueszwasserfisch = new Gehege<>("Sueszwasserfisch");

        log.info(gibbonGehege.aufnehmen(new Gibbon("Gibbon1")));
        log.info(gibbonGehege.aufnehmen(new Gibbon("Gibbon2")));
        log.info(gibbonGehege.aufnehmen(new Gibbon("Gibbon3")));
        log.info(gibbonGehege.aufnehmen(new Gibbon("Gibbon4")));
        log.info(gibbonGehege.getAnimals());

        log.info(krokoGehege.aufnehmen(new Krokodil("Kroko1")));
        log.info(krokoGehege.aufnehmen(new Krokodil("Kroko2")));
        log.info(krokoGehege.aufnehmen(new Krokodil("Kroko3")));
        log.info(krokoGehege.aufnehmen(new Krokodil("Kroko4")));
        log.info(krokoGehege.getAnimals());

        Zoo<Gehege<?>> zoo = new Zoo<>();
        log.info(zoo.errichten(gibbonGehege));
        log.info(zoo.errichten(gibbonGehege));
        log.info(zoo.errichten(krokoGehege));
        log.info(zoo.abreissen(krokoGehege));
        log.info(zoo.abreissen(krokoGehege));

        Aquarium aquarium = new Aquarium();
        log.info(aquarium.errichten(salzwasserfisch));
        log.info(aquarium.errichten(sueszwasserfisch));
        log.info(aquarium.abreissen(sueszwasserfisch));
        log.info(aquarium.abreissen(sueszwasserfisch));
        

    }
}
