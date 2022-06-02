package Minigames;

import java.util.Scanner;
import java.util.logging.Logger;

public class Hangman {

    private String[] words = {"terminator", "banana", "computer", "cow", "rain", "water"};
    private String word;
    private String notGuessedChar;
    private int count;
    private boolean won;
    private final Logger LOGGER = Logger.getLogger(getClass().getName());

    public void initHangman() {

        Scanner sc = new Scanner(System.in);
        word = words[(int) (Math.random() * words.length)];
        notGuessedChar = new String(new char[word.length()]).replace("\0", "*");
        count = 0;
        while (count < 7 && notGuessedChar.contains("*")) {
            LOGGER.info("Guess");
            LOGGER.info(notGuessedChar);
            String guess = sc.next();
            hang(guess);
        }

    }

    public void hang(String guess) {
        String guessedChar = "";
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess.charAt(0)) {
                guessedChar += guess.charAt(0);
            } else if (notGuessedChar.charAt(i) != '*') {
                guessedChar += word.charAt(i);
            } else {
                guessedChar += "*";
            }
        }

        if (notGuessedChar.equals(guessedChar)) {
            count++;
            print();
        } else {
            notGuessedChar = guessedChar;
        }
        if (notGuessedChar.equals(word)) {
            LOGGER.info("Correct: " + word);
            this.won = true;
        }
    }

    public void print() {
        if (count == 1) {
            LOGGER.info("Wrong!");
            LOGGER.info("""




                ___|___
                 """);
        }
        if (count == 2) {
            LOGGER.info("Wrong!");
            LOGGER.info("""

                   |
                   |
                   |
                   |
                ___|___
                 """);
        }
        if (count == 3) {
            LOGGER.info("Wrong!");
            LOGGER.info("""

                 \t_________
                   |        |
                   |
                   |
                   |
                ___|___
                 """);
        }
        if (count == 4) {
            LOGGER.info("Wrong!");
            LOGGER.info("""

                 \t_________
                   |        |
                   |        O
                   |
                   |
                ___|___
                 """);
        }
        if (count == 5) {
            LOGGER.info("Wrong!");
            LOGGER.info("""

                 \t_________
                   |        |
                   |        O
                   |        |
                   |
                ___|___
                 """);
        }

        if (count == 6) {
            LOGGER.info("Wrong!");
            LOGGER.info("""

                 \t_________
                   |        |
                   |        O
                   |        |
                   |       / \\
                ___|___
                 """);
        }
        if (count == 7) {
            LOGGER.info("""

                 \t_________
                   |        |
                   |        O
                   |       \\|/
                   |       / \\
                ___|___
                 """);
            LOGGER.info("Game Over!: " +  word);
        }
    }

    public boolean getWon() {
        return this.won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }
}
