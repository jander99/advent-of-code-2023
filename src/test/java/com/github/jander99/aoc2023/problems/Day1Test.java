package com.github.jander99.aoc2023.problems;

import com.github.jander99.aoc2023.util.InputLoader;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class Day1Test {

    private static final String TEST_1_INPUT_FILE = "day1/test-1.txt";
    private static final String TEST_2_INPUT_FILE = "day1/test-2.txt";
    private static final String REAL_INPUT_FILE = "day1/input.txt";

    private static List<String> test1InputList;
    private static List<String> test2InputList;
    private static List<String> realInputList;

    @BeforeAll
    static void setup() {
        test1InputList = InputLoader.readLinesFromInputFile(TEST_1_INPUT_FILE);
        test2InputList = InputLoader.readLinesFromInputFile(TEST_2_INPUT_FILE);
        realInputList = InputLoader.readLinesFromInputFile(REAL_INPUT_FILE);
    }

    @Test
    public void partOne() {

        Day1 day1 = new Day1();
        boolean filtered = false;

        Integer testCalibrationSum = day1.calibrationSum(test1InputList, filtered);
        assertEquals(142, testCalibrationSum);

        Integer realCalibrationSum = day1.calibrationSum(realInputList, filtered);
        log.info("Day 1, Part 1: {}", realCalibrationSum);

        // Answer
        assertEquals(56042, realCalibrationSum);
    }

    @Test
    public void partTwo() {
        Day1 day1 = new Day1();
        boolean filtered = true;

        Integer testCalibrationSum = day1.calibrationSum(test2InputList, filtered);
        assertEquals(281, testCalibrationSum);

        Integer realCalibrationSum = day1.calibrationSum(realInputList, filtered);
        log.info("Day 1, Part 2: {}", realCalibrationSum);

        // Answer
        assertEquals(55358, realCalibrationSum);
    }

    @Test
    public void testReplaceWordsWithDigits() {

        Day1 day1 = new Day1();

        String converted = day1.replaceWordsWithDigits("xtqtwoneeightlvcjqfourckfour2nine");
        assertEquals("xtq2ne8lvcjq4ck429", converted);
    }
}
