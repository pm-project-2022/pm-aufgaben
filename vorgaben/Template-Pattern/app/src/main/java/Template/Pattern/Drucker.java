package Template.Pattern;

public abstract class Drucker {
    
    public final void kopieren(){
        scannen();
        drucken();
    }

    abstract protected void scannen();
    abstract protected void drucken();
}
