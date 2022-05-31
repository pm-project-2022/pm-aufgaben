package calculator;

import operations.*;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class OperationLoader {

    static Logger LOGGER = Logger.getLogger(OperationLoader.class.getName());

    /**
     * Loads the operation classes from a directory.
     *
     * @param directory the directory to search for operation classes
     * @return a map with the operations and their names, or an empty map if none were found
     */
    public static Map<String, IOperation> loadOperations(File directory){

        HashMap<String, IOperation> map = new HashMap<>();

        try {

            URL[] ua = new URL[]{directory.toURI().toURL()};
            URLClassLoader ucl = URLClassLoader.newInstance(ua);

            // Load .class files
            Class<?> add = ucl.loadClass("operations.Addieren");
            Class<?> sub = ucl.loadClass("operations.Subtrahieren");
            Class<?> mul = ucl.loadClass("operations.Multiplizieren");

            LOGGER.info("Loaded .class Files");

            // Create instance from .class Files
            IOperation instanceAdd = (IOperation) add.getDeclaredConstructor().newInstance();
            IOperation instanceSub = (IOperation) sub.getDeclaredConstructor().newInstance();
            IOperation instanceMult = (IOperation) mul.getDeclaredConstructor().newInstance();

            LOGGER.info("Created instances of .class Files");

            // Get Name value from Annotations
            String nameAdd = add.getAnnotation(MathOperation.class).operation();
            String nameSub = sub.getAnnotation(MathOperation.class).operation();
            String nameMulti = mul.getAnnotation(MathOperation.class).operation();

            // fill hash map
            map.put(nameAdd, instanceAdd);
            map.put(nameSub, instanceSub);
            map.put(nameMulti, instanceMult);

        } catch (MalformedURLException | ClassNotFoundException | InvocationTargetException | InstantiationException |
                 IllegalAccessException | NoSuchElementException | NoSuchMethodException e) {
            LOGGER.severe(e.getMessage());
        }

        return map;
    }

}
