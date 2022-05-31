package Template.Pattern;

/**
 * Abstrakte Klasse um verschiedene Drucker zu implementieren
 */

public abstract class Drucker {

    /**
     * lässt den drucker kopieren indem es etwas einscannt und dieses druckt
     * @return scan und druck meldung der drucker
     */
    public final String kopieren(){
        return scannen() + " " + drucken();
    }

    /**
     * scan methode, die jede druckerklasse für sich selber implementiert
     * @return scan meldung
     */
    abstract protected String scannen();

    /**
     * kopier methode, die jede druckerklasse für sich selber implementiert
     * @return kopier meldung
     */
    abstract protected String drucken();
}
