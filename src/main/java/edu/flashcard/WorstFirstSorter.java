package edu.flashcard;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WorstFirstSorter implements CardOrganizer {
    @Override
    public List<FlashCard> organize(List<FlashCard> cards) {
        // Sort cards so that those with the lowest success rate come first
        cards.sort(Comparator.comparingDouble(FlashCard::getSuccessRate));
        return cards;
    }
}
