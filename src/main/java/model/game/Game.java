package model.game;

import model.board.GameBoard;
import model.cell.Cell;

public interface Game {
    GameBoard getBoard();
    void makeMove(int x, int y) throws InvalidMoveException;
    void makeMove(Cell c) throws InvalidMoveException;
    int whoWon();
    void newGame();
    int getTurn();
}
