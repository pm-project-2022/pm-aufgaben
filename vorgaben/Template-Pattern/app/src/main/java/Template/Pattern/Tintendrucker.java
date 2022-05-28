package Template.Pattern;

public class Tintendrucker extends Drucker {

    @Override
    protected String scannen() {
       return "Scanne das Dokument mit dem Tintendrucker.";
        
    }

    @Override
    protected String drucken() {
       return  "Drucke das Dokument auf dem Tintendrucker.";
        
    }
    
}
