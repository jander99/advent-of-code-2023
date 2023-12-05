package com.github.jander99.aoc2023.problems;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Day3 {

    private char[][] schematic;

    public void loadSchematic(char[][] schematic) {
        this.schematic = schematic;
    }

    public int sumPartNumbers() {

        int sum = 0;

        for (int row=0; row < schematic.length; row++) {
            StringBuilder numberBuilder = new StringBuilder();
            for (int col = 0; col < schematic[row].length; col++) {
                char currentChar = schematic[row][col];
                if (Character.isDigit(currentChar)) {
                    numberBuilder.append(currentChar);
                } else {
                    if (!numberBuilder.isEmpty()) {

                        int numberLength = numberBuilder.length();
                        int numberStartCol = col - numberLength;
                        if (isAdjacentToSymbol(row, numberStartCol, numberLength)) {
                            sum += Integer.parseInt(numberBuilder.toString());
                        }

                        numberBuilder.setLength(0);
                    }
                }
            }

            if (!numberBuilder.isEmpty()) {

                int numberLength = numberBuilder.length();
                int numberStartCol = schematic[row].length - numberLength;
                if (isAdjacentToSymbol(row, numberStartCol, numberLength)) {
                    sum += Integer.parseInt(numberBuilder.toString());
                }

                numberBuilder.setLength(0);
            }
        }

        return sum;
    }

    public List<Pair<Integer, Integer>> findGears() {
        List<Pair<Integer, Integer>> gearPositions = new ArrayList<>();

        for (int row = 0; row < schematic.length; row++) {
            for (int col = 0; col <schematic[row].length; col++) {
                if (schematic[row][col] == '*') {
                    gearPositions.add(Pair.of(row, col));
                }
            }
        }

        List<Pair<Integer, Integer>> refinedGears = gearPositions.stream()
                        .filter(gear -> isValidGear(gear.getLeft(), gear.getRight()))
                        .toList();

        return refinedGears;
    }

    private boolean isAdjacentToSymbol(int startRow, int startCol, int length) {
        int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

        for (int i = 0; i < length; i++) {
            int currentCol = startCol + i;
            for (int j = 0; j < 8; j++) {
                int newRow = startRow + dx[j];
                int newCol = currentCol + dy[j];

                if (newRow >= 0 && newRow < schematic.length && newCol >= 0 && newCol < schematic[0].length) {
                    if (isSymbol(schematic[newRow][newCol])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isSymbol(char ch) {
        return !Character.isDigit(ch) && ch != '.';
    }


    private boolean isValidGear(int row, int col) {

        int topRowDigitCount = getRowDigitCount(row-1, col);
        int middleRowDigitCount = getRowDigitCount(row, col);
        int bottomRowDigitCount = getRowDigitCount(row+1, col);

        int totalDigits = topRowDigitCount + middleRowDigitCount + bottomRowDigitCount;

        return totalDigits == 2;
    }

    private boolean isDigit(int row, int col) {
        if (row >= 0 && row < schematic.length &&
                col >= 0 && col < schematic[row].length) {
            return Character.isDigit(schematic[row][col]);
        }
        return false;
    }

    private int getRowDigitCount(int row, int col) {
        boolean left = isDigit(row, col-1);
        boolean center = isDigit(row, col);
        boolean right = isDigit(row, col+1);

        if(left && !center && !right) {
            return 1;
        } else if (!left && !center && right) {
            return 1;
        } else if (left && right && !center) {
            return 2;
        } else if ((left && center) || (center & right)) {
            return 1;
        }
        return 0;
    }

    public Integer calculateTotalGearRatio() {

        Integer sum = 0;

        List<Pair<Integer, Integer>> validGears = findGears();

        for (Pair<Integer, Integer> gear : validGears) {
            Integer ratio = findGearRatio(gear.getLeft(), gear.getRight());
            sum += ratio;
        }

        return sum;
    }

    private Integer findGearRatio(int row, int col) {
        List<Integer> allNumbers = new ArrayList<>();

        for (int dy = -1; dy <= 1; dy++) {
            allNumbers.addAll(extractNumbersFromRow(row + dy, col));
        }

        if (allNumbers.size() == 2) {
            log.info("Gear ({},{}): {} * {} ", row, col, allNumbers.get(0), allNumbers.get(1));
            return allNumbers.get(0) * allNumbers.get(1);
        } else {
            log.info("Encountered gear at ({},{}) with {} numbers surrounding it. This shouldn't happen.", row, col, allNumbers.size());
            log.info(allNumbers.toString());
        }
        return 0;
    }

    private List<Integer> extractNumbersFromRow(int row, int col) {
        List<Integer> numbers = new ArrayList<>();

        boolean left = isDigit(row, col-1);
        boolean center = isDigit(row, col);
        boolean right = isDigit(row, col+1);

        if(left && center && right) {
            StringBuilder b = new StringBuilder();
            b.append(schematic[row][col-1]);
            b.append(schematic[row][col]);
            b.append(schematic[row][col+1]);
            numbers.add(Integer.parseInt(b.toString()));
        }

        if (!left && center && !right) { // Checks around center position
            numbers.add(Integer.parseInt(String.valueOf(schematic[row][col])));
        } else if (left && center && !right) {
            StringBuilder b = new StringBuilder();
            if(isDigit(row,col-2)) {
                b.append(schematic[row][col-2]);
            }
            b.append(schematic[row][col-1]);
            b.append(schematic[row][col]);

            numbers.add(Integer.parseInt(b.toString()));

        } else if (!left && center && right) {
            StringBuilder b = new StringBuilder();
            b.append(schematic[row][col]);
            b.append(schematic[row][col+1]);

            if(isDigit(row,col+2)) {
                b.append(schematic[row][col+2]);
            }

            numbers.add(Integer.parseInt(b.toString()));
        }

        if(left && !center) {
            StringBuilder b = new StringBuilder();
            if(isDigit(row, col-2)) {
                if (isDigit(row,col-3)) {
                    b.append(schematic[row][col-3]);
                }
                b.append(schematic[row][col-2]);
            }
            b.append(schematic[row][col-1]);

            numbers.add(Integer.parseInt(b.toString()));
        }

        if(!center && right) {
            StringBuilder b = new StringBuilder();
            b.append(schematic[row][col+1]);

            if(isDigit(row, col+2)) {
                b.append(schematic[row][col+2]);
                if (isDigit(row,col+3)) {
                    b.append(schematic[row][col+3]);
                }
            }
            numbers.add(Integer.parseInt(b.toString()));
        }

        return numbers;
    }
}
