package ringbuffer;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static Logger logger;
    private static ConsoleHandler consoleHandler;

    public static void initLogger(){
        logger = Logger.getLogger(Main.class.getName());
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);
        consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new ConsoleFormatter());
        logger.addHandler(consoleHandler);
    }
    
    public static void main(String[] args) {
        initLogger();
        Ringbuffer buffer = new Ringbuffer(5);

        try {
            logger.fine("The removed element is: '" + buffer.remove() +"'" );
        } catch (IllegalStateException stateException) {
            logger.warning(stateException.getMessage());
            //System.err.println(stateException.getMessage());
        }
        buffer.add("First");
        //System.out.println("First element added succesfully");
        logger.fine("First element added succesfully");
        buffer.add("Second");
        //System.out.println("Second element added Succesfully");
        logger.fine("Second element added succesfully");
        try {
            buffer.add("Third");
            //System.out.println("Third element added Succesfully");
            logger.fine("Third element added succesfully");
        } catch (IllegalStateException stateException) {
            logger.warning(stateException.getMessage());
            //System.err.println(stateException.getMessage());
        }
        String first = buffer.remove();
        logger.info("The removed element is: '" + first +"'" );
        //System.out.printf("The removed element is: %s", first);
    }
}
