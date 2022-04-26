package zoo;

import java.util.ArrayList;

import zoo.tiere.Tier;

public class Gehege<T extends Tier>{
    private ArrayList<T> gehege;
    private String gehegeName;

    public Gehege(String gehegeNamen){
        this.gehege = new ArrayList<>();
        this.gehegeName = gehegeNamen;
    }

    public String aufnehmen(T tier){
        for (T t : gehege) {
            if(t.getName().equals(tier.getName())){
                return t.getName() + "befindet sich bereits im Gehege.";
            }
        }
        this.gehege.add(tier);
        return tier.getName() + " wurde in dem Gehege " + this.gehegeName + " aufgenommen.";
    }

    public String entfernen (T tier){
        for (T t : gehege) {
            if(t.getName().equals(tier.getName())){
                this.gehege.remove(t);
                return t.getName() + "verlässt das Gehege";
            }
        }

        return tier.getName() + "befindet sich nicht in dem Gehege";
    }

    public String getName(){
        return this.gehegeName;
    }

    public String getAnimals(){
        String animals = "";
        for (T t : gehege) {
            animals += t.getName() + " ";
        }
        return animals;
    }
}
