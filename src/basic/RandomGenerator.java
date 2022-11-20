package basic;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {

    /**
     * Generates a randomly generated value
     * @param min minimum randomly generated value
     * @param max maximum randomly generated value
     * @return A randomly generated value
     */
    public static int generate(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
