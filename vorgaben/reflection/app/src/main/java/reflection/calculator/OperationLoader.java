package reflection.calculator;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.logging.Logger;

import reflection.operations.IOperation;

public class OperationLoader {
    private static Logger log = Logger.getLogger(OperationLoader.class.getName());

    /**
     * Loads the operation classes from a directory.
     *
     * @param directory the directory to search for operation classes
     * @return a map with the operations and their names, or an empty map if none were found
     */
    public static Map<String, IOperation> loadOperations(File directory) {
        log.info("Operationen werden eingelesen");
        try {
            URL[] operations = new URL[]{directory.toURI().toURL()};
            URLClassLoader ucl = URLClassLoader.newInstance(operations);
            Class<?> addition = ucl.loadClass("Addition");
            Class<?> subtraktion = ucl.loadClass("Subtraktion");
            Class<?> multiplikation = ucl.loadClass("Multiplikation");
            Class<?> division = ucl.loadClass("Division");
        } catch (MalformedURLException e) {
            log.severe("Argument ist null oder das Protokoll ist unbekannt");
        } catch (ClassNotFoundException e) {
            log.severe("Klasse konnte nicht gefunden werden.");
        }

        

        return null;
    }
}
