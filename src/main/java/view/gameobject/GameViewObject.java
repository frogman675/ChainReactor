package view.gameobject;

import presenter.GameObjectPresenter;

import java.awt.*;
import java.util.Objects;

public abstract class GameViewObject {
    protected final GameObjectPresenter presenter;
    private final view.gameobject.ID ID;

    protected GameViewObject(GameObjectPresenter presenter, view.gameobject.ID id) {
        this.presenter = presenter;
        ID = id;
    }

    public abstract void render(Graphics graphics);

    public view.gameobject.ID getID(){
        return ID;
    }

    public abstract void tick();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameViewObject that = (GameViewObject) o;
        return ID.equals(that.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
