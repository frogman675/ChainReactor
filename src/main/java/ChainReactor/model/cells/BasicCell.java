package ChainReactor.model.cells;

import ChainReactor.model.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicCell implements Cell {
    private int team;
    private int count;
    private Position pos;
    private List<Cell> neighbors;

    public BasicCell(int x, int y){
        neighbors = new ArrayList<>();
        pos = new Position(x, y);
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int getNeighborCount() {
        return neighbors.size();
    }

    @Override
    public Position getPosition() {
        return pos;
    }

    @Override
    public void incrementCount() {
        count++;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void reset() {
        team = -1;
        count = 0;
    }

    @Override
    public List<Cell> getNeighbors() {
        return neighbors;
    }

    @Override
    public void addNeighbor(Cell... c) {
        neighbors.addAll(Arrays.asList(c));
    }

    @Override
    public void tick() {
        if(count >= getNeighborCount()){
            for(Cell n : getNeighbors()){
                n.setTeam(getTeam());
                n.incrementCount();
            }
            setCount(0);
        }
    }
}
