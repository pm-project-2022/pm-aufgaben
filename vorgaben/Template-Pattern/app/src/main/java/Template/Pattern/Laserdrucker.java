package Template.Pattern;

import java.util.logging.Logger;

public class Laserdrucker extends Drucker {

    public Laserdrucker(){
        this.log = Logger.getLogger("Laserdruckerlogger");
    }

    @Override
    protected String scannen() {
        return "Scanne das Dokument mit dem Laserdrucker.";
        
    }

    @Override
    protected String drucken() {
        return "Drucke das Dokument auf dem Laserdrucker.";
        
    }
    
}
