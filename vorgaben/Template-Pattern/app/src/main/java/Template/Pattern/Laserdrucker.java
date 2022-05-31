package Template.Pattern;

import java.util.logging.Logger;

/**
 * Laserdrucker implementation der abtrakten Klasse Drucker
 */

public class Laserdrucker extends Drucker {
    Logger log = Logger.getLogger(Main.class.getName());

    @Override
    protected String scannen() {
        log.info("Scanne das Dokument mit dem Laserdrucker.");
        return "Scanne das Dokument mit dem Laserdrucker.";
        
    }

    @Override
    protected String drucken() {
        log.info("Drucke das Dokument auf dem Laserdrucker.");
        return "Drucke das Dokument auf dem Laserdrucker.";
        
    }
    
}
