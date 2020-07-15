package util;

import java.awt.*;

public class TeamColors {
    public static Color getTeamColor(int team){
        switch (team){
            case 1:
                return Color.RED;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.YELLOW;
            case 4:
                return Color.GREEN;
            default:
                return Color.BLACK;
        }
    }
}
