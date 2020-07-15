package view.listener;

import presenter.MainPresenter;

import java.awt.event.MouseEvent;

public class GameMouseInput extends MouseInput {

    public GameMouseInput(MainPresenter presenter) {
        super(presenter);
    }

    @Override
    public void mouseClicked(MouseEvent m){
        if(enabled)
        //System.out.println(m.getPoint().x + ", " + m.getPoint().y);
        presenter.makeClick(m.getPoint());
    }
}
