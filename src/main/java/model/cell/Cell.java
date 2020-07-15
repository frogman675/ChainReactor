package model.cell;

import model.board.Coordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Cell {
    private Renderer renderer;
    public interface Renderer{

    }

    protected List<Cell> neighbors = new ArrayList<>();
    protected int count = 0;
    protected int team = 0;
    private Coordinates coordinates;

    public void setTeam(int team){
        this.team = team;
    }

    public Cell(int x, int y){
        coordinates = new Coordinates(x, y);
    }

    public void link(Cell... cells){
        if(cells != null && cells.length > 0 && cells[0] != null)
            neighbors.addAll(Arrays.asList(cells));
    }

    public int getNeighborCount(){
        return neighbors.size();
    }

    public int getTeam(){
        return (count == 0) ? 0 : team;
    }

    public int getCount() {
        return count;
    }

    public void setCoordinates(int x, int y) {
        this.coordinates = new Coordinates(x, y);
    }

    public int getX(){
        return coordinates.x;
    }

    public int getY(){
        return coordinates.y;
    }

    public abstract void clear();
    public abstract void activate(int team);
    public abstract void tick();
}
