package bad_smells_und_refactoring;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    ArticleTest.class,
    BikeTest.class,
    BromptonTest.class,
    CustomerDataTest.class,
    EbikeTest.class,
    MountainbikeTest.class
}) 

public class MyTestSuite {
    
}
