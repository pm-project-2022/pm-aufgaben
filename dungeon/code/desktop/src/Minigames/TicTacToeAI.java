package Minigames;

public class TicTacToeAI {

    /* the board */
    private int board[][];

    public static final int EMPTY = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;

    /**
     * initializes Board
     */
    public TicTacToeAI() {
        board = new int[3][3];
    }

    /**
     * gets Boardvalue at index
     * @param i index i
     * @param j index j
     * @return boardvalue
     */
    public int getBoardValue(int i, int j) {
        if (i < 0 || i >= 3) return EMPTY;
        if (j < 0 || j >= 3) return EMPTY;
        return board[i][j];
    }

    /**
     * sets boardvalue
     * @param i index i
     * @param j index j
     * @param token current toke
     */
    public void setBoardValue(int i, int j, int token) {
        if (i < 0 || i >= 3) return;
        if (j < 0 || j >= 3) return;
        board[i][j] = token;
    }

    /**
     * calculates next winning move with current token
     * @param token current token
     * @return next winning move
     */
    public int[] nextWinningMove(int token) {

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (getBoardValue(i, j) == EMPTY) {
                    board[i][j] = token;
                    boolean win = isWin(token);
                    board[i][j] = EMPTY;
                    if (win) return new int[]{i, j};
                }

        return null;
    }

    /**
     * inverses playerturn
     * @param token current token
     * @return current turn
     */
    public int inverse(int token) {
        return token == ONE ? TWO : ONE;
    }

    /**
     * calculates best move with current token
     * @param token current token
     * @return best move
     */
    public int[] nextMove(int token) {

        /* lucky position in the center of board*/
        if (getBoardValue(1, 1) == EMPTY) return new int[]{1, 1};

        /* if we can move on the next turn */
        int winMove[] = nextWinningMove(token);
        if (winMove != null) return winMove;

        /* choose the move that prevent enemy to win */
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (getBoardValue(i, j) == EMPTY) {
                    board[i][j] = token;
                    boolean ok = nextWinningMove(inverse(token)) == null;
                    board[i][j] = EMPTY;
                    if (ok) return new int[]{i, j};
                }

        /* choose available move */
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (getBoardValue(i, j) == EMPTY)
                    return new int[]{i, j};

        /* no move is available */
        return null;
    }

    /**
     * checks if current token won or not
     * @param token current token
     * @return true if won false if not
     */
    public boolean isWin(int token) {
        final int DI[] = {-1, 0, 1, 1};
        final int DJ[] = {1, 1, 1, 0};
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {

                /* we skip if the token in position(i,j) not equal current token */
                if (getBoardValue(i, j) != token) continue;

                for (int k = 0; k < 4; k++) {
                    int ctr = 0;
                    while (getBoardValue(i + DI[k] * ctr, j + DJ[k] * ctr) == token) ctr++;

                    if (ctr == 3) return true;
                }
            }
        return false;
    }
}
