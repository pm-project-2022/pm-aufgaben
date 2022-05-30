package reflection.calculator;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import java.util.logging.Logger;

import reflection.operations.IOperation;

public class OperationLoader {
    private static Logger log = Logger.getLogger(OperationLoader.class.getName());

    /**
     * Loads the operation classes from a directory.
     *
     * @param directory the directory to search for operation classes
     * @return a map with the operations and their names, or an empty map if none
     *         were found
     */
    public static Map<String, IOperation> loadOperations(File directory) {
        Map<String, IOperation> operations = new HashMap<>();
        log.info("Operationen werden eingelesen");
        try {

            URL[] ua = new URL[] { directory.toURI().toURL() };
            URLClassLoader ucl = URLClassLoader.newInstance(ua);
            Class<?> classAddition = ucl.loadClass("Addition");
            Class<?> classSubtraktion = ucl.loadClass("Subtraktion");
            Class<?> classMultiplikation = ucl.loadClass("Multiplikation");
            Class<?> classDivision = ucl.loadClass("Division");

            IOperation addition = (IOperation) classAddition.getDeclaredConstructor().newInstance();
            IOperation subtraktion = (IOperation) classAddition.getDeclaredConstructor().newInstance();
            IOperation multiplikation = (IOperation) classAddition.getDeclaredConstructor().newInstance();
            IOperation division = (IOperation) classAddition.getDeclaredConstructor().newInstance();

        } catch (MalformedURLException e) {
            log.severe("Argument ist null oder das Protokoll ist unbekannt");
        } catch (ClassNotFoundException e) {
            log.severe("Klasse konnte nicht gefunden werden.");
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
