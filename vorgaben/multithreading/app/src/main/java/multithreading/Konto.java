package multithreading;


public class Konto {
    private double kontostand = 1000;
    private int id;

    public Konto(int id){
        this.id = id;
    }

    /**
     * gibt die konto-id zurück
     * @return konto-id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Von diesem Konto soll Geld gesendet werden
     *
     * @param betrag Betrag der von diesem Konto abgezogen werden soll
     * @return true wenn der Betrag abgezogen wurde, false wenn nicht
     */
    public boolean sendeGeld(double betrag) {
        while (betrag > kontostand) {
            return false;
        }
        kontostand -= betrag;
        return true;
    }

    /**
     * Auf dieses Konto soll geld gesendet werden
     *
     * @param betrag Betrag der diesem Konto gutgeschrieben werden soll
     */
    public void empfangeGeld(double betrag) {
        kontostand += betrag;
    }
}
