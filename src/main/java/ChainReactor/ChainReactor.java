package ChainReactor;

import ChainReactor.model.board.Board;
import ChainReactor.model.board.SimpleBoard;
import ChainReactor.presenter.Presenter;
import ChainReactor.view.Gui;
import ChainReactor.view.View;

public class ChainReactor implements Runnable {
    private Thread thread;
    private Presenter presenter;

    public static void main(String[] args){
        //EventQueue.invokeLater(new ChainReactor());
        new ChainReactor().start();
    }

    @Override
    public void run() {
        presenter.setRunning(true);
        presenter.start();
    }

    public synchronized void stop(){
        try{
            thread.join();
            presenter.setRunning(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void start(){
        Board board = new SimpleBoard();
        View view = new Gui();
        presenter = new Presenter(board, view);
        thread = new Thread(this);
        thread.start();
    }
}