package model.board;

import model.cell.BasicCell;
import model.cell.Cell;

import java.util.Iterator;

public class SquareBoard implements GameBoard {
    private Cell[][] board;
    private int[] offset;
    //Pair<Integer, Integer> pair;
    //Map<AbstractMap.SimpleImmutableEntry<Integer, Integer>, >
    //Map<Coordinates, List<Coordinates>> connections = new HashMap<>();
    int width = 7;
    int height = 3;

    public SquareBoard(){
        initBoard();
    }

    @Override
    public void initBoard() {
        board = new Cell[width][];
        offset = new int[width];
        for(int i = 0; i < board.length; i++) {
            board[i] = new Cell[height];
        }
        //offset[0] = 1;
        //offset[6] = 1;

        initCells();
    }

    private void initCells(){
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = new BasicCell(i , j);
            }
            board[0][0] = null;
            board[0][2] = null;
            board[6][0] = null;
            board[6][2] = null;
            board[3][1] = null;
        }
        for(Cell c : this){
            if (c.getX() - 1 >= 0)
                c.link(board[c.getX() - 1][c.getY()]);
            if (c.getY() - 1 >= 0)
                c.link(board[c.getX()][c.getY() - 1]);
            if (c.getX() + 1 < width)
                c.link(board[c.getX() + 1][c.getY()]);
            if (c.getY() + 1 < height)
                c.link(board[c.getX()][c.getY() + 1]);
            /*
            for(int i = minX; i <= maxX; i++){
                for(int j = minY; j <= maxY; j++){
                    c.link(board[i][j]);
                }
            }

             */

        }

        //board[1][1].activate(1);
        //board[1][1].activate(1);
        //board[1][0].activate(2);
        /*
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j].link();
            }
        }

         */
    }

    @Override
    public Cell getCell(int x, int y) {
        return board[x][y];
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int whoWon() {
        int team = 0;
        for(Cell cell : this){
            if(cell.getTeam() != 0 && team != 0 && cell.getTeam() != team) return 0;
            else if(cell.getTeam() != 0) team = cell.getTeam();
        }
        return team;
    }

    @Override
    public void clear() {
        for(Cell c : this){
            c.clear();
        }
    }

    public int[] getOffset() {
        return offset;
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
                Cell c = getCell(currentx, currenty);
                if(++currenty >= board[currentx].length){
                    currenty = 0;
                    if(++currentx >= board.length) {
                        currentx = 0;
                        next = false;
                    }
                }
                if(next && peek() == null) notNullNext();
                if(c == null) return next();
                else return c;
            }

            private Cell notNullNext(){
                Cell c = getCell(currentx, currenty);
                if(++currenty >= board[currentx].length){
                    currenty = 0;
                    if(++currentx >= board.length) {
                        currentx = 0;
                        next = false;
                    }
                }
                while(next && peek() == null) notNullNext();
                return c;
            }

            private Cell peek(){
                return getCell(currentx, currenty);
            }
        };
    }
}
