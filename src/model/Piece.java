package model;

import java.awt.Color;

import javax.swing.ImageIcon;

import view.GraphicalManager;

/**
 * Represents a game piece.
 */
public class Piece {

    private int value;
    private boolean avaliable;
    ImageIcon icon;
    Color color;

    /**
     * Constructs a new Piece object with the specified value.
     *
     * @param value the value of the piece
     * @param icon the icon representing the piece
     */
    public Piece(int value, ImageIcon icon) {
        this.value = value;
        this.avaliable = true;
        this.icon = icon;
    }

    /**
     * Returns the value of the piece.
     *
     * @return the value of the piece
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the icon representing the piece.
     *
     * @return the icon representing the piece
     */
    public ImageIcon getIcon() {
        return icon;
    }

    /**
     * Sets the color of the piece.
     *
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
        this.icon = GraphicalManager.paintImage(icon, color, 1000, 1000);
    }

    /**
     * Returns the color of the piece.
     *
     * @return the color of the piece
     */
    public Color getColor() {
        return color;
    }

    /**
     * Checks if the piece is available.
     *
     * @return true if the piece is available, false otherwise
     */
    public boolean isAvaliable() {
        return avaliable;
    }

    /**
     * Sets the availability of the piece.
     *
     * @param avaliable true if the piece is available, false otherwise
     */
    public void setAvaliable(boolean avaliable) {
        this.avaliable = avaliable;
    }

    /**
     * Returns a string representation of the piece.
     *
     * @return a string representation of the piece
     */
    @Override
    public String toString() {
        return value + "";
    }
}