package model.game;

import model.board.GameBoard;
import model.board.SquareBoard;
import model.cell.Cell;

public class GameInstance implements Game {
    private GameBoard board = new SquareBoard();
    private int teamTurn = 1;
    private final int numTeams = 2;
    private int turns = 0;

    @Override
    public GameBoard getBoard() {
        return board;
    }

    @Override
    public void makeMove(int x, int y) throws InvalidMoveException {
        turns++;
        int team = board.getCell(x, y).getTeam();
        try {
            if (team == 0 || team == teamTurn) {
                board.getCell(x, y).activate(teamTurn);
                nextTeam();
            } else throw new InvalidMoveException(0);
        }
        catch (StackOverflowError e){
            throw new InvalidMoveException(team);
        }
        int winner = whoWon();
        if(winner > 0 && turns >= numTeams) throw new InvalidMoveException(winner);
    }

    @Override
    public void makeMove(Cell c) throws InvalidMoveException {
        makeMove(c.getX(), c.getY());
    }

    @Override
    public int whoWon() {
        return board.whoWon();
    }

    @Override
    public void newGame() {
        board.clear();
        teamTurn = 1;
        turns = 0;
    }

    @Override
    public int getTurn() {
        return teamTurn;
    }

    private void nextTeam(){
        teamTurn++;
        if(teamTurn > numTeams){
            teamTurn = 1;
        }
    }
}
