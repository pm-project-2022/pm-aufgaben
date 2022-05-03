import org.junit.*;
import java.util.ArrayList;
import static org.junit.Assume.assumeTrue;

public class JUnit {

    private ArrayList<String> a1;
    @Before
    public void setup() {
        a1 = new ArrayList();
        a1.add("a");
        a1.add("b");
    }

    /**
     * Prüft ob Liste gefüllt ist
     */
    @Test
    public void assume(){
        assumeTrue(a1.get(0).equals("a"));
        assumeTrue(a1.get(1).equals("b"));
    }

    /**
     * Leert die Liste
     */
    @After
    public void tearDown(){
        a1.clear();
        assumeTrue(a1.isEmpty());
    }

    /**
     * Add zur Liste
     */
    @Test
    public void testAdd(){
        a1.add("c");
        Assert.assertEquals(a1.size(),3);
        Assert.assertTrue(a1.get(0).equals("a") && a1.get(1).equals("b") && a1.get(2).equals("c"));
    }

    /**
     * Entfernt Objekt aus der Liste
     */
    @Test
    public void testRemoveObject(){
        a1.remove("a");
        Assert.assertTrue(a1.get(0).equals("b") && a1.size() == 1);
    }

    /**
     * Entfernt Index aus der Liste
     */
    @Test
    public void testRemoveIndex(){
        a1.remove(1);
        Assert.assertTrue(a1.get(0).equals("a") && a1.size() == 1);
    }



}
