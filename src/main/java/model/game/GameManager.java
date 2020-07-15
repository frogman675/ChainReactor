package model.game;

import model.board.GameBoard;
import model.cell.Cell;

public class GameManager implements Game {
    private static final Game game = new GameInstance();

    @Override
    public GameBoard getBoard() {
        return game.getBoard();
    }

    @Override
    public void makeMove(int x, int y) throws InvalidMoveException {
        game.makeMove(x, y);
    }

    @Override
    public void makeMove(Cell c) throws InvalidMoveException {
        game.makeMove(c);
    }

    @Override
    public int whoWon() {
        return game.whoWon();
    }

    @Override
    public void newGame() {
        game.newGame();
    }

    @Override
    public int getTurn() {
        return game.getTurn();
    }
}
