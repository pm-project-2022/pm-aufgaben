package Template.Pattern;

public abstract class Drucker {

    public final String kopieren(){
        return scannen() + " " + drucken();
    }

    abstract protected String scannen();
    abstract protected String drucken();
}
