package util;

import view.MainView;

public class ChainReactor {
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    public static void main(String[] args){
        MainView view = new MainView(WIDTH, HEIGHT, "Chain Reactor");
        view.start();
    }
}
