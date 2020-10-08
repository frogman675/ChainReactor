package ChainReactor.util;

import java.awt.*;

public class TeamColors {
    public static Color getColor(int team){
        switch (team){
            case 0:
                return Color.red;
            case 1:
                return Color.blue;
            default:
                return Color.black;
        }
    }
}
