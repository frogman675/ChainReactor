package model.board;

import model.board.SquareBoard;
import model.cell.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SquareBoardTest {
    SquareBoard board;
    @BeforeEach
    public void setup(){
        board = new SquareBoard();
    }

    @Test
    public void testIterator(){
        for(Cell c : board){
            Assertions.assertNotNull(c);
        }
    }
}
