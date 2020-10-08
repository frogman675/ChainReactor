package ChainReactor.util;

import ChainReactor.view.render.ViewObject;

import java.awt.*;

public class ViewUtils {
    public static boolean collide(Point p, ViewObject o){
        return p.x >= o.getPosition().x && p.x <= (o.getPosition().x + o.getSize().width)
                && p.y >= o.getPosition().y && p.y <= (o.getPosition().y + o.getSize().height);
    }
}
