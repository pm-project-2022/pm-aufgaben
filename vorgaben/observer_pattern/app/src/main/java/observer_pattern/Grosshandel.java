package observer_pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

public class Grosshandel {
    private static final Logger LOGGER = Logger.getLogger(Grosshandel.class.getName());
    private HashMap<WarenTyp, Integer> lager;
    private final List<IObserver> observers = new ArrayList<>();
    public Grosshandel() {
        lager = new HashMap<>();
        for (WarenTyp typ : WarenTyp.values()) {
            lager.put(typ, 0);
        }
    }

    /**
     * Ermöglicht einem Einzelhändler als Observer zu registieren
     * @param observer
     */
    public void register(IObserver observer){
        this.observers.add(observer);
        LOGGER.info("Observer hat sich angemeldet\n");
    }

    /**
     * Ein Einzelhändler kann Waren mit einer bestimmten Anzahl bestellen. Wenn
     * diese in ausreichender Zahl verfügbar ist, liefert der Großhandel die
     * gewünschte Ware direkt aus.
     *
     * @param kunde   Der Kunde, welcher die Bestellung tätigt.
     * @param auftrag Der Auftrag, welcher abgearbeitet werden soll.
     * @return true, wenn der Auftrag ausgeführt wurde; false sonst
     */
    public boolean bestellen(Einzelhandel kunde, Auftrag auftrag) {
        if (lager.getOrDefault(auftrag.getWarenTyp(), 0) >= auftrag.getAnzahl()) {
            lager.put(auftrag.getWarenTyp(), lager.get(auftrag.getWarenTyp()) - auftrag.getAnzahl());
            LOGGER.info("Auftrag wurde ausgeführt.\n");
            notifyObserver(kunde, "Auftrag wurde ausgeführt, die Ware befindet sich auf dem Weg.\n");
            kunde.empfangen(auftrag);
            return true;
        }
        LOGGER.warning("Lagerbestand nicht ausreichend.\n");
        notifyObserver(kunde, "Auftrag kann nicht ausgeführt werden. Der Lagerbestand ist nicht ausreichend\n");
        return false;
    }

    /**
     * benachrichtigt den kunden wenn dieser als observer registiert ist
     * @param kunde der zu benachrichtigen kunde
     * @param observerableMessage nachricht an den kunden
     */
    private void notifyObserver(Einzelhandel kunde, String observerableMessage){
        for (IObserver observer : observers) {
            if(observer.equals(kunde)){
                LOGGER.info("Observer wird benachrichtigt.\n");
                kunde.update(observerableMessage);
            }
        }
    }

    /**
     * Der Grosshandel bekommt immer Ware, von der am wenigsten aktuell verfügbar
     * ist.
     */
    public void loop() {
        Random random = new Random();
        Map.Entry<WarenTyp, Integer> kleinsterBestand = findeKleinstenBestand();
        int zusatzMenge = random.nextInt(1, 5);
        kleinsterBestand.setValue(kleinsterBestand.getValue() + zusatzMenge);
        LOGGER.info(kleinsterBestand.getKey() + "-Bestand wurde auf" + kleinsterBestand.getValue() + " erhöht\n");
    }

    private Map.Entry<WarenTyp, Integer> findeKleinstenBestand() {
        Map.Entry<WarenTyp, Integer> kleinsterBestand = null;
        for (Map.Entry<WarenTyp, Integer> loop : lager.entrySet()) {
            if (kleinsterBestand == null || kleinsterBestand.getValue() > loop.getValue()) {
                kleinsterBestand = loop;
            }
        }
        return kleinsterBestand;
    }
}
