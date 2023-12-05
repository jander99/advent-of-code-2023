package com.github.jander99.aoc2023.problems;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class Day4 {

    @Data
    static class Card {
        private HashSet<Integer> winningNumbers;
        private HashSet<Integer> cardNumbers;

        private String cardData;
        private int cardId;

        public Card(String cardData) {
            this.cardData = cardData;
            winningNumbers = new HashSet<>();
            cardNumbers = new HashSet<>();
            this.cardId = extractCardId(cardData);
            parseCardData();
        }

        private int extractCardId(String cardData) {
            String idStr = cardData.substring(5, cardData.indexOf(':')).trim();
            return Integer.parseInt(idStr);
        }


        private void parseCardData() {
            String data = cardData.substring(cardData.indexOf(':') + 1);
            StringTokenizer tokenizer = new StringTokenizer(data, "|");
            String winningNums = tokenizer.nextToken().trim();
            String yourNums = tokenizer.nextToken().trim();

            for (String num : winningNums.split("\\s+")) {
                winningNumbers.add(Integer.parseInt(num));
            }

            for (String num : yourNums.split("\\s+")) {
                cardNumbers.add(Integer.parseInt(num));
            }
        }

        public int calculateCardScore() {
            int score = 0;
            for (Integer number : cardNumbers) {
                if (winningNumbers.contains(number)) {
                    score = score == 0 ? 1 : score * 2;
                }
            }
            return score;
        }

        public int getNumberOfMatches() {
            int matches = 0;
            for (Integer number : cardNumbers) {
                if (winningNumbers.contains(number)) {
                    matches++;
                }
            }
            return matches;
        }
    }

    public Integer getTotalCardValue(List<String> cards) {

        int totalScore = 0;
        for (String cardData : cards) {
            Card card = new Card(cardData);
            totalScore += card.calculateCardScore();
        }

        return totalScore;
    }

//    public Integer getTotalNumberOfCards(List<String> cards) {
//
//        int totalCards = 0;
//        List<Card> cardQueue = new ArrayList<>();
//
//        for (String cardData : cards) {
//            Card newCard = new Card(cardData);
//            log.info("Adding new Card {} to queue.", newCard.getCardId());
//            cardQueue.add(newCard);
//        }
//
//        while(!cardQueue.isEmpty()) {
//            Card currentCard = cardQueue.remove(0);
//            totalCards++;
//
//            int matches = currentCard.getNumberOfMatches();
//            log.info("Card {} has {} matches.", currentCard.getCardId(), matches);
//            for (int i = 1; i<= matches; i++) {
//                int nextCardId = currentCard.getCardId() + i;
//                if (nextCardId < cards.size()) {
//                    log.info("Adding Card {} to queue.", nextCardId);
//                    cardQueue.add(new Card(cards.get(nextCardId)));
//                }
//            }
//        }
//        return totalCards;
//    }

    public Integer getTotalNumberOfCards(List<String> cards) {
        int[] cardCounts = new int[cards.size()];
        Arrays.fill(cardCounts, 1);

        for (int i =0; i <cards.size(); i++) {
            Card currentCard = new Card(cards.get(i));
            int matches = currentCard.getNumberOfMatches();

            for (int j = 1; j <= matches; j++) {
                int nextCardIndex = i + j;
                if (nextCardIndex < cards.size()) {
                    cardCounts[nextCardIndex] += cardCounts[i];
                }
            }
        }

        int totalCards = Arrays.stream(cardCounts).sum();
        return totalCards;

    }

}
