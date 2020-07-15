package view;

import presenter.MainPresenter;
import util.TeamColors;
import view.listener.GameMouseInput;
import view.listener.MouseInput;
import view.listener.NewGameMouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class MainView extends Canvas implements MainPresenter.View {
    private final MainPresenter presenter;
    private final int width, height;

    private final MouseInput gamelistener;
    private final MouseInput newgamelistener;

    private BufferStrategy bs;

    private int invalidMove = 0;
    private int winner = 0;

    public MainView(int width, int height, String title){
        presenter = new MainPresenter(this);
        this.width = width;
        this.height = height;

        gamelistener = new GameMouseInput(presenter);
        this.addMouseListener(gamelistener);
        newgamelistener = new NewGameMouse(presenter);
        newgamelistener.setEnabled(false);
        this.addMouseListener(newgamelistener);

        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);
    }

    public void start(){
        presenter.start();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, width, height);
        int turn = presenter.getTeamTurn();
        graphics.setColor(TeamColors.getTeamColor(turn));
        String teamTurn = ("Team " + turn + "'s turn");
        graphics.drawChars(teamTurn.toCharArray(), 0, teamTurn.length(), 30, 30);
        if(invalidMove > 0){
            graphics.setColor(Color.WHITE);
            graphics.drawChars("Invalid Move".toCharArray(), 0, 12, width / 2, height / 2 - 100);
            invalidMove--;
        }
        if(winner > 0){
            graphics.setColor(TeamColors.getTeamColor(winner));
            graphics.drawChars("You Win".toCharArray(), 0, 7, width / 2, height / 2 - 100);
            gamelistener.setEnabled(false);
            newgamelistener.setEnabled(true);
        }
    }

    @Override
    public void printInvalidMove() {
        invalidMove = 720;
    }

    @Override
    public void printWinner(int winner) {
        this.winner = winner;
    }

    @Override
    public void newGame() {
        winner = 0;
        gamelistener.setEnabled(true);
        newgamelistener.setEnabled(false);
    }

    @Override
    public Graphics makeGraphics() {
        bs = this.getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return null;
        }
        return bs.getDrawGraphics();
    }

    @Override
    public void showGraphics(Graphics graphics) {
        graphics.dispose();
        bs.show();
    }
}
