package Template.Pattern;

import java.util.logging.Logger;

/**
 * demo klasse um die verschiedenen drucker zu demonstrieren.
 */

public class Main {
    public static void main(String[] args) {
        Logger log = Logger.getLogger(Main.class.getName());
        Drucker tintenDrucker = new Tintendrucker();
        Drucker laserDrucker = new Laserdrucker();
        log.info(tintenDrucker.kopieren());
        log.info(laserDrucker.kopieren());
    }
}
