package model.game;

public class InvalidMoveException extends Exception{
    public int winner;
    public InvalidMoveException(int winner){
        this.winner = winner;
    }
}
