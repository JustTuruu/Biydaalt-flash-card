package edu.flashcard;


public class FlashCard {
  private final String question;
  private final String answer;
  private int correctAnswers;
  private int totalAttempts;
  private boolean recentlyMistaken;

  /**
   * Creates a new flash card.
   * @param question The question text
   * @param answer The answer text
   */
  public FlashCard(String question, String answer) {
    this.question = question;
    this.answer = answer;
  }

  /** Records an answer attempt. */
  public void recordAnswer(boolean correct) {
    totalAttempts++;
    if (correct) {
      correctAnswers++;
      recentlyMistaken = false;
    } else {
      recentlyMistaken = true;
    }
  }

  public String getQuestion() { return question; }
  public String getAnswer() { return answer; }
  public int getCorrectAnswers() { return correctAnswers; }
  public int getTotalAttempts() { return totalAttempts; }
  public boolean isRecentlyMistaken() { return recentlyMistaken; }
  
  /** @return Success rate between 0.0 and 1.0 */
  public double getSuccessRate() {
    return totalAttempts == 0 ? 0.0 : (double) correctAnswers / totalAttempts;
  }
}