package edu.flashcard;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Manages achievement tracking and unlocking.
 */
public class AchievementSystem {
  private final Set<String> unlocked = new HashSet<>();

  /**
   * Checks achievements for a card interaction.
   * @param card Current flash card
   */
  public void checkAchievements(FlashCard card) {
    checkRepeatAchievement(card);
    checkConfidentAchievement(card);
  }

  private void checkRepeatAchievement(FlashCard card) {
    if (card.getTotalAttempts() >= 5) {
      unlock("REPEAT", "Answered a card 5+ times");
    }
  }

  private void checkConfidentAchievement(FlashCard card) {
    if (card.getCorrectAnswers() >= 3) {
      unlock("CONFIDENT", "3+ correct answers on a card");
    }
  }

  private void unlock(String name, String description) {
    if (!unlocked.contains(name)) {
      unlocked.add(name);
      System.out.printf("\n🏆 %s Unlocked: %s\n", name, description);
    }
  }
}