package mocking;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import mocking.Utility;

// Ergänzen Sie UtilityTest so, dass alle Testmethoden grün werden.
// Die vorgegebenen Klassen dürfen nicht geändert werden.
// Die Testaufrufe müssen auch erhalten bleiben.
public class UtilityTest {
    private Utility utilityClass;
    private Evil evilClass;
    // Initialisieren Sie die Attribute entsprechend vor jedem Test.

    @Before
    public void setUp(){
        this.evilClass = mock(Evil.class);
        this.utilityClass = spy(new Utility(this.evilClass));
    }

    @Test
    public void test_nonEvilAdd() {
        Assert.assertEquals(10, utilityClass.nonEvilAdd(9, 1));
    }

    @Test
    public void test_evilAdd() {
        Assert.assertEquals(10, utilityClass.evilAdd(9, 1));
    }

    @Test
    public void test_veryEvilAdd() {
        doNothing().when(this.utilityClass).evilMethod();
        utilityClass.veryEvilAdd(9, 1);
        Assert.assertEquals(10, utilityClass.getIntResult());
    }
}
