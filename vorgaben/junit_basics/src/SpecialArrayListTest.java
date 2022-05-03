import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

/**
 * Parametrisierter Test
 */

@RunWith(Parameterized.class)
public class SpecialArrayListTest {

    @Parameter(0)
    public String s1;

    @Parameter(1)
    public String s2;

    @Parameter(2)
    public String erg;


    @Parameters
    public static Collection<Object[]> values() {
        return Arrays.asList(new Object[][]{{"", "", ""}, {"", "a", "a"}, {"a", "", "a"}, {"abc", "123", "abc123"}});
    }

    @Test
    public void testConcat(){
        SpecialArrayList sal = new SpecialArrayList();
        sal.concatAddStrings(s1,s2);
        assertTrue(sal.get(0).equals(erg));

    }


}
