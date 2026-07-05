import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {

    private Calculator calculator = new Calculator();

    @Test
    public void testAdd() {
        assertEquals(5, calculator.add(2, 3));
    }

}
