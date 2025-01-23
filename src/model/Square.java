package model;
/**
 * The Square class represents a square on the game board.
 * It keeps track of the value of the square and the availability of different sized pieces.
 */
/**
 * The Square class represents a square on the game board.
 * Each square can hold pieces of different sizes and has a value associated with it.
 */
public class Square{

    private int value;
    private boolean small_avaliable;
    private boolean medium_avaliable;
    private boolean large_avaliable;
    private Piece smallPiece;
    private Piece mediumPiece;
    private Piece largePiece;

    /**
     * Constructs a Square object with initial values.
     * The initial value is set to 0 and all piece sizes are available.
     */
    public Square(){
        this.value = 0;
        this.small_avaliable = true;
        this.medium_avaliable = true;
        this.large_avaliable = true;
    }

    /**
     * Plays a piece on the square.
     * If the piece is not available or the move is invalid, an IllegalArgumentException is thrown.
     * The value of the square is updated based on the value of the piece.
     * The availability of the corresponding piece size is also updated.
     * 
     * @param piece the piece to be played on the square
     * @throws InvalidMoveException if the piece is not available or the move is invalid
     */
    public void playPiece(Piece piece) throws InvalidMoveException{
        if (!piece.isAvaliable()){
            throw new InvalidMoveException("InvalidMove");
        }
        if (piece.getValue()<10 && small_avaliable){
            this.smallPiece = piece;
            this.value += piece.getValue();
            piece.setAvaliable(false);
            small_avaliable = false;
        } else if (piece.getValue()<100 && piece.getValue()>=10 && medium_avaliable){
            this.mediumPiece = piece;
            this.value += piece.getValue();
            piece.setAvaliable(false);
            medium_avaliable = false;
        } else if (piece.getValue()<1000 && piece.getValue()>=100 && large_avaliable){
            this.largePiece = piece;
            this.value += piece.getValue();
            piece.setAvaliable(false);
            large_avaliable = false;
        } else {
            throw new InvalidMoveException("InvalidMove");
        }
    }

    /**
     * Returns the value of the square.
     * 
     * @return the value of the square
     */
    public int getValue(){
        return value;
    }

    /**
     * Checks if the small piece is available.
     * 
     * @return true if the small piece is available, false otherwise
     */
    public boolean isSmall_avaliable() {
        return small_avaliable;
    }

    /**
     * Checks if the medium piece is available.
     * 
     * @return true if the medium piece is available, false otherwise
     */
    public boolean isMedium_avaliable() {
        return medium_avaliable;
    }

    /**
     * Checks if the large piece is available.
     * 
     * @return true if the large piece is available, false otherwise
     */
    public boolean isLarge_avaliable() {
        return large_avaliable;
    }

    /**
     * Gets the small piece on the square.
     * 
     * @return the small piece on the square
     */
    public Piece getSmallPiece() {
        return smallPiece;
    }

    /**
     * Gets the medium piece on the square.
     * 
     * @return the medium piece on the square
     */
    public Piece getMediumPiece() {
        return mediumPiece;
    }

    /**
     * Gets the large piece on the square.
     * 
     * @return the large piece on the square
     */
    public Piece getLargePiece() {
        return largePiece;
    }

    /**
     * Returns a string representation of the square.
     * The string includes the value of the square.
     * 
     * @return a string representation of the square
     */
    public String toString(){
        return " "+ value;
    }

}