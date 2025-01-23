package model;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
/**
 * Represents a player in the game.
 */
public class Player {

    private ArrayList<Piece> pieces;
    private int pieceNum;
    private int number;
    private Color color;
    private String name;

    /**
     * Constructs a new Player object with the specified pieceNum and number.
     *
     * @param pieceNum  the pieceNum of the player's pieces
     * @param number the player's number
     * @param selectedbase the selected base for the player's pieces
     */
    public Player(int pieceNum, int number, String selectedbase) {
        this.pieceNum = pieceNum;
        this.number = number;
        pieces = new ArrayList<Piece>();
        for (int i = 0; i < 3; i++) {
            pieces.add(new Piece(pieceNum, new ImageIcon(getClass().getResource("/assets/bases/"+selectedbase+"/smallPiece.png"))));
        }
        for (int i = 0; i < 3; i++) {
            pieces.add(new Piece(pieceNum * 10, new ImageIcon(getClass().getResource("/assets/bases/"+selectedbase+"/mediumPiece.png"))));
        }
        for (int i = 0; i < 3; i++) {
            pieces.add(new Piece(pieceNum * 100, new ImageIcon(getClass().getResource("/assets/bases/"+selectedbase+"/bigPiece.png"))));
        }
    }

    /**
     * Returns the list of pieces owned by the player.
     *
     * @return the list of pieces
     */
    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    /**
     * Returns the pieceNum of the player's pieces.
     *
     * @return the pieceNum
     */
    public int getpieceNum() {
        return pieceNum;
    }

    /**
     * Returns the player's color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the player's color.
     *
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
        for (Piece piece : pieces) {
            piece.setColor(color);
        }
    } 

    /**
     * Returns the player's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the player's name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the player's number.
     *
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Allows the player to play a piece on the game board.
     *
     * @param row the row to play the piece
     * @param column the column to play the piece
     * @param piece the piece to be played
     * @return the coordinates of the piece to be played
     */
    public ArrayList<Integer> playPiece(Integer row, Integer column, Integer piece) {
        ArrayList<Integer> coordinates = new ArrayList<Integer>();
        coordinates.add(number);
        coordinates.add(piece);
        coordinates.add(row);
        coordinates.add(column);
        return coordinates;
    }

    /**
     * Returns a string representation of the player's pieces.
     *
     * @return a string representation
     */
    public String toString() {
        String str = "";
        for (Piece piece : pieces) {
            str += piece.getValue() + " ";
        }
        return str;
    }
}