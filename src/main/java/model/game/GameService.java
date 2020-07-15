package model.game;

import model.cell.Cell;
import presenter.GameObjectPresenter;
import presenter.MainPresenter;
import view.gameobject.BoardView;

import java.awt.*;

public class GameService implements Runnable{
    private boolean running = false;
    private Thread thread;

    private final MainPresenter main;
    private final GameObjectPresenter gamePresenter;

    private final Game game = new GameManager();

    public GameService(MainPresenter main){
        this.main = main;
        gamePresenter = new GameObjectPresenter(this);
        gamePresenter.addObject(new BoardView(gamePresenter, game.getBoard()));
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
            }
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer = System.currentTimeMillis();
                //System.out.println("FPS " + frames);
                frames = 0;
            }
        }
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void tick(){
        gamePresenter.tick();
    }

    public void render(){
        Graphics graphics = main.makeGraphics();
        if(graphics == null) return;
        main.render(graphics);
        gamePresenter.render(graphics);

        main.showGraphics(graphics);
    }

    public void makeClick(Point point) throws InvalidMoveException {
        Cell c = gamePresenter.getCell(point);
        if(c == null) return;
        game.makeMove(c);
    }

    public void newGame() {
        game.newGame();
    }

    public int getTeamTurn() {
        return gamePresenter.getTeamTurn();
    }

    public int getTurn() {
        return game.getTurn();
    }
}
