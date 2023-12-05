package com.github.jander99.aoc2023.problems;

import com.github.jander99.aoc2023.util.InputLoader;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class Day4Test {

    private static final String TEST_1_INPUT_FILE = "day4/test-1.txt";
    private static final String TEST_2_INPUT_FILE = "day4/test-2.txt";
    private static final String REAL_INPUT_FILE = "day4/input.txt";

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

        Day4 day4 = new Day4();
        Integer testCardTotal = day4.getTotalCardValue(test1InputList);
        assertEquals(13, testCardTotal);

        Integer realCardTotal = day4.getTotalCardValue(realInputList);
        log.info("Day 4, Part 1: {}", realCardTotal);

        // Answer
        assertEquals(27845, realCardTotal);
    }

    @Test
    public void partTwo() {
        assert(true);

        Day4 day4 = new Day4();
        Integer testTotalCards = day4.getTotalNumberOfCards(test2InputList);
        assertEquals(30, testTotalCards);

        Integer realTotalCards = day4.getTotalNumberOfCards(realInputList);
        log.info("Day 4, Part 1: {}", realTotalCards);


    }

}
