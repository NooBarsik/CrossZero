

/**
 * @author Чашников Михаил
 * @version dated 26 may 2017
 */

import java.util.*;

public class AIPlayer {
    String displayChar;
    int currentTurnCell;

    AIPlayer(char ch){
        this.displayChar = "[" + ch + "]";
    }


    int getCurrentTurnCell(String[][] map){
        int result;
        int[][] xy = {{7,8,9},
                {4,5,6},
                {1,2,3}
        };
        int x1 = 0;
        int y1 = 0;
        int x = -1;
        int y = -1;
        int counterX = 0;
        int counterNull = 0;

        for (int i = 0; i < 3; i++) {
            counterX = 0;
            counterNull = 0;
            for (int j = 0; j < 3; j++) {
                if (map[i][j].equals("[x]")) counterX++;
                if (map[i][j].equals("[ ]")) {
                    counterNull++;
                    x1 = i;
                    y1 = j;
                }
                if (counterNull == 1 && counterX == 2){
                    x = x1;
                    y = y1;
                }
            }
        }

        x1 = 0;
        y1 = 0;
        for (int i = 0; i < 3; i++) {
            counterX = 0;
            counterNull = 0;
            for (int j = 0; j < 3; j++) {
                if (map[j][i].equals("[x]")) counterX++;
                if (map[j][i].equals("[ ]")) {
                    counterNull++;
                    x1 = j;
                    y1 = i;
                }
                if (counterNull == 1 && counterX == 2){
                    x = x1;
                    y = y1;
                }
            }
        }

        counterX = 0;
        counterNull = 0;
        for (int j = 0; j < 3; j++) {
            if (map[j][j].equals("[x]")) counterX++;
            if (map[j][j].equals("[ ]")) {
                counterNull++;
                x1 = j;
                y1 = j;
            }
            if (counterNull == 1 && counterX == 2){
                x = x1;
                y = y1;
            }
        }

        counterX = 0;
        counterNull = 0;
        x1 = 0;
        y1 = 0;
        for (int j = 0; j < 3; j++) {
            int i = 2 - j;
            if (map[j][i].equals("[x]")) counterX++;
            if (map[j][i].equals("[ ]")) {
                counterNull++;
                x1 = j;
                y1 = i;
            }
            if (counterNull == 1 && counterX == 2){
                x = x1;
                y = y1;
            }
        }

        if (x != -1 && y != -1) result = xy[x][y];
        else{Random random = new Random();
            int rand = random.nextInt(8);
            result = rand + 1;
        }

        return result;
    }

}
