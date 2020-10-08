package ChainReactor.model.board;

import ChainReactor.model.cells.BasicCell;
import ChainReactor.model.cells.Cell;

import java.util.Iterator;

public class SimpleBoard implements Board {
    private final Cell[][] board;
    private int turn;

    public SimpleBoard(){
        board = new Cell[][]{
                {new BasicCell(0, 0), new BasicCell(0, 1)},
                {new BasicCell(1, 0), new BasicCell(1, 1)}
        };

        board[0][0].addNeighbor(board[0][1], board[1][0]);
        board[0][1].addNeighbor(board[0][0], board[1][1]);
        board[1][0].addNeighbor(board[0][0], board[1][1]);
        board[1][1].addNeighbor(board[0][1], board[1][0]);

        board[0][0].setCount(1);
        board[0][0].setTeam(1);
    }

    //@Override
    //public Cell[][] getBoard(){
    //    return  board;
    //}

    @Override
    public void reset() {
        turn = 0;
        for(Cell[] row : board){
            for(Cell c : row){
                c.reset();
            }
        }
    }

    @Override
    public void setTurn(int turn) {
        this.turn = turn;
    }

    @Override
    public int getTurn() {
        return turn;
    }

    @Override
    public int getWinner() {
        int team = -1;
        int count = 0;
        for(Cell c : this){
            count += c.getCount();
            if(c.getTeam() != -1) {
                if (team == -1) team = c.getTeam();
                else if (team != c.getTeam()) return -1;
            }
        }
        if(count <= 2) return -1;
        return team;
    }

    @Override
    public int getWidth() {
        return 2;
    }

    @Override
    public int getHeight() {
        return 2;
    }

    @Override
    public Iterator<Cell> iterator() {
        return new Iterator<>() {
            int currentx = 0;
            int currenty = 0;
            boolean next = board.length > 0;
            @Override
            public boolean hasNext() {
                return next;
            }

            @Override
            public Cell next() {
                Cell c = board[currentx][currenty];
                if(++currentx >= board.length){
                    currentx = 0;
                    if(++currenty >= board.length) {
                        next = false;
                    }
                }
                return c;
            }
        };
    }

    @Override
    public void tick() {

    }
}
