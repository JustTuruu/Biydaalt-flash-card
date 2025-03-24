package edu.flashcard;

import java.util.List;

/**
 * Interface for card organization strategies.
 */
public interface CardOrganizer {
  /**
   * Organizes cards according to strategy.
   * @param cards Cards to organize
   * @return Organized list of cards
   */
  List<FlashCard> organize(List<FlashCard> cards);
}