package ChainReactor.view.render;

import ChainReactor.model.cells.Cell;
import ChainReactor.util.TeamColors;

import java.awt.*;

public class CellView extends ViewObject {
    public static final int CELL_WIDTH = 20;
    public static final int CELL_HEIGHT = CELL_WIDTH;
    private int team;
    private int count;

    private final Cell cell;

    public CellView(int xoff, int yoff, Cell cell) {
        this.cell = cell;
        int xcorner = cell.getPosition().x * CELL_WIDTH + xoff;
        int ycorner = cell.getPosition().y * CELL_HEIGHT + yoff;
        position = new Point(xcorner, ycorner);
        size = new Size(CELL_WIDTH, CELL_HEIGHT);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.drawRect(position.x, position.y, size.width, size.height);
        graphics.setColor(Color.gray);
        graphics.fillRect(position.x + 1, position.y + 1, size.width - 1, size.height - 1);
        drawBlocksInCells(graphics);
    }

    @Override
    public void update() {
        team = cell.getTeam();
        count = cell.getCount();
    }

    @Override
    public boolean dead() {
        return false;
    }

    private void drawBlocksInCells(Graphics graphics){
        graphics.setColor(TeamColors.getColor(team));
        graphics.drawChars(Integer.toString(count).toCharArray(), 0, 1, position.x, position.y);
//        for(int i = 0; i < count; i++){
//            int width = size.width - 2;
//            int height;
//            if(cell.getNeighborCount() - 1 == 0)
//                height = size.height - 2;
//            else height = (size.height - cell.getNeighborCount()) / (cell.getNeighborCount() - 1);
//            int x = (position.x + 1);
//            int y = (position.y + 1) + (i * height + i);
//            graphics.fillRect(x, y, width, height);
//            //cell.setTeam(cell.getTeam() + 1);
//
//        }
    }
}
