package view.listener;

import presenter.MainPresenter;

import java.awt.event.MouseAdapter;

public abstract class MouseInput extends MouseAdapter {
    protected final MainPresenter presenter;
    protected boolean enabled = true;

    public MouseInput(MainPresenter presenter) {
        this.presenter = presenter;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
}
