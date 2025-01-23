package model;
/**
 * The InvalidMoveException class represents an exception that is thrown when an invalid move is made in the game.
 * It is a subclass of the Exception class.
 */
public class InvalidMoveException extends Exception {
    /**
     * Constructs a new InvalidMoveException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidMoveException(String message) {
        super(message);
        controller.SoundManager.playWrongSound();
    }
}