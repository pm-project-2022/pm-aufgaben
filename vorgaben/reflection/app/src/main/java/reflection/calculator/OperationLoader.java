package reflection.calculator;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
        try {

            URL[] ua = new URL[] { directory.toURI().toURL() };
            URLClassLoader ucl = URLClassLoader.newInstance(ua);
            Class<?> classAddition = Class.forName("Addition", true, ucl);

            Object eve = classAddition.getDeclaredConstructor().newInstance();
            Class<?>[] params = new Class<?>[]{int.class, int.class};
            Method method = classAddition.getMethod("doOperation", params);
            log.info(String.valueOf(method.invoke(eve, 1,2)));

            //Annotation annotation = classAddition.getDeclaredAnnotations()[0];

        } catch (MalformedURLException e) {
            log.severe("Argument ist null oder das Protokoll ist unbekannt");
        } catch (ClassNotFoundException e) {
            log.severe("Klasse konnte nicht gefunden werden.");
        } catch (SecurityException e) {
            
            e.printStackTrace();
        } catch (InstantiationException e) {
            
            e.printStackTrace();
        } catch (IllegalAccessException e) {
           
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
           
            e.printStackTrace();
        } catch (InvocationTargetException e) {
           
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
           
            e.printStackTrace();
        }

        return operations;
    }
}
