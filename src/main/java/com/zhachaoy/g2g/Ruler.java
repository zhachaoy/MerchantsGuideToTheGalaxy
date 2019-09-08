package com.zhachaoy.g2g;

import java.util.*;

/**
 * Ruler class
 *
 * @author zhachaoy@163.com
 * @date 2019/09/08
 */
public class Ruler {

    // Define the number of repeated characters and repetitions that are not allowed
    private static final Character[] NonRepeatingRomanNumerals = {'D', 'L', 'V'};
    private static Map<Character, Integer> NonRepeatableLiteralsCount = getNonRepeatableLiteralsCount();

    // Define characters and consecutive repeat words that are allowed to repeat continuously
    private static final Character[] RepeatingRomanNumerals = {'I', 'X', 'C', 'M'};
    private static Map<Character, Integer> RepeatableLiteralsCount = getRepeatableLiteralsCount();

    private static Map<Integer, Integer[]> ROMAN_SUBTRACTABLE_MAPPING = Collections.unmodifiableMap(
            new HashMap<Integer, Integer[]>() {
                {
                    put(1, new Integer[]{5, 10});
                    put(5, new Integer[]{});
                    put(10, new Integer[]{50, 100});
                    put(50, new Integer[]{});
                    put(100, new Integer[]{100, 1000});
                    put(500, new Integer[]{});
                    put(1000, new Integer[]{});
                }
            });

    private static Map<Character, Integer> ROMAN_TO_NUMERIC_MAPPING = Collections.unmodifiableMap(
            new HashMap<Character, Integer>() {
                {
                    put('I', 1);
                    put('V', 5);
                    put('X', 10);
                    put('L', 50);
                    put('C', 100);
                    put('D', 500);
                    put('M', 1000);
                }
            });

    /**
     * checkLiteralCountValidity() method keeps the count of all repeatable and non repeatable Literals.
     */
    public static void checkLiteralCountValidity(Character CurrentLiteral) {
        if (checkIfLiteralPresent(NonRepeatingRomanNumerals, CurrentLiteral)) {
            NonRepeatableLiteralsCount.put(CurrentLiteral, NonRepeatableLiteralsCount.get(CurrentLiteral) + 1);
            if (NonRepeatableLiteralsCount.containsValue(2)) {
                System.err.println("Error: D, L, and V can never be repeated.");
                System.exit(0);
            }
        } else if (checkIfLiteralPresent(RepeatingRomanNumerals, CurrentLiteral)) {
            Character keyForValueContainingThree = getKeyForValueContainingThree();
            if (keyForValueContainingThree != '\0') {
                if (CurrentLiteral.equals(keyForValueContainingThree)) {
                    System.err.println("Error: The symbols I, X, C, and M can be repeated three times in succession, but no more.");
                    System.exit(0);
                } else if (currentLiteralSmallerThanPrevious(CurrentLiteral, keyForValueContainingThree)) {
                    RepeatableLiteralsCount.put(CurrentLiteral, RepeatableLiteralsCount.get(CurrentLiteral) + 1);
                    RepeatableLiteralsCount.put(keyForValueContainingThree, 0);
                }
            } else {
                RepeatableLiteralsCount.put(CurrentLiteral, RepeatableLiteralsCount.get(CurrentLiteral) + 1);
            }
        }
    }


    /**
     * Applies the subtaction logic and checks if the element is subtractable by the other or not.
     *
     * @param lastDecimal
     * @param decimal
     * @param lastNumber
     * @return
     */
    public static float subtractionLogic(Float lastDecimal, Float decimal, Float lastNumber) {
        if (Arrays.asList(ROMAN_SUBTRACTABLE_MAPPING.get(Math.round(decimal))).contains(Math.round(lastNumber))) {
            return lastDecimal - decimal;
        } else {
            return lastDecimal + decimal;
        }
    }

    /**
     * Checks if an element is present in an array
     *
     * @param array
     * @param literal
     * @return
     */
    public static boolean checkIfLiteralPresent(Character[] array, Character literal) {
        boolean result = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(literal)) {
                result = true;
            }
        }
        return result;
    }

    public static void resetLiteralsCounter() {
        RepeatableLiteralsCount = getRepeatableLiteralsCount();
        NonRepeatableLiteralsCount = getNonRepeatableLiteralsCount();
    }

    /**
     * @return 不可以重复的字符已经重复的次数
     */
    private static Map<Character, Integer> getNonRepeatableLiteralsCount() {
        Map<Character, Integer> map = new HashMap<Character, Integer>() {
            {
                put('V', 0);
                put('L', 0);
                put('D', 0);
            }
        };
        return map;
    }

    /**
     * @return 可以连续重复的字符已经连续重复的次数
     */
    private static Map<Character, Integer> getRepeatableLiteralsCount() {
        Map<Character, Integer> map = new HashMap<Character, Integer>() {
            {
                put('I', 0);
                put('X', 0);
                put('C', 0);
                put('M', 0);
            }
        };
        return map;
    }

    /**
     * getKeyForValueContainingThree() method returns the key corresponding to value 3.This is used while checking the count of Literals
     * that cannot be repeated more than 3 times.
     */
    private static char getKeyForValueContainingThree() {
        char key = '\0';
        Iterator<Map.Entry<Character, Integer>> iter = RepeatableLiteralsCount.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Character, Integer> entry = iter.next();
            if (entry.getValue().equals(3)) {
                return Character.valueOf(entry.getKey());
            }
        }
        return key;
    }

    /**
     * checks if currentLiteral is smaller than the previous one. This rule is applied when repeatable literals can
     * occur 4 times only if the 3rd and 4th occurance has a smaller value between them.
     *
     * @param CurrentLiteral
     * @param keyForValueContainingThree
     * @return
     */
    private static boolean currentLiteralSmallerThanPrevious(char CurrentLiteral, char keyForValueContainingThree) {
        if (ROMAN_TO_NUMERIC_MAPPING.get(CurrentLiteral) > ROMAN_TO_NUMERIC_MAPPING.get(keyForValueContainingThree)) {
            System.err.println("Error : Should have been a lesser Roman Numeral next instead of " + CurrentLiteral);
            System.exit(0);
            return false;
        } else {
            return true;
        }
    }
}