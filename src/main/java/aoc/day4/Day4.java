package aoc.day4;

import java.util.stream.IntStream;

public class Day4 {

    public static int LOWER_VALUE = 197487;
    public static int UPPER_VALUE = 673251;

    public static int calculateNumberOfPasswords() {
        return (int) IntStream.rangeClosed(LOWER_VALUE, UPPER_VALUE).filter(Day4::meetCriteria).count();
    }

    public static int calculateNumberOfPasswordsPt2() {
        return (int) IntStream.rangeClosed(LOWER_VALUE, UPPER_VALUE).filter(Day4::meetCriteriaPart2).count();
    }

    public static boolean meetCriteria(int val) {
        char[] chars = Integer.toString(val).toCharArray();
        return hasDouble(chars) && allIncreasing(chars);
    }

    public static boolean meetCriteriaPart2(int val) {
        char[] chars = Integer.toString(val).toCharArray();
        return hasDoublePart2(chars) && allIncreasing(chars);
    }

    private static boolean allIncreasing(char[] chars) {
        boolean allIncreasing = true;
        for (int i = 0; i < chars.length - 1; i++) {
            allIncreasing &= chars[i] <= chars[i + 1];
        }
        return allIncreasing;
    }

    private static boolean hasDoublePart2(char[] chars) {
        boolean hasTwoAdj = IntStream.range(0, 1).allMatch(p -> chars[p] == chars[p + 1] && chars[p + 1] != chars[p + 2]);
        hasTwoAdj |= IntStream.range(1, chars.length - 2).anyMatch(p -> chars[p] == chars[p + 1] && chars[p + 1] != chars[p + 2] && chars[p] != chars[p - 1]);
        hasTwoAdj |= chars[4] == chars[5] && chars[4] != chars[3];
        return hasTwoAdj;
    }

    private static boolean hasDouble(char[] chars) {
        return IntStream.range(0, chars.length - 1).anyMatch(p -> chars[p] == chars[p + 1]);
    }

}
