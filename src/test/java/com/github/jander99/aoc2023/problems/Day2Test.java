package com.github.jander99.aoc2023.problems;

import com.github.jander99.aoc2023.util.InputLoader;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class Day2Test {

    private static final String TEST_1_INPUT_FILE = "day2/test-1.txt";
    private static final String TEST_2_INPUT_FILE = "day2/test-2.txt";
    private static final String REAL_INPUT_FILE = "day2/input.txt";

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

        Day2 day2 = new Day2();
        day2.setMaximumColors(12, 13, 14);
        Integer testGameIdSum = day2.getPossibleGameIdSum(test1InputList);
        assertEquals(8, testGameIdSum);


        Integer realGameIdSum = day2.getPossibleGameIdSum(realInputList);
        log.info("Day 2, Part 1: {}", realGameIdSum);

        // Answer
        assertEquals(2268, realGameIdSum);
    }

    @Test
    public void partTwo() {

        Day2 day2 = new Day2();
        day2.setMaximumColors(12, 13, 14);
        Integer testGamePowerSum = day2.getGamePowerSum(test2InputList);
        assertEquals(2286, testGamePowerSum);

        Integer realGamePowerSum = day2.getGamePowerSum(realInputList);
        log.info("Day 2, Part 2: {}", realGamePowerSum);
    }
}
