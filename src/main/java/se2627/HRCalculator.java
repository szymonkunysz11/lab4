package se2627;

public class HRCalculator {

    public static int calculateMaxHR(int age) {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Age out of bounds");
        }
        return 220 - age;
    }

    public static String getWorkoutZone(int age, int bpm) {
        if (bpm <= 0) {
            throw new IllegalArgumentException("BPM must be positive");
        }
        double percent = (double) bpm / calculateMaxHR(age);
        if (percent < 0.5) {
            return "Resting";
        } else if (percent < 0.6) {
            return "Recovery";
        } else if (percent < 0.7) {
            return "Fat Burn";
        } else if (percent < 0.8) {
            return "Aerobic";
        } else if (percent < 0.9) {
            return "Anaerobic";
        }
        return "VO2 Max";
    }
}
