package ChainReactor.view.render;

import ChainReactor.model.GameObject;

import java.awt.*;

public abstract class ViewObject {
    protected Point position;
    protected Size size;
    protected int ttl;

    private final long time = System.currentTimeMillis();
    public abstract void render(Graphics g);
    public void update(){

    }
    public boolean dead(){
        if(ttl < 0) return false;
        return (System.currentTimeMillis() - time) >= ttl;
    }

    protected ViewObject(){
        ttl = -1;
    }

    public ViewObject(int x, int y, int width, int height, int ttl){
        size = new Size(width, height);
        position = new Point(x, y);
        this.ttl = ttl;
    }

    public ViewObject(int ttl){
        this.ttl = ttl;
    }

    public Point getPosition() {
        return position;
    }

    public Size getSize() {
        return size;
    }

    public static class Size{
        public Size(int width, int height){
            this.width = width;
            this.height = height;
        }

        public int width;
        public int height;
    }

    public void defaultRender(Graphics g){
        g.fillRect(position.x, position.y, size.width, size.height);
    }
}
