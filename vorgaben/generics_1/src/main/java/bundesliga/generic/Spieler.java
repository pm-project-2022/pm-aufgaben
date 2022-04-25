package bundesliga.generic;

public class Spieler implements ISpieler{
    String spielerName;

    public Spieler(String spielerName){
        this.spielerName = spielerName;
    }

    @Override
    public String getName() {
        return this.spielerName;
    }
    
}
