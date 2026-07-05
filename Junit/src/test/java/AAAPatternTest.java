import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class AAAPatternTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @After
    public void tearDown() {
        calculator = null;
    }

    @Test
    public void testAdd() {
        int a = 10;
        int b = 20;
        int expected = 30;

        int actual = calculator.add(a, b);

        assertEquals(expected, actual);
    }

}
