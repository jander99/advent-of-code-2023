package com.github.jander99.aoc2023.problems;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

@Slf4j
public class Day1 {

    private static final Map<String, Integer> DIGIT_WORDS = new LinkedHashMap<>();

    static {
        DIGIT_WORDS.put("one", 1);
        DIGIT_WORDS.put("two", 2);
        DIGIT_WORDS.put("three", 3);
        DIGIT_WORDS.put("four", 4);
        DIGIT_WORDS.put("five", 5);
        DIGIT_WORDS.put("six", 6);
        DIGIT_WORDS.put("seven", 7);
        DIGIT_WORDS.put("eight", 8);
        DIGIT_WORDS.put("nine", 9);
    }


    public Integer calibrationSum(List<String> testInputList, boolean readSpelledDigits) {
        return testInputList.stream().mapToInt(s -> getCalibrationValueDigits(s, readSpelledDigits)).sum();
    }

    public static int getCalibrationValueDigits(String line, boolean readSpelledDigits) {
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

        return firstDigit*10 + lastDigit;
    }

    public static int getSpelledDigit(String str) {
        OptionalInt digit = DIGIT_WORDS.entrySet().stream()
                .filter(entry -> str.endsWith(entry.getKey()))
                .mapToInt(Map.Entry::getValue)
                .findFirst();

        return digit.orElse(-1);
    }

}
