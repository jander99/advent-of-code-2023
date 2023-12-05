package com.github.jander99.aoc2023.problems;

import com.github.jander99.aoc2023.util.InputLoader;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class Day3Test {

    private static final String TEST_1_INPUT_FILE = "day3/test-1.txt";
    private static final String TEST_2_INPUT_FILE = "day3/test-2.txt";
    private static final String REAL_INPUT_FILE = "day3/input.txt";

    private static char[][] test1InputList;
    private static char[][] test2InputList;
    private static char[][] realInputList;


    @BeforeAll
    static void setup() {
        test1InputList = InputLoader.readCharacterArrayFromInputFile(TEST_1_INPUT_FILE);
        test2InputList = InputLoader.readCharacterArrayFromInputFile(TEST_2_INPUT_FILE);
        realInputList = InputLoader.readCharacterArrayFromInputFile(REAL_INPUT_FILE);
    }

    @Test
    public void partOne() {
        assert(true);

        Day3 day3 = new Day3();
        day3.loadSchematic(test1InputList);
        int testPartSum = day3.sumPartNumbers();
        assertEquals(4361, testPartSum);

        day3.loadSchematic(realInputList);
        int realPartSum = day3.sumPartNumbers();
        log.info("Day 3, Part 1: {}", realPartSum);

        // Answer
        assertEquals(527364, realPartSum);

    }

    @Test
    public void partTwo() {
        assert(true);

        Day3 day3 = new Day3();
        day3.loadSchematic(test2InputList);
        var testGears = day3.calculateTotalGearRatio();
        assertEquals(467835, testGears);

        day3.loadSchematic(realInputList);
        var realGears = day3.calculateTotalGearRatio();
        log.info("Day 3, Part 2: {}", realGears);

        // Answer
    }

}
