package ChainReactor.view;

import ChainReactor.model.board.Board;
import ChainReactor.view.render.ViewObject;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;
public class Gui implements View {
    public static final int WIDTH = 640, HEIGHT = 480;

    private Canvas canvas = new Canvas();
    private Board gameBoard;
    private BufferStrategy bs;

    List<ViewObject> renderObjects = new ArrayList<>();
    public Gui() {
        JFrame frame = new JFrame("Game");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(canvas);
        frame.setVisible(true);
    }

    @Override
    public void addRenderer(ViewObject renderer) {
        renderObjects.add(renderer);
    }

    @Override
    public void removeRenderer(ViewObject renderer) {
        renderObjects.remove(renderer);
    }

    @Override
    public void render(){
        Graphics g = makeGraphics();
        if(g == null) return;

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        Iterator<ViewObject> i = renderObjects.iterator();
        while(i.hasNext()){
            ViewObject r = i.next();
            if(r.dead()) i.remove();
            else r.render(g);
        }

        showGraphics(g);
    }

    @Override
    public void registerListener(MouseAdapter adapter) {
        canvas.addMouseListener(adapter);
    }


    private Graphics makeGraphics() {
        bs = canvas.getBufferStrategy();
        if(bs == null){
            canvas.createBufferStrategy(3);
            return null;
        }
        return bs.getDrawGraphics();
    }

    private void showGraphics(Graphics graphics){
        graphics.dispose();
        bs.show();
    }
}