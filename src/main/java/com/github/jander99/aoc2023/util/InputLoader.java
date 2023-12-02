package com.github.jander99.aoc2023.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class InputLoader {

    public static List<String> readLinesFromInputFile(String file) {
        List<String> fileLines = new ArrayList<>();

        try {
            fileLines = Files.readAllLines(Paths.get("src/main/resources/" + file));
        } catch (IOException e) {
            log.error("Unable to load file");
        }
        return fileLines;
    }

    public static List<Integer> readIntegerLinesFromInputFile(String file) {
        return readLinesFromInputFile(file)
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<Integer> readSingleLineIntegerArrayFromInputFile(String file) {
        return Arrays.stream(readLinesFromInputFile(file)
                .get(0)
                .split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }
}
