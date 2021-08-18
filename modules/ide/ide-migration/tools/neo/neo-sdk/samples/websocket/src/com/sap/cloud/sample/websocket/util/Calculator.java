package com.sap.cloud.sample.websocket.util;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a utility class which given a string of numbers separated by columns calculates their sum.
 */

public final class Calculator {

    private static final String SEPARATOR = ";";

    /**
     * Calculates the sum of n numbers.
     *
     * @param input a string containing a list of numbers, separated by semicolons, e.g. 1;2;3
     * @return
     */
    public static String calculateSum(String input) {
        List<Integer> numbers = parseNumbers(input);
        int sum = sum(numbers);
        return String.valueOf(sum);
    }

    private Calculator() {
    }

    private static List<Integer> parseNumbers(String input) {
        String[] numbers = input.split(SEPARATOR);
        ArrayList<Integer> result = new ArrayList<Integer>(numbers.length);
        for (String number : numbers) {
            try {
                result.add(Integer.parseInt(number.trim()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[" + number + "] in message [" + input + "] is not a valid number");
            }
        }

        return result;
    }

    private static int sum(List<Integer> numbers) {
        int sum = 0;
        for (Integer number : numbers) {
            sum = sum + number;
        }

        return sum;
    }
}
