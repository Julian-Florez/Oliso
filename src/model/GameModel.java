package model;
import java.util.ArrayList;

/**
 * The Game class represents a game instance.
 * It manages the game board, players, turns, and win conditions.
 */
public class GameModel {

    private Board board;
    private Turn turn;
    private int mode;
    private ArrayList<Player> player = new ArrayList<Player>();
    private boolean win = false;
    private boolean draw = false;
    private String winner;
    private int piecesPlayed = 0;


    /**
     * Constructs a new GameModel object with the specified mode and selected base.
     *
     * @param mode           the mode of the game
     * @param selectedbase   the selected base for the players pieces
     */
    public GameModel(int mode, String selectedbase) {
        this.mode = mode;
        board = new Board();
        turn = new Turn(4);

        switch (mode) {
            case 0:
                player.add(new Player(ColorPiece.RED.getValue(), 0, selectedbase));
                player.add(new Player(ColorPiece.BLUE.getValue(), 1, selectedbase));
                player.add(new Player(ColorPiece.GREEN.getValue(), 2, selectedbase));
                player.add(new Player(ColorPiece.YELLOW.getValue(), 3, selectedbase));
                break;
            case 1:
                player.add(new Player(ColorPiece.RED.getValue(), 0, selectedbase));
                player.add(new Player(ColorPiece.BLUE.getValue(), 1, selectedbase));
                player.add(new Player(ColorPiece.RED.getValue(), 2, selectedbase));
                player.add(new Player(ColorPiece.BLUE.getValue(), 3, selectedbase));
                break;
            case 2:
                player.add(new Player(ColorPiece.RED.getValue(), 0, selectedbase));
                player.add(new Player(ColorPiece.BLUE.getValue(), 1, selectedbase));
                player.add(new Player(ColorPiece.RED.getValue(), 2, selectedbase));
                player.add(new Player(ColorPiece.BLUE.getValue(), 3, selectedbase));
                break;
            default:
                break;
        }
    }

    /**
     * Plays a game piece on the board based on the provided play coordinates.
     * Checks for a win condition and updates the turn.
     *
     * @param play The coordinates of the play: [playerIndex, pieceIndex, row, column].
     * @throws InvalidMoveException if the move is invalid.
     */
    public void playPiece(ArrayList<Integer> play) throws InvalidMoveException{
        Piece piece_obj = player.get(play.get(0)).getPieces().get(play.get(1));
        board.playPiece(piece_obj, play.get(2), play.get(3));
        if (board.checkWin()) {
            winner = player.get(play.get(0)).getName();
            win = true;
        }

        piecesPlayed++;

        if (piecesPlayed == 27){
            draw = true;
        }
        turn.play();
    }

    /**
     * Passes the turn to the next player.
     */
    public void pass() {
        turn.play();
    }

    /**
     * Returns the game board.
     *
     * @return The game board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Returns the winner of the game.
     *
     * @return the winner of the game
     */
    public String getWinner() {
        return winner;
    }

    /**
     * Returns the current mode of the game.
     *
     * @return the current mode of the game
     */
    public int getMode() {
        return mode;
    }

    /**
     * Returns the draw status of the game.
     *
     * @return true if the game ended in a draw, false otherwise.
     */
    public boolean getDraw() {
        return draw;
    }

    /**
     * Checks if a move is legal at the specified row and column.
     *
     * @param row The row index.
     * @param column The column index.
     * @return True if the move is legal, false otherwise.
     */
    public boolean checkLegalMove(int row, int column){
        return board.checkLegalMove(row, column);
    }

    /**
     * Returns the win status of the game.
     *
     * @return True if a player has won the game, false otherwise.
     */
    public boolean getWin() {
        return win;
    }

    /**
     * Sets the win status of the game.
     *
     * @param win the win status to set
     */
    public void setWin(boolean win) {
        this.win = win;
    }

    /**
     * Returns the player at the specified index.
     *
     * @param i The index of the player.
     * @return The player at the specified index.
     */
    public Player getUser(int i) {
        return player.get(i);
    }

    /**
     * Returns the current turn object.
     *
     * @return The current turn object.
     */
    public Turn getTurn() {
        return turn;
    }

    /**
     * Returns a string representation of the game board.
     *
     * @return A string representation of the game board.
     */
    public String toString() {
        return board.toString();
    }
}