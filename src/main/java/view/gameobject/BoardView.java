package view.gameobject;

import model.board.GameBoard;
import model.cell.Cell;
import presenter.GameObjectPresenter;
import util.ChainReactor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardView extends GameViewObject {
    private final List<CellView> cells;
    GameBoard board;
    public BoardView(GameObjectPresenter presenter, GameBoard board) {
        super(presenter, ID.BOARD);
        int xOffset = (ChainReactor.WIDTH / 2) - ((board.getWidth() / 2) * CellView.CELL_WIDTH);
        int yOffset = (ChainReactor.HEIGHT / 2) - ((board.getHeight() / 2) * CellView.CELL_HEIGHT);
        cells = new ArrayList<>();
        this.board = board;
        for(Cell c : board){
            presenter.addObject(new CellView(presenter, c, xOffset, yOffset));
            cells.add(new CellView(presenter, c, xOffset, yOffset));
        }
    }

    public List<CellView> getCells(){
        return cells;
    }

    @Override
    public void render(Graphics graphics) {
        //graphics.drawPolygon(new Hexagon());
        //for(CellView c : cells){
        //    c.render(graphics);
        //}
    }

    @Override
    public void tick() {
        //board.tick();
    }
}
