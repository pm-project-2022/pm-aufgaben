package bad_smells_und_refactoring;

/**
 * Erbt von Bike und definiert die Klasse genauer
 */

public class Mountainbike extends Bike {

    public Mountainbike(String pn, double p, int ms, int rgc, int fgc) {
        super(pn, p, ms, rgc, fgc, "Mountainbike");
    }
}
