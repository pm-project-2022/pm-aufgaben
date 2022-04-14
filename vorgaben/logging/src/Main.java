import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Ringbuffer buffer = new Ringbuffer(5);
        Logger logger = Logger.getLogger(Main.class.getName());
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new ConsoleFormatter());
        logger.addHandler(consoleHandler);

        try {
            buffer.remove();
        } catch (IllegalStateException stateException) {
            logger.warning(stateException.getMessage());
            //System.err.println(stateException.getMessage());
        }
        buffer.add("First");
        //System.out.println("First element added succesfully");
        buffer.add("Second");
        //System.out.println("Second element added Succesfully");
        try {
            buffer.add("Third");
            //System.out.println("Third element added Succesfully");
        } catch (IllegalStateException stateException) {
            logger.warning(stateException.getMessage());
            //System.err.println(stateException.getMessage());
        }
        String first = buffer.remove();
        logger.info("The removed element is '" + first+"'");
        //System.out.printf("The removed element is: %s", first);
    }
}
