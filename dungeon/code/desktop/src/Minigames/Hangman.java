package Minigames;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Hangman {

    private String[] words;
    private String word;
    private String notGuessedChar;
    private int count;
    private boolean won;
    private final Logger LOGGER = Logger.getLogger(getClass().getName());


    /**
     * fills array with words from .txt
     * @throws IOException
     */
    private void fillArray() throws IOException {
        List<String> list= new ArrayList<>();
        BufferedReader bf = new BufferedReader(new FileReader("dungeon/code/desktop/src/Minigames/words.txt"));
        String line = bf.readLine();

        while (line != null) {
            list.add(line);
            line = bf.readLine();
        }
        bf.close();

        words = list.toArray(new String[0]);
    }

    /**
     * initializes hangman game
     * @throws IOException
     */
    public void initHangman() throws IOException {
        fillArray();
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

    /**
     * checks if guesses are correct
     * @param guess guessed letter
     */
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

    /**
     * prints hangman
     */
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

    /**
     * getter for won
     * @return true if won false if not
     */
    public boolean getWon() {
        return this.won;
    }

    /**
     * setter for won
     * @param won true or false
     */
    public void setWon(boolean won) {
        this.won = won;
    }
}
