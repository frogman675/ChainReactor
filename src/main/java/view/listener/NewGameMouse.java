package view.listener;

import presenter.MainPresenter;

import java.awt.event.MouseEvent;

public class NewGameMouse extends MouseInput {
    public NewGameMouse(MainPresenter presenter) {
        super(presenter);
    }

    @Override
    public void mouseClicked(MouseEvent m){
        if(enabled)
            //System.out.println(m.getPoint().x + ", " + m.getPoint().y);
            presenter.newGame();
    }
}
