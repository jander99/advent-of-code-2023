package com.github.jander99.aoc2023.problems;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Slf4j
public class Day1 {

    private static final Map<String, String> DIGIT_WORDS = new LinkedHashMap<>();

    static {
        DIGIT_WORDS.put("three", "3");
        DIGIT_WORDS.put("seven", "7");
        DIGIT_WORDS.put("eight", "8");
        DIGIT_WORDS.put("five", "5");
        DIGIT_WORDS.put("four", "4");
        DIGIT_WORDS.put("nine", "9");
        DIGIT_WORDS.put("two", "2");
        DIGIT_WORDS.put("six", "6");
        DIGIT_WORDS.put("one", "1");
    }


    public Integer calibrationSum(List<String> testInputList, boolean filtered) {

        //TODO: Figure out why my implementation is off by 4.

        Integer sum = 0;
        Integer sum2 = 0;

        for (String line : testInputList) {
            Integer lineSum = computeIntegerFromLine(line, filtered);
            Integer secondOpinion = getRealNumber(getCalibrationValueDigits(line, filtered));

            sum += lineSum;
            sum2 += secondOpinion;
        }

        log.info("Sum: {}, Second Opinion: {}", sum, sum2);

        return testInputList.stream().mapToInt(s -> computeIntegerFromLine(s, filtered)).sum();
    }

    private Integer computeIntegerFromLine(String line, boolean filter) {
        if (filter) {
            line = replaceWordsWithDigits(line);
        }
        String firstDigit = String.valueOf(findFirstDigitCharacter(line));
        String lastDigit = String.valueOf(findFirstDigitCharacter(new StringBuilder(line).reverse().toString()));

        return Integer.parseInt(firstDigit + lastDigit);
    }

    private char findFirstDigitCharacter(String line) {
        for (char c : line.toCharArray()) {
            if (Character.isDigit(c)) {
                return c;
            }
        }
        log.error("Oh no, something went wrong with {}", line);
        return '\0';
    }

    public String replaceWordsWithDigits(String input) {
        for (int i = 0; i < input.length(); i++) {
            // Check segments of length 3, 4, and 5
            for (int len = 3; len <= 5; len++) {
                if (i + len <= input.length()) {
                    String segment = input.substring(i, i + len);
                    if (DIGIT_WORDS.containsKey(segment)) {
                        // Replace the first matching segment
                        String replaced = input.substring(0, i) + DIGIT_WORDS.get(segment)
                                + input.substring(i + len);
                        // Recursively process the updated string
                        return replaceWordsWithDigits(replaced);
                    }
                }
            }
        }
        // Return the string when no more replacements can be made
        return input;
    }





    public static int[] getCalibrationValueDigits(String line, boolean readSpelledDigits) {
        StringBuilder readPart = new StringBuilder();
        int firstDigit = -1;
        int lastDigit = -1;

        for (int i = 0; i < line.length(); i++) {
            int digit = -1;
            char c = line.charAt(i);
            readPart.append(c);

            if (c >= '0' && c <= '9') {
                digit = c - '0';
            } else if (readSpelledDigits) {
                digit = getSpelledDigit(readPart.toString());
            }

            if (digit == -1) continue;

            if (firstDigit == -1) {
                firstDigit = digit;
            }

            lastDigit = digit;
        }

        return new int[]{firstDigit, lastDigit};
    }

    public static int getSpelledDigit(String str) {
        int digit = -1;

        for (String key : DIGIT_WORDS.keySet()) {
            if (key.length() > str.length()) continue;

            String subbed = str.substring(str.length() - key.length());
            if (!subbed.equals(key)) continue;

            digit = Integer.parseInt(DIGIT_WORDS.get(key));
            break;
        }

        return digit;
    }

    public static int getRealNumber(int[] a) {
        return a[0]*10 + a[1];
    }

}
