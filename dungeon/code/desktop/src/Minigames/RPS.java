package Minigames;


import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

public class RPS {

    private boolean won;
    String playerMove;
    String computerMove;
    Logger log = Logger.getLogger(getClass().getName());

    /**
     * initializes game
     */
    public void initRPS() {
        playRockPaperScissors();
    }

    /**
     * calls determineWinner function with moves
     */
    private void playRockPaperScissors() {
        determineWinner(getPlayerMove(),getComputerMove());
    }

    /**
     * player makes a move
     * @return playerMove
     */
    private String getPlayerMove(){
        Scanner sc = new Scanner(System.in);
        log.info("Schere Stein Papier");

        switch (sc.next()) {
            case "Schere" -> playerMove = "Schere";
            case "Stein" -> playerMove = "Stein";
            case "Papier" -> playerMove = "Papier";
            default -> getPlayerMove();
        }
        return playerMove;
    }

    /**
     * computer makes a move
     * @return computerMove
     */
    private String getComputerMove(){
        Random random = new Random();
        int rand = random.nextInt(3)+1;
        switch (rand) {
            case 1 -> computerMove = "Schere";
            case 2 -> computerMove = "Stein";
            case 3 -> computerMove = "Papier";
        }
        return computerMove;
    }

    /**
     * determines the winner
     * @param player playerMove
     * @param comp computerMove
     */
    private void determineWinner(String player, String comp){

        if(player == "Stein" && comp == "Schere" || player == "Schere" && comp == "Papier"
        || player == "Papier" && comp == "Stein"){
            log.info("Spieler hat gewonnen!");
            setWon(true);
        }
        if(player == "Stein" && comp == "Stein" || player == "Schere" && comp == "Schere"
            || player == "Papier" && comp == "Papier"){
            log.info("Unentschieden!");
        }

        if(player == "Stein" && comp == "Papier" || player == "Schere" && comp == "Stein"
            || player == "Papier" && comp == "Schere"){
            log.info("Computer hat gewonnen!");
        }
    }

    /**
     * getter for won
     * @return true if won false if not
     */
    public boolean getWon() {
        return won;
    }

    /**
     * setter for won
     * @param won true or false
     */
    public void setWon(boolean won) {
        this.won = won;
    }
}
