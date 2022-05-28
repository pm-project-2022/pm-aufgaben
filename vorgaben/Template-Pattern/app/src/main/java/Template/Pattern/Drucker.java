package Template.Pattern;

import java.util.logging.Logger;

public abstract class Drucker {
    protected Logger log;


    public final void kopieren(){
        scannen();
        drucken();
    }

    abstract protected String scannen();
    abstract protected String drucken();
}
