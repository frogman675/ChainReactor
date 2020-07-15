package view.gameobject;

import model.cell.Cell;
import presenter.GameObjectPresenter;

import java.awt.*;

import static util.TeamColors.getTeamColor;

public class CellView extends GameViewObject {
    public static final int CELL_WIDTH = 21;
    public static final int CELL_HEIGHT = CELL_WIDTH;
    private final Cell cell;
    private final Point screenPosition;

    public Point getScreenPosition() {
        return screenPosition;
    }

    public Cell getCell(){
        return cell;
    }

    protected CellView(GameObjectPresenter presenter, Cell cell) {
        super(presenter, view.gameobject.ID.CELL);
        this.cell = cell;
        screenPosition = new Point();
    }

    public CellView(GameObjectPresenter presenter, Cell cell, int xOffset, int yOffset) {
        super(presenter, view.gameobject.ID.CELL);
        this.cell = cell;
        int xcorner = cell.getX() * CELL_WIDTH + xOffset;
        int ycorner = cell.getY() * CELL_HEIGHT + yOffset;
        screenPosition = new Point(xcorner, ycorner);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.drawRect(screenPosition.x, screenPosition.y, CELL_WIDTH, CELL_HEIGHT);
        graphics.setColor(Color.gray);
        graphics.fillRect(screenPosition.x + 1, screenPosition.y + 1, CELL_WIDTH - 1, CELL_HEIGHT - 1);
        drawBlocksInCells(graphics);
    }

    @Override
    public void tick() {
        cell.tick();
    }

    private void drawBlocksInCells(Graphics graphics){
        graphics.setColor(getTeamColor(cell.getTeam()));
        for(int i = 0; i < cell.getCount(); i++){
            int width = CELL_WIDTH - 2;
            int height;
            if(cell.getNeighborCount() - 1 == 0)
                height = CELL_HEIGHT - 2;
            else height = (CELL_HEIGHT - cell.getNeighborCount()) / (cell.getNeighborCount() - 1);
            int x = (screenPosition.x + 1);
            int y = (screenPosition.y + 1) + (i * height + i);
            graphics.fillRect(x, y, width, height);
            //cell.setTeam(cell.getTeam() + 1);

        }
    }

    public boolean isInside(Point p) {
        return screenPosition.x <= p.x && (screenPosition.x + CellView.CELL_WIDTH) >= p.x &&
                screenPosition.y <= p.y && (screenPosition.y + CellView.CELL_HEIGHT) >= p.y;
    }
}
