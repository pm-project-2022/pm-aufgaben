package Template.Pattern;

import java.util.logging.Logger;

public class Tintendrucker extends Drucker {
   Logger log = Logger.getLogger(Main.class.getName());

   @Override
   protected String scannen() {
      log.info("Scanne das Dokument mit dem Tintendrucker.");
      return "Scanne das Dokument mit dem Tintendrucker.";

   }

   @Override
   protected String drucken() {
      log.info("Drucke das Dokument auf dem Tintendrucker.");
      return "Drucke das Dokument auf dem Tintendrucker.";

   }

}
