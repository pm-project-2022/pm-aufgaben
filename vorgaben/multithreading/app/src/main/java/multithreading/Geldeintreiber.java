package multithreading;

import java.util.List;

public class Geldeintreiber extends Thread{
    private Kunde k;
    private List<Kunde> kunden;

    public Geldeintreiber(Kunde k, List<Kunde> kunden){
        this.k = k;
        this.kunden = kunden;
    }
}
