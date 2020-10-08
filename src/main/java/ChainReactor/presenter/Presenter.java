package ChainReactor.presenter;

import ChainReactor.model.GameObject;
import ChainReactor.model.board.Board;
import ChainReactor.model.cells.BasicCell;
import ChainReactor.model.cells.Cell;
import ChainReactor.util.TeamColors;
import ChainReactor.util.ViewUtils;
import ChainReactor.view.View;
import ChainReactor.view.listeners.MouseListener;
import ChainReactor.view.render.BoardView;
import ChainReactor.view.render.CellView;
import ChainReactor.view.render.ViewObject;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Presenter
{
    private boolean running = false;
    private final Board board;
    private final View view;

    private final Map<GameObject, ViewObject> gameObjects = new HashMap<>();

    private final ViewObject tmp = new ViewObject() {
        private Color color = Color.YELLOW;

        @Override
        public void render(Graphics g) {
            g.setColor(color);
            g.fillRect(0, 0, 10, 10);
        }

        @Override
        public void update() {
            color = (color == Color.YELLOW) ? Color.cyan : Color.YELLOW;
        }
    };

    public Presenter(Board board, View view) {
        this.board = board;
        this.view = view;
    }

    public void start() {
        initGame();
        //addToCell(board.iterator().next(), 0);
        reset();

        run();
    }

    private void initGame() {
        BoardView b = new BoardView(board);
        gameObjects.put(board, b);
        view.addRenderer(tmp);
        view.addRenderer(b);
        view.registerListener(new MouseListener(this));

        for(Cell c : board){
            CellView newCell = new CellView(b.X_OFFSET, b.Y_OFFSET, c);
            view.addRenderer(newCell);
            gameObjects.put(c, newCell);
        }

    }

    private void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 1.0; //60.0
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
            render();

            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer = System.currentTimeMillis();
                //System.out.println("FPS " + frames);
                frames = 0;
            }
        }
    }

    private void render(){
        view.render();
    }

    private void reset() {
        board.reset();
    }

    private void tick(){
        tmp.update();
        for(GameObject o : gameObjects.keySet()){
            o.tick();
        }
        //board.setTurn(board.getTurn() == 1 ? 0 : 1);
        //b.turn = board.getTurn();
    }

    private void changePlayerTurn(){
        board.setTurn(board.getTurn() == 1 ? 0 : 1);
        gameObjects.get(board).update();
    }

    private void addToCell(Cell cell, int team){
        cell.setTeam(team);
        cell.incrementCount();
//        try {
//            if (cell.incrementCount()) {
//                for (Cell c : cell.getNeighbors()) {
//                    addToCell(c, team);
//                }
//                view.addRenderer(new ViewObject(100, 100, 20, 20, 1000) {
//                    @Override
//                    public void render(Graphics g) {
//                        g.setColor(Color.YELLOW);
//                        defaultRender(g);
//                    }
//                });
//            }
//        }
//        catch(StackOverflowError ignored){
//
//        }
        changePlayerTurn();
        gameObjects.get(board).update();
        gameObjects.get(cell).update();
    }

    public void makeClick(Point point){

        for(Map.Entry<GameObject, ViewObject> o : gameObjects.entrySet()){
            if(ViewUtils.collide(point, o.getValue())){
                makeClick(o.getKey());
            }
        }
    }

    private void makeClick(GameObject o){
        if(board.getWinner() > -1){
            reset();
        }
        else if(Cell.class.isAssignableFrom(o.getClass())){
            if(((Cell) o).getTeam() != -1 && board.getTurn() != ((Cell) o).getTeam()){
                view.addRenderer(new ViewObject(1000) {
                    @Override
                    public void render(Graphics g) {
                        g.setColor(Color.WHITE);
                        g.drawChars("Invalid Move".toCharArray(), 0, "Invalid Move".length(), 20, 50);
                    }
                });

            }
            else addToCell((Cell) o, board.getTurn());
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}