package edu.flashcard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class for Flash Card System.
 */
public class FlashCardApp {
  private static final String HELP_MESSAGE = 
      "Usage: flashcard <cards-file> [options]\n"
      + "Options:\n"
      + "  --help                Display this help information\n"
      + "  --order <order>       Organization type, default: \"random\"\n"
      + "                        [options: random, worst-first, recent-mistakes-first]\n"
      + "  --repetitions <num>   Number of required correct answers per card\n"
      + "  --invertCards         Swap questions/answers";

  public static void main(String[] args) {
    if (args.length == 0 || args[0].equals("--help")) {
      System.out.println(HELP_MESSAGE);
      return;
    }

    // Argument parsing logic
    String cardsFile = args[0];
    String order = "random";
    int repetitions = 1;
    boolean invertCards = false;

    for (int i = 1; i < args.length; i++) {
      switch (args[i]) {
        case "--order":
          if (i + 1 < args.length) order = args[++i];
          break;
        case "--repetitions":
          if (i + 1 < args.length) repetitions = Integer.parseInt(args[++i]);
          break;
        case "--invertCards":
          invertCards = true;
          break;
        default:
          System.out.println("Unknown option: " + args[i]);
          System.exit(1);
      }
    }

    List<FlashCard> cards = loadCardsFromFile(cardsFile, invertCards);
    CardOrganizer organizer = createOrganizer(order);
    new LearnSession(cards, organizer, repetitions).start();
  }

  private static CardOrganizer createOrganizer(String order) {
    switch (order) {
      case "worst-first":
        return new WorstFirstSorter();
      case "recent-mistakes-first":
        return new RecentMistakesFirstSorter();
      default:
        return new RandomSorter();
    }
  }

  private static List<FlashCard> loadCardsFromFile(String filename, boolean invert) {
    List<FlashCard> cards = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      String question = null;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.startsWith("Q:")) {
          question = line.substring(2).trim();
        } else if (line.startsWith("A:") && question != null) {
          String answer = line.substring(2).trim();
          cards.add(invert 
              ? new FlashCard(answer, question)
              : new FlashCard(question, answer));
          question = null;
        }
      }
    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
      System.exit(1);
    }
    return cards;
  }
}