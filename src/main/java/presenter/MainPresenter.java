package presenter;

import model.game.GameService;
import model.game.InvalidMoveException;

import java.awt.*;

public class MainPresenter implements Presenter{
    private final View view;

    private final GameService service;

    public MainPresenter(View view){
        this.view = view;
        service = new GameService(this);
    }

    public void start(){
        service.start();
    }


    @Override
    public void render(Graphics graphics){
        view.render(graphics);
    }

    public void makeClick(Point point) {
        try {
            service.makeClick(point);
        } catch (InvalidMoveException e) {
            if(e.winner == 0){
                view.printInvalidMove();
            }
            else view.printWinner(e.winner);
        }
    }

    public void newGame() {
        service.newGame();
        view.newGame();
    }

    public int getTeamTurn() {
        return service.getTeamTurn();
    }

    public Graphics makeGraphics() {
        return view.makeGraphics();
    }

    public void showGraphics(Graphics graphics) {
        view.showGraphics(graphics);
    }

    public interface View {
        void render(Graphics graphics);
        void printInvalidMove();
        void printWinner(int winner);
        void newGame();
        Graphics makeGraphics();
        void showGraphics(Graphics graphics);
    }


}
