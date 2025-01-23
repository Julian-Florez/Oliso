package model;
/**
 * The Color enum represents different colors in a game.
 */
public enum ColorPiece {
    RED(1), BLUE(3), GREEN(5), YELLOW(7);

    private final int value;

    /**
     * Constructs a Color object with the specified value.
     *
     * @param value the value associated with the color
     */
    ColorPiece(int value) {
        this.value = value;
    }

    /**
     * Returns the value associated with the color.
     *
     * @return the value of the color
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the color associated with the specified value.
     *
     * @param value the value of the color
     * @return the color associated with the value
     */
    public static ColorPiece getColor(int value) {
        for (ColorPiece color : ColorPiece.values()) {
            if (color.getValue() == value) {
                return color;
            }
        }
        return null;
    }

    /**
     * Returns the color as a string.
     *
     * @return the color as a string
     */

    @Override
    public String toString() {
        switch (this) {
            case RED:
                return "Red";
            case BLUE:
                return "Blue";
            case GREEN:
                return "Green";
            case YELLOW:
                return "Yellow";
            default:
                return null;
        }
    }

    public String toHex() {
        switch (this) {
            case RED:
                return "#FF0000";
            case BLUE:
                return "#0000FF";
            case GREEN:
                return "#00FF00";
            case YELLOW:
                return "#FFFF00";
            default:
                return null;
        }
    }   
}
