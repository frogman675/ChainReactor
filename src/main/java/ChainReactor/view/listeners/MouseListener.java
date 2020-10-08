package ChainReactor.view.listeners;

import ChainReactor.presenter.Presenter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {
    private final Presenter presenter;

    public MouseListener(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void mouseClicked(MouseEvent m) {
        presenter.makeClick(m.getPoint());
    }
}
