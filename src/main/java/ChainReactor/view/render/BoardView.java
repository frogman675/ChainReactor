package ChainReactor.view.render;

import ChainReactor.model.board.Board;
import ChainReactor.util.TeamColors;
import ChainReactor.view.Gui;

import java.awt.*;

public class BoardView extends ViewObject {
    public final int X_OFFSET;
    public final int Y_OFFSET;
    private final Board board;

    private int turn = 0;
    private int winner = -1;
    public BoardView(Board board){
        position = new Point(0, 0);
        size = new Size(Gui.WIDTH, Gui.HEIGHT);
        this.board = board;
        X_OFFSET = (Gui.WIDTH / 2) - ((board.getWidth() / 2) * CellView.CELL_WIDTH);
        Y_OFFSET = (Gui.HEIGHT / 2) - ((board.getHeight() / 2) * CellView.CELL_HEIGHT);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(TeamColors.getColor(turn));
        String teamTurn = ("Team " + (turn + 1) + "'s turn");
        g.drawChars(teamTurn.toCharArray(), 0, teamTurn.length(), 30, 30);

        if(winner > -1){
            g.setColor(TeamColors.getColor(winner));
            String winnerStr = ("Team " + (winner + 1) + " won!");
            g.drawChars(winnerStr.toCharArray(), 0, winnerStr.length(), 30, 70);
        }
    }

    @Override
    public void update() {
        turn = board.getTurn();
        winner = board.getWinner();
    }

    @Override
    public boolean dead() {
        return false;
    }
}
