package Template.Pattern;

public class Laserdrucker extends Drucker {

    @Override
    protected String scannen() {
        return "Scanne das Dokument mit dem Laserdrucker.";
        
    }

    @Override
    protected String drucken() {
        return "Drucke das Dokument auf dem Laserdrucker.";
        
    }
    
}
