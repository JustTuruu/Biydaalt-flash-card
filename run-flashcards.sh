#!/bin/bash

# Define the default parameters
CARDS_FILE="src/main/java/edu/flashcard/cards.txt"
ORDER="recent-mistakes-first"
REPETITIONS=3

# Run the application using Maven
mvn exec:java -Dexec.mainClass="edu.flashcard.FlashCardApp" -Dexec.args="$CARDS_FILE --order $ORDER --repetitions $REPETITIONS"