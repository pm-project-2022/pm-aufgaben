package Template.Pattern;

import java.util.logging.Logger;

public class Tintendrucker extends Drucker {

    public Tintendrucker(){
        this.log = Logger.getLogger("Tintendruckerlogger");
    }

    @Override
    protected String scannen() {
       return "Scanne das Dokument mit dem Tintendrucker.";
        
    }

    @Override
    protected String drucken() {
       return  "Drucke das Dokument auf dem Tintendrucker.";
        
    }
    
}
