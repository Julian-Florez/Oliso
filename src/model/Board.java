package model;
import java.util.ArrayList;
/**
 * The Board class represents the game board for a Tic-Tac-Toe game.
 * It contains a 2D ArrayList of Square objects to represent the individual squares on the board.
 * The class provides methods to play a piece on the board, check for a win condition, and retrieve the current state of the board.
 */
/**
 * Represents a game board consisting of squares.
 */
public class Board{

    private ArrayList<ArrayList<Square>> squares;
    private int win = 0;

    /**
     * Constructs a new Board object.
     * Initializes the squares ArrayList and populates it with Square objects.
     */
    public Board(){
        squares = new ArrayList<ArrayList<Square>>();
        for (int i = 0; i < 3; i++){
            squares.add(new ArrayList<Square>());
            for (int j = 0; j < 3; j++){
                squares.get(i).add(new Square());
            }
        }
    }

    /**
     * Plays a piece on the board at the specified row and column.
     * @param piece The Piece object to be played.
     * @param row The row index of the square to play the piece.
     * @param column The column index of the square to play the piece.
     * @throws InvalidMoveException if the move is invalid.
     */
    public void playPiece(Piece piece, int row, int column) throws InvalidMoveException{
        squares.get(row).get(column).playPiece(piece);
    }

    /**
     * Retrieves the 2D ArrayList of Square objects representing the current state of the board.
     * @return The squares ArrayList.
     */
    public ArrayList<ArrayList<Square>> getSquares(){
        return squares;
    }

    /**
     * Checks if a move is legal on the board.
     * @param row The row index of the square to check.
     * @param column The column index of the square to check.
     * @return true if the move is legal, false otherwise.
     */
    public boolean checkLegalMove(int row, int column){
        if (row < 0 || row > 2 || column < 0 || column > 2){
            return false;
        }
        return true;
    }

    /**
     * Checks if there is a win condition on the board.
     * @return true if there is a win condition, false otherwise.
     */
    public boolean checkWin(){
        // Check rows
        for (ArrayList<Square> row : squares){
            for (Square square : row){
                if (square.getValue() == 111 || square.getValue() == 333 || square.getValue() == 555 || square.getValue() == 777){
                    this.win = 1;
                    return true;
                }
            }
            if (CheckFirstCondition(row)){
                this.win = 2;
                return true;
            }
            if (CheckSecondCondition(row)){
                this.win = 3;
                return true;
            }
        }
        // Check columns
        for (ArrayList<Square> row : transpose()){
            
            if (CheckFirstCondition(row)){
                this.win = 4;
                return true;
            }
            if (CheckSecondCondition(row)){
                this.win = 5;
                return true;
            }
        }
        // Check diagonals
        ArrayList<Square> diagonal1 = new ArrayList<>();
        ArrayList<Square> diagonal2 = new ArrayList<>();
        for (int i = 0; i < squares.size(); i++) {
            diagonal1.add(squares.get(i).get(i));
            diagonal2.add(squares.get(i).get(squares.size() - 1 - i));
        }
        if (CheckFirstCondition(diagonal1) || CheckFirstCondition(diagonal2)) {
            this.win = 6;
            return true;
        }
        if (CheckSecondCondition(diagonal1) || CheckSecondCondition(diagonal2)) {
            this.win = 7;
            return true;
        }
        return false;
    }

    /**
     * Checks the first win condition for a given row.
     * @param row The ArrayList of Square objects representing a row on the board.
     * @return true if the first win condition is met, false otherwise.
     */
    public boolean CheckFirstCondition (ArrayList<Square> row){
        if (row.get(0).getValue() / 100 == row.get(1).getValue() / 100 && row.get(1).getValue() / 100 == row.get(2).getValue() / 100 && row.get(0).getValue() / 100 != 0) {
            return true;
        }
        if (row.get(0).getValue() % 100 / 10 == row.get(1).getValue() % 100 / 10 && row.get(1).getValue() % 100 / 10 == row.get(2).getValue() % 100 / 10 && row.get(0).getValue() % 100 / 10 != 0) {
            return true;
        }
        if (row.get(0).getValue() % 100 % 10 == row.get(1).getValue() % 100 % 10 && row.get(1).getValue() % 100 % 10 == row.get(2).getValue() % 100 % 10 && row.get(0).getValue() % 100 % 10 != 0) {
            return true;
        }
        return false;
    }

    /**
     * Checks the second win condition for a given row.
     * @param row The ArrayList of Square objects representing a row on the board.
     * @return true if the second win condition is met, false otherwise.
     */
    public boolean CheckSecondCondition (ArrayList<Square> row){
        if (row.get(0).getValue() / 100 == row.get(1).getValue() % 100 /10 && row.get(1).getValue() % 100 / 10 == row.get(2).getValue() % 100 % 10 && row.get(0).getValue()/100 != 0 && row.get(1).getValue()%100/10 != 0 && row.get(2).getValue()%100%10 != 0) {
            return true;
        }
        if ((row.get(0).getValue() % 100 % 10 == row.get(1).getValue() % 100 / 10 ) && (row.get(1).getValue() % 100 / 10 == row.get(2).getValue() / 100)&& (row.get(0).getValue()%100%10 != 0) && (row.get(1).getValue()%100/10 != 0) && (row.get(2).getValue()/100 != 0)) {
            return true;
        }
        return false;
    }

    /**
     * Transposes the squares ArrayList to swap rows and columns.
     * @return The transposed 2D ArrayList of Square objects.
     */
    public ArrayList<ArrayList<Square>> transpose() {
        ArrayList<ArrayList<Square>> transposed = new ArrayList<ArrayList<Square>>();
        int numRows = squares.size();
        int numCols = squares.get(0).size();

        for (int j = 0; j < numCols; j++) {
            ArrayList<Square> newRow = new ArrayList<Square>();
            for (int i = 0; i < numRows; i++) {
                newRow.add(squares.get(i).get(j));
            }
            transposed.add(newRow);
        }

        return transposed;        
    }

    /**
     * Retrieves the win condition of the board.
     * @return The win condition as an integer.
     */
    public int getWin(){
        return win;
    }

    /**
     * Returns a string representation of the board.
     * @return The board as a string.
     */
    public String toString(){
        String board = "";
        for (ArrayList<Square> row : squares){
            for (Square square : row){
                board += square.toString();
            }
            board += "\n";
        }
        return board;
    }
}