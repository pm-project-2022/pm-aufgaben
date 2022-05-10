package mocking;

import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import mocking.Utility;

// Ergänzen Sie UtilityTest so, dass alle Testmethoden grün werden.
// Die vorgegebenen Klassen dürfen nicht geändert werden.
// Die Testaufrufe müssen auch erhalten bleiben.
public class UtilityTest {
    private Utility utilityClass;
    // Initialisieren Sie die Attribute entsprechend vor jedem Test.

    @Before
    void setUp(){
        this.utilityClass = mock(Utility.class);
    }

    @Test
    void test_nonEvilAdd() {
        Assert.assertEquals(10, utilityClass.nonEvilAdd(9, 1));
    }

    @Test
    void test_evilAdd() {
        Assert.assertEquals(10, utilityClass.evilAdd(9, 1));
    }

    @Test
    void test_veryEvilAdd() {
        utilityClass.veryEvilAdd(9, 1);
        Assert.assertEquals(10, utilityClass.getIntResult());
    }
}
