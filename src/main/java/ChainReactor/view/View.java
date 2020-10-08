package ChainReactor.view;

import ChainReactor.view.render.ViewObject;

import java.awt.event.MouseAdapter;

public interface View {
    void addRenderer(ViewObject renderer);
    void removeRenderer(ViewObject renderer);
    void render();

    void registerListener(MouseAdapter adapter);

}