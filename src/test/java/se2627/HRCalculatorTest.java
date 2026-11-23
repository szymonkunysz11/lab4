package se2627;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HRCalculatorTest {

    @Test
    public void testValidMaxHR() {
        var result = HRCalculator.calculateMaxHR(25);
        assertEquals(195, result);
    }

    @Test
    public void testInvalidMaxHR_ageTooHigh() {
        assertThrows(IllegalArgumentException.class,
                () -> HRCalculator.calculateMaxHR(500));
    }

    @Test
    public void testInvalidMaxHR_ageTooLow() {
        assertThrows(IllegalArgumentException.class,
                () -> HRCalculator.calculateMaxHR(-1));
    }

    @ParameterizedTest
    @CsvSource({
            "20, 1, Resting",
            "20, 99, Resting",
            "20, 100, Recovery",
            "20, 119, Recovery",
            "20, 120, Fat Burn",
            "20, 139, Fat Burn",
            "20, 140, Aerobic",
            "20, 159, Aerobic",
            "20, 160, Anaerobic",
            "20, 179, Anaerobic",
            "20, 180, VO2 Max",
            "20, 250, VO2 Max",
            "40, 150, Anaerobic"
    })
    public void testWorkoutZone(int age, int bpm, String expectedZone) {
        assertEquals(expectedZone, HRCalculator.getWorkoutZone(age, bpm));
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 100",
            "121, 100",
            "500, 100",
            "20, 0",
            "20, -10"
    })
    public void testInvalidWorkoutZone(int age, int bpm) {
        assertThrows(IllegalArgumentException.class,
                () -> HRCalculator.getWorkoutZone(age, bpm));
    }
}
