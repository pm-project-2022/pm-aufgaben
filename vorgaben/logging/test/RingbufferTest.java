import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

class RingbufferTest {
    Ringbuffer buffer;
    Logger logger;
    ConsoleHandler consoleHandler;

    @BeforeEach
    void setUp() {
        buffer = new Ringbuffer(2);
        logger = Logger.getLogger(RingbufferTest.class.getName());
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);
        consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new ConsoleFormatter());
        logger.addHandler(consoleHandler);
    }

    @Test
    void addSuccessFirstElement() {
        assertDoesNotThrow(() -> buffer.add("1"));
        assertEquals(1, buffer.elementsCount());
    }

    @Test
    void addSuccessLastElement() {
        assertDoesNotThrow(() -> buffer.add("1"));
        assertDoesNotThrow(() -> buffer.add("2"));
        assertEquals(2, buffer.elementsCount());
    }

    @Test
    void addOverLimit() {
        assertDoesNotThrow(() -> buffer.add("1"));
        assertDoesNotThrow(() -> buffer.add("2"));
        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> buffer.add(""));
        logger.severe(thrown.getMessage());
        assertEquals("The Current Buffer is already full.", thrown.getMessage());
    }

    @Test
    void remove() {
        String element = "1";
        assertDoesNotThrow(() -> buffer.add(element));
        assertEquals(element, buffer.remove());
        assertEquals(0, buffer.elementsCount());
    }

    @Test
    void removeEmpty() {
        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> buffer.remove());
        logger.severe(thrown.getMessage());
        assertEquals("The Current Buffer does not contain any element.", thrown.getMessage());
    }

    @Test
    void checkRingAdd() {
        assertDoesNotThrow(() -> buffer.add("1"));
        assertDoesNotThrow(() -> buffer.add("2"));
        assertDoesNotThrow(() -> buffer.remove());
        assertDoesNotThrow(() -> buffer.remove());
        assertDoesNotThrow(() -> buffer.add("3"));
        assertEquals(1, buffer.elementsCount());
    }

    @Test
    void checkRingRemove() {
        assertDoesNotThrow(() -> buffer.add("1"));
        assertDoesNotThrow(() -> buffer.add("2"));
        assertDoesNotThrow(() -> buffer.remove());
        assertDoesNotThrow(() -> buffer.remove());
        assertDoesNotThrow(() -> buffer.add("3"));
        assertDoesNotThrow(() -> buffer.remove());
        assertEquals(0, buffer.elementsCount());
    }
}