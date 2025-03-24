package edu.flashcard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages a learning session with flash cards.
 */
public class LearnSession {
  private final List<FlashCard> cards;
  private final CardOrganizer organizer;
  private final int repetitions;
  private final AchievementSystem achievements = new AchievementSystem();

  /**
   * Creates a new learning session.
   * @param cards Cards to use
   * @param organizer Organization strategy
   * @param repetitions Required correct answers
   */
  public LearnSession(List<FlashCard> cards, CardOrganizer organizer, int repetitions) {
    this.cards = new ArrayList<>(cards);
    this.organizer = organizer;
    this.repetitions = repetitions;
  }

  /** Starts the interactive session. */
  public void start() {
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.println("Starting learning session...\n");
      
      while (true) {
        List<FlashCard> currentRound = organizer.organize(getPendingCards());
        if (currentRound.isEmpty()) break;

        for (FlashCard card : currentRound) {
          presentCard(card, scanner);
          achievements.checkAchievements(card);
        }
      }
      
      System.out.println("\nSession complete!");
    }
  }

  private List<FlashCard> getPendingCards() {
    List<FlashCard> pending = new ArrayList<>();
    for (FlashCard card : cards) {
      if (card.getCorrectAnswers() < repetitions) {
        pending.add(card);
      }
    }
    return pending;
  }

  private void presentCard(FlashCard card, Scanner scanner) {
    System.out.println("Q: " + card.getQuestion());
    System.out.print("A: ");
    String answer = scanner.nextLine().trim();
    boolean correct = answer.equalsIgnoreCase(card.getAnswer());
    card.recordAnswer(correct);
    System.out.println(correct ? "Correct!" : "Wrong! Correct answer: " + card.getAnswer());
  }
}