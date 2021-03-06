import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class CollatzCalculatorTest {

    private CollatzCalculator collatzCalculator = new CollatzCalculator();

    @Test
    public void testZeroStepsRequiredWhenStartingFrom1() {
        assertEquals(0, collatzCalculator.computeStepCount(1));
    }

    @Test
    public void testCorrectNumberOfStepsWhenAllStepsAreDivisions() {
        assertEquals(4, collatzCalculator.computeStepCount(16));
    }

    @Test
    public void testCorrectNumberOfStepsWhenBothStepTypesAreNeeded() {
        assertEquals(9, collatzCalculator.computeStepCount(12));
    }

    @Test
    public void testAVeryLargeInput() {
        assertEquals(152, collatzCalculator.computeStepCount(1000000));
    }

    @Test
    public void testZeroIsConsideredInvalidInput() {
        IllegalArgumentException expected =
            assertThrows(
                IllegalArgumentException.class,
                () -> collatzCalculator.computeStepCount(0));

        assertEquals(expected.getMessage(), "Only natural numbers are allowed");
    }

    @Test
    public void testNegativeIntegerIsConsideredInvalidInput() {
        IllegalArgumentException expected =
            assertThrows(
                IllegalArgumentException.class,
                () -> collatzCalculator.computeStepCount(-15));

        assertEquals(expected.getMessage(), "Only natural numbers are allowed");
    }

}
