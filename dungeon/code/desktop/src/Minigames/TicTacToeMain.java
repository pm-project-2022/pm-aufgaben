package Minigames;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class TicTacToeMain extends JFrame implements ActionListener {

    private final JButton[][] buttons = new JButton[3][3];
    private final JButton playButton = new JButton("Play");
    private final JLabel statusLabel = new JLabel("");
    private TicTacToeAI game = null;
    private int player = 0;
    private int ai = 0;
    private boolean isPlay = false;
    private String[] chars = new String[]{"", "X", "O"};
    private boolean won = false;
    private boolean lost = false;

    /**
     * sets current status
     * @param s status
     */
    private void setStatus(String s) {
        statusLabel.setText(s);
    }

    /**
     * enables all buttons
     * @param enabled true or false
     */
    private void setButtonsEnabled(boolean enabled) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(enabled);
                if (enabled) buttons[i][j].setText(" ");
            }
    }

    /**
     * initializes ttt Gui
     */
    public TicTacToeMain() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel centerPanel = new JPanel(new GridLayout(3, 3));
        Font font = new Font("Arial", Font.BOLD, 32);
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton(" ");
                buttons[i][j].setFont(font);
                buttons[i][j].addActionListener(this);
                buttons[i][j].setFocusable(false);
                centerPanel.add(buttons[i][j]);
            }

        playButton.addActionListener(this);

        JPanel northPanel = new JPanel();
        northPanel.add(statusLabel);

        JPanel southPanel = new JPanel();
        southPanel.add(playButton);

        setStatus("Click 'Play' To Start");
        setButtonsEnabled(false);

        add(northPanel, "North");
        add(centerPanel, "Center");
        add(southPanel, "South");

        setSize(300, 300);

        // I'm lazy to implement the correct way
        setLocationRelativeTo(null);
    }

    /**
     * computer makes turn
     */
    private void computerTurn() {
        if (!isPlay) return;

        int[] pos = game.nextMove(ai);
        if (pos != null) {
            int i = pos[0];
            int j = pos[1];
            buttons[i][j].setText(chars[ai]);
            game.setBoardValue(i, j, ai);
        }

        checkState();
    }

    /**
     * game over with status
     * @param s current status
     */
    private void gameOver(String s) {
        setStatus(s);
        setButtonsEnabled(false);
        isPlay = false;
    }

    /**
     * checks current gamestate
     */
    private void checkState() {
        if (game.isWin(player)) {
            gameOver("Gewonnen!");
            setWon(true);
        }
        if (game.isWin(ai)) {
            gameOver("Verloren!");
            setLost(true);
        }
        if (game.nextMove(player) == null && game.nextMove(ai) == null) {
            gameOver("Unentschieden, nochmal versuchen!");
        }
    }

    /**
     * clicks a button
     * @param i index i
     * @param j index j
     */
    private void click(int i, int j) {
        if (game.getBoardValue(i, j) == TicTacToeAI.EMPTY) {
            buttons[i][j].setText(chars[player]);
            game.setBoardValue(i, j, player);
            checkState();
            computerTurn();
        }
    }

    /**
     * Button actionPerformer
     * @param event the event to be processed
     */
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == playButton) {
            play();
        } else {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (event.getSource() == buttons[i][j])
                        click(i, j);
        }
    }

    /**
     * initializes turns
     */
    private void play() {
        game = new TicTacToeAI();
        player = TicTacToeAI.ONE;
        ai = TicTacToeAI.TWO;
        setStatus("Your Turn");
        setButtonsEnabled(true);
        isPlay = true;
    }

    /**
     * getter for won
     * @return true or false
     */
    public boolean getWon(){
        return this.won;
    }

    /**
     * set won status
     * @param won true or false
     */
    public void setWon(boolean won){
        this.won = won;
    }

    /**
     * get lost status
     * @return true or false
     */
    public boolean getLost(){
        return lost;
    }

    /**
     * set lost status
     * @param lost true or false
     */
    public void setLost(boolean lost){
        this.lost = lost;
    }
}
