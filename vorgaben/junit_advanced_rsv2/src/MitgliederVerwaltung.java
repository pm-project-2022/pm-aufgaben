public class MitgliederVerwaltung {

    /**
     * Testet, ob ein Mitglied in den Verein aufgenommen werden kann.
     *
     * @param alter      Alter in Lebensjahren, Bereich [0, 99]
     * @param motivation Motivation auf einer Scala von 0 bis 10
     * @return <code>true</code>, wenn das Mitglied aufgenommen werden kann, sonst <code>false</code>
     */
    public boolean testBeitritt(int alter, int motivation) {
        if (alter < 0 || alter > 99 || motivation < 0 || motivation > 10) {
            throw new IllegalArgumentException("Alter oder/und Motivation ungültig");
        }
        if (alter < 16) {
            return false;
        }
        return motivation >= 4 && motivation <= 7;
    }
}
