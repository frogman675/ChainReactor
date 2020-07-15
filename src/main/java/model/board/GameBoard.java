package model.board;

import model.cell.Cell;

public interface GameBoard extends Iterable<Cell> {
    void initBoard();
    Cell getCell(int x, int y);
    int getWidth();
    int getHeight();

    int whoWon();

    void clear();
}
