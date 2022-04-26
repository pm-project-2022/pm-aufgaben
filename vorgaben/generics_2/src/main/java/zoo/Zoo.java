package zoo;

import java.util.ArrayList;

public class Zoo<T extends Gehege<?>> {
    ArrayList<T> zoo;

    public Zoo() {
        this.zoo = new ArrayList<>();
    }

    public String errichten(T gehege) {
        for (T t : zoo) {
            if (t.getName().equals(gehege.getName())) {
                return gehege.getName() + "-Gehege existiert schon";
            }

        }
        zoo.add(gehege);
        return gehege.getName() + "-Gehege wurde errichtet.";
    }

    public String abreissen(T gehege) {
        for (T t : zoo) {
            if (t.getName().equals(gehege.getName())) {
                zoo.remove(gehege);
                return gehege.getName() + "-Gehege wurde abgerissen.";
            }

        }
        return gehege.getName() + "-Gehege existiert nicht.";
    }

}
