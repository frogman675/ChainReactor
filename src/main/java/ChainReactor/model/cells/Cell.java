package ChainReactor.model.cells;

import ChainReactor.model.GameObject;
import ChainReactor.model.Position;

import java.util.List;

public interface Cell extends GameObject {
    void reset();
    List<Cell> getNeighbors();
    void addNeighbor(Cell... c);
    void setTeam(int team);
    void setCount(int count);
    int getTeam();
    int getCount();
    int getNeighborCount();
    Position getPosition();

    void incrementCount();
}
