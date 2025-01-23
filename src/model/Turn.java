package model;
/**
 * The Turn class represents the current turn and player in a game.
 */
public class Turn {
    
    private int turn;
    private int max_turns;
    private int actual_player;
    
    /**
     * Constructs a Turn object with the specified number of players.
     * 
     * @param players the number of players in the game
     */
    public Turn(int players){
        this.turn = 0;
        this.max_turns = 3*9;
        this.actual_player = 0;
    }
    
    /**
     * Advances the turn to the next one.
     */
    public void nextTurn(){
        turn++;
    }
    
    /**
     * Returns the current turn number.
     * 
     * @return the current turn number
     */
    public int getTurn(){
        return turn;
    }

    /**
     * Returns the maximum number of turns in the game.
     * 
     * @return the maximum number of turns
     */
    public int getMaxTurns(){
        return max_turns;
    }

    /**
     * Advances the player to the next one.
     */
    public void nextPlayer(){
        if (actual_player == 3){
            actual_player = 0;
        } else {
            actual_player++;
        }
    }

    /**
     * Returns the index of the current player.
     * 
     * @return the index of the current player
     */
    public int getActualPlayer(){
        return actual_player;
    }

    /**
     * Performs the actions for the current turn, including advancing the turn and player.
     */
    public void play(){
        nextTurn();
        nextPlayer();
    }
    
    /**
     * Returns a string representation of the Turn object.
     * 
     * @return a string representation of the Turn object
     */
    public String toString(){
        return "Turn: " + turn + " Player: " + actual_player;
    }
}