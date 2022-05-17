package observer_pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class Einzelhandel implements IObserver{
    private static final Logger LOGGER = Logger.getLogger(Einzelhandel.class.getName());
    private HashMap<WarenTyp, Integer> lager;
    private Grosshandel grosshandel;
    private List<Auftrag> auftraege;

    public Einzelhandel(Grosshandel grosshandel) {
        lager = new HashMap<>();
        auftraege = new ArrayList<>();
        this.grosshandel = grosshandel;
        this.grosshandel.register(this);
    }

    /**
     * Kunde bestellt Ware: Speichere den Auftrag ab und versuche später
     * in <code>loop()</code>, den Auftrag beim Großhandel zu bestellen.
     *
     * @param auftrag der Auftrag, der aufgenommen werden soll.
     */
    public void bestellen(Auftrag auftrag) {
        auftraege.add(auftrag);
        LOGGER.info("Auftrag: " + auftrag.getAnzahl() + " " + auftrag.getWarenTyp() + " wurde hinzufügt\n");
    }

    /**
     * Empfange Ware vom Großhandel, füge die Ware dem Lager hinzu und
     * entferne den offenen Auftrag.
     *
     * @param auftrag der Auftrag, der abgearbeitet werden soll.
     */
    public void empfangen(Auftrag auftrag) {
        lager.put(auftrag.getWarenTyp(), lager.getOrDefault(auftrag.getWarenTyp(), 0) + auftrag.getAnzahl());
        auftraege.remove(auftrag);
        LOGGER.info("Neue Lieferung angekommen. Inhalt: " + auftrag.getAnzahl() + " " + auftrag.getWarenTyp()+ "\n");
    }

    /**
     * Versuche alle Aufträge durch Bestellung beim Großhandel abzuschließen.
     */
    public void loop() {
        for (int i = auftraege.size(); i > 0; i--) {
            Auftrag auftrag = auftraege.get(i - 1);
            grosshandel.bestellen(this, auftrag);
        }
    }

    /**
     * Existieren beim Einzelhändler noch offene Aufträge?
     *
     * @return true wenn mehr als 1 Auftrag offen ist, sonst false
     */
    public boolean hatAuftraege() {
        return auftraege.size() > 0;
    }

    @Override
    public void update(String observerableMessage) {
        LOGGER.info(observerableMessage);
        
    }
}
