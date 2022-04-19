package ringbuffer;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple Ringbuffer for String objects.
 */
public class Ringbuffer {
    private final String[] buffer;
    private int start, elements;
    private Logger logger;
    private ConsoleHandler consoleHandler;
    private FileHandler fileHandler, fileHandler2;

    /**
     * Constructor for the buffer which creates the String array for the storage.
     *
     * @param size needs to be at least 1
     * @throws IllegalArgumentException when the size is below or equal to 0
     */
    public Ringbuffer(int size) {
        loggerInit();
        if (size <= 0) {
            this.logger.severe("The Size of the buffer needs to be at least 1.");
            //System.err.println("The Size of the buffer needs to be at least 1.");
            throw new IllegalArgumentException("The Size of the buffer needs to be at least 1.");
        }
        
        this.logger.info("Creating array with size of " + size + " for storage.");
        //System.out.println("Creating array with size of " + size + " for storage.");
        buffer = new String[size];
    }

    /**
     * Initiates the logger and the console/filehandler
     */
    private void loggerInit(){
        this.logger = Logger.getLogger(Ringbuffer.class.getName());
        this.logger.setUseParentHandlers(false);
        this.logger.setLevel(Level.ALL);
        this.consoleHandler = new ConsoleHandler();
        this.consoleHandler.setLevel(Level.ALL);
        this.consoleHandler.setFormatter(new ConsoleFormatter());
        this.logger.addHandler(consoleHandler);
        try {
            this.fileHandler = new FileHandler("logWarningsSevere.csv", true);
            this.fileHandler2 = new FileHandler("logAll.txt", true);
            this.fileHandler.setLevel(Level.WARNING);
            this.fileHandler2.setLevel(Level.ALL);
            this.fileHandler.setFormatter(new FileFormatter());
            this.fileHandler2.setFormatter(new FileFormatter());
            this.logger.addHandler(fileHandler);
            this.logger.addHandler(fileHandler2);
        } catch (SecurityException e) {
            this.logger.severe(e.getMessage());
        } catch (IOException e) {
            this.logger.severe(e.getMessage());
        } 
    }

    /**
     * Adds an Element at the position of the storage marker.
     *
     * @param element any String element which should be buffered.
     * @throws IllegalStateException when the method is called and there is no empty space available.
     */
    public void add(String element) {
        if (elements == buffer.length) {
            this.logger.warning("The Current Buffer is already full.");
            //System.err.println("The Current Buffer is already full.");
            throw new IllegalStateException("The Current Buffer is already full.");
        }
        
        this.logger.info("Adding '" + element + "' to buffer on position " + (start + elements) % buffer.length);
        //System.out.println("Adding " + element + " to buffer on position " + (start + elements) % buffer.length);
        buffer[(start + elements) % buffer.length] = element;
        this.logger.fine("Increasing Element count by 1 to " + (elements + 1));
        //System.out.println("Increasing Element count by 1 to " + (elements + 1));
        ++elements;
    }

    /**
     * Removes the first Element at the position of the storage marker.
     *
     * @throws IllegalStateException when the method is called and there is no element available.
     * @return the removed Element.
     */
    public String remove() {
        this.logger.info("Currently the buffer does contain: " + elements + " elements");
        //System.out.println("Currently the buffer does contain: " + elements + " elements");
        if (elements == 0) {
            //System.err.println("The Current Buffer does not contain any element.");
            this.logger.warning("The Current Buffer does not contain any element.");
            throw new IllegalStateException("The Current Buffer does not contain any element.");
        }
        String s = buffer[start];
        this.logger.info("Moving element from buffer to temporary variable the value: " + s);
        //System.out.println("Moving element from buffer to temporary variable the value: " + s);
        this.logger.info("Moving the start pointer from " + start + " to " + (start + 1) % buffer.length);
        //System.out.println("Moving the start pointer from " + start + " to " + (start + 1) % buffer.length);
        start = (start + 1) % buffer.length;
        this.logger.fine("Decreasing Element count by 1 to " + (elements - 1));
        //System.out.println("Decreasing Element count by 1 to " + (elements - 1));
        elements--;
        return s;
    }

    /**
     * Gives the number of how much space is still available to be filled.
     *
     * @return the number of empty Spaces
     */
    public int emptySpace() {
        this.logger.info("Method emptySpace current elementCount is " + elements + " elements and the buffer has a size of " + buffer.length + ".");
        //System.out.println("Method emptySpace current elementCount is " + elements + " elements and the buffer has a size of " + buffer.length + ".");
        return buffer.length - elements;
    }

    /**
     * Gives the amount of elements currently held in the buffer
     *
     * @return the amount of elements currently held in the buffer
     */
    public int elementsCount() {
        this.logger.info("Method elementsCount current count is " + elements + " elements.");
        //System.out.println("Method elementsCount current count is " + elements + " elements.");
        return elements;
    }
}
