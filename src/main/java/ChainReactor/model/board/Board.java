package ChainReactor.model.board;

import ChainReactor.model.GameObject;
import ChainReactor.model.cells.Cell;

public interface Board extends GameObject, Iterable<Cell>{
    //Cell[][] getBoard();
    void reset();

    void setTurn(int turn);
    int getTurn();
    int getWinner();

    int getWidth();
    int getHeight();
}
