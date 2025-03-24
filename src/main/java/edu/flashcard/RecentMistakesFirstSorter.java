package edu.flashcard;

import java.util.ArrayList;
import java.util.List;

/**
 * Organizes cards with recent mistakes first.
 */
public class RecentMistakesFirstSorter implements CardOrganizer {
  @Override
  public List<FlashCard> organize(List<FlashCard> cards) {
    List<FlashCard> result = new ArrayList<>();
    List<FlashCard> correct = new ArrayList<>();
    
    for (FlashCard card : cards) {
      if (card.isRecentlyMistaken()) {
        result.add(card);
      } else {
        correct.add(card);
      }
    }
    
    result.addAll(correct);
    return result;
  }
}