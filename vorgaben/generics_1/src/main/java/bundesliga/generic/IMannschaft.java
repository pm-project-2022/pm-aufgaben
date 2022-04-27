package bundesliga.generic;

public interface IMannschaft<E extends ISpieler> {
    /**
     * Nimmt einen Spieler in die Mannschaft auf.
     *
     * <p>Ein Spieler kann in mehreren verschiedenen Mannschaften spielen, aber in einer Mannschaft
     * nur einmal gelistet sein.
     *
     * <p>Es gibt <b>keine</b> Reihenfolge unter den Spielern einer Mannschaft.
     *
     * @param spieler Spieler, der aufgenommen werden soll.
     * @return <code>false</code> wenn Spieler nicht hinzugefügt werden konnte, sonst <code>true
     *     </code>.
     */
    boolean aufnehmen(E spieler);

    /**
     * Entfernt einen Spieler aus der Mannschaft.
     *
     * @param spieler Spieler, der entfernt werden soll.
     * @return <code>false</code> wenn Spieler nicht entfernt werden konnte, ansonsten <code>true
     *     </code>.
     */
    boolean rauswerfen(E spieler);
}
