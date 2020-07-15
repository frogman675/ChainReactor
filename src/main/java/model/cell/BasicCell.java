package model.cell;

public class BasicCell extends Cell {
    private int activations = 0;

    public BasicCell(int x, int y) {
        super(x, y);
    }

    @Override
    public void activate(int team) {
        activations++;
        this.team = (team == -1) ? this.team : team;
    }

    @Override
    public void tick() {
        if(activations > 0){
            activations--;
            count++;
            if(count >= getNeighborCount()) {
                count -= getNeighborCount();
                for (Cell c : neighbors) {
                    c.activate(this.team);
                }
            }
        }
    }

    @Override
    public void clear() {
        count = 0;
        team = 0;
    }
}