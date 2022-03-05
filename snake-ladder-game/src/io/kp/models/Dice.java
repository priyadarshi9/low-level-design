package io.kp.models;

import java.util.Random;

/**
 * Class denoting the singleton dice object every player has.
 */
public class Dice {

    private int maxValue;
    private int minValue = 1;
    private Random random;

    private static Dice instance = null;

    // We need to make this a singleton.
    private Dice(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        initRandomNumberGenerator();
    }

    // Expose static factory methods for getting the singleton instance.
    public static synchronized Dice getInstance(int minValue, int maxValue) {
        if (instance == null) {
            return new Dice(minValue, maxValue);
        }
        return instance;
    }

    public static synchronized Dice getInstance(int maxValue) {
        if (instance == null) {
            return new Dice(1, maxValue);
        }
        return instance;
    }


    // Initialize the random number generator
    private void initRandomNumberGenerator() {
        random = new Random();
    }

    public Dice(int maxValue) {
        this(1, maxValue);
    }

    /**
     * Method to roll this dice and return a number in the range [minNumber, maxNumber]
     */
    public int roll() {
        return random.nextInt(maxValue) + minValue;
    }

}
