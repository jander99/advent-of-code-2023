package com.github.jander99.aoc2023.problems;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Day2 {

    private static int maxRed;
    private static int maxGreen;
    private static int maxBlue;

    public void setMaximumColors(int red, int green, int blue) {
        maxRed = red;
        maxGreen = green;
        maxBlue = blue;
    }

    public Integer getPossibleGameIdSum(List<String> input) {
        List<Game> games = input.stream().map(this::parseGame).toList();
        return games.stream().filter(this::isGamePossible).mapToInt(Game::getId).sum();
    }

    public Integer getGamePowerSum(List<String> input) {
        List<Game> games = input.stream().map(this::parseGame).toList();
        return games.stream().mapToInt(this::computeGamePower).sum();
    }


    private Game parseGame(String gameLine) {

        String[] parts = gameLine.split(":");
        int gameId = Integer.parseInt(parts[0].replaceAll("[^0-9]", ""));
        Game game = new Game(gameId);

        String[] sets = parts[1].split(";");

        for (String set : sets) {
            String[] items = set.split(",");

            for (String item : items) {
                String[] details = item.trim().split(" ");
                int count = Integer.parseInt(details[0].trim());
                String color = details[1].trim();

                switch (color) {
                    case "red":
                        if (count > game.getRed()) {
                            game.setRed(count);
                        }
                        break;
                    case "green":
                        if (count > game.getGreen()) {
                            game.setGreen(count);
                        }
                        break;
                    case "blue":
                        if (count > game.getBlue()) {
                            game.setBlue(count);
                        }
                        break;
                }
            }
        }

        return game;
    }

    private boolean isGamePossible(Game game) {
        return game.getRed() <= maxRed &&
                game.getGreen() <= maxGreen &&
                game.getBlue() <= maxBlue;
    }

    private Integer computeGamePower(Game game) {
        return game.getRed() * game.getGreen() * game.getBlue();
    }


    @Data
    private static class Game {
        private int id;
        private int red;
        private int green;
        private int blue;

        public Game(int id) {
            this.id = id;
            red = green = blue = 0;
        }
    }
}
