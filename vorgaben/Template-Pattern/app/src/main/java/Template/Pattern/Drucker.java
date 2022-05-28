package Template.Pattern;

import java.util.logging.Logger;

public abstract class Drucker {
    protected Logger log;


    public final void kopieren(){
        log.info(scannen());
        log.info(drucken());
    }

    abstract protected String scannen();
    abstract protected String drucken();
}
