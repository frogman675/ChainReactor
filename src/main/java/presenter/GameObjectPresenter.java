package presenter;

import model.cell.Cell;
import model.game.GameService;
import view.gameobject.CellView;
import view.gameobject.GameViewObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameObjectPresenter implements Presenter {

    private final GameService service;
    List<GameViewObject> objects = new ArrayList<>();

    public GameObjectPresenter(GameService service) {
        this.service = service;
    }

    @Override
    public void render(Graphics graphics){
        for(GameViewObject o : objects){
            o.render(graphics);
        }
    }

    public void tick() {
        for(GameViewObject o : objects){
            o.tick();
        }
    }

    public void addObject(GameViewObject o){
        objects.add(o);
    }

    public void addObjects(List<GameViewObject> o){
        objects.addAll(o);
    }

    public void removeObject(GameViewObject o){
        objects.remove(o);
    }

    public Cell getCell(Point p){
        for(GameViewObject c : objects) {
            if(c.getClass().equals(CellView.class)) {
                CellView v = (CellView)c;
                if (v.isInside(p)) {
                    return v.getCell();
                }
            }
        }
        return null;
    }

    public int getTeamTurn() {
        return service.getTurn();
    }
}
