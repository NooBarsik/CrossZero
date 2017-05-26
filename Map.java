/**
 * @author Чашников Михаил
 * @version dated 26 may 2017
 */
public class Map {
    String[][] gameMap = {{"[ ]","[ ]","[ ]"},
        {"[ ]","[ ]","[ ]"},
        {"[ ]","[ ]","[ ]"}
    };

    Map(){
    }

    public void setMap(int n, String displayChar){
        switch (n){
            case 7:
                this.gameMap[0][0] = displayChar;
                break;
            case 8:
                this.gameMap[0][1] = displayChar;
                break;
            case 9:
                this.gameMap[0][2] = displayChar;
                break;
            case 4:
                this.gameMap[1][0] = displayChar;
                break;
            case 5:
                this.gameMap[1][1] = displayChar;
                break;
            case 6:
                this.gameMap[1][2] = displayChar;
                break;
            case 1:
                this.gameMap[2][0] = displayChar;
                break;
            case 2:
                this.gameMap[2][1] = displayChar;
                break;
            case 3:
                this.gameMap[2][2] = displayChar;
                break;
            default:
                break;
        }
    }

    public boolean checkCell(int k){
        switch (k){
            case 7:
                return this.gameMap[0][0].equals("[ ]");
            case 8:
                return this.gameMap[0][1].equals("[ ]");
            case 9:
                return this.gameMap[0][2].equals("[ ]");
            case 4:
                return this.gameMap[1][0].equals("[ ]");
            case 5:
                return this.gameMap[1][1].equals("[ ]");
            case 6:
                return this.gameMap[1][2].equals("[ ]");
            case 1:
                return this.gameMap[2][0].equals("[ ]");
            case 2:
                return this.gameMap[2][1].equals("[ ]");
            case 3:
                return this.gameMap[2][2].equals("[ ]");
            default:
                return false;
        }
    }

    public boolean overflowState(){
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.gameMap[i][j].equals("[ ]")) counter++;
            }
        }
        return counter == 0;
    }

    public boolean winState(){
        for (int i = 0; i < 3; i++) {
            StringBuilder win = new StringBuilder();
            String cell = this.gameMap[i][0];
            for (int j = 0; j < 3; j++) {
                if (this.gameMap[i][j].equals(cell) && !this.gameMap[i][j].equals("[ ]")) win.append(this.gameMap[i][j]);
            }
            if (win.toString().equals("[x][x][x]") || win.toString().equals("[o][o][o]")) return true;
        }

        for (int i = 0; i < 3; i++) {
            StringBuilder win = new StringBuilder();
            String cell = this.gameMap[0][i];
            for (int j = 0; j < 3; j++) {
                if (this.gameMap[j][i].equals(cell) && !this.gameMap[j][i].equals("[ ]")) win.append(this.gameMap[j][i]);
            }
            if (win.toString().equals("[x][x][x]") || win.toString().equals("[o][o][o]")) return true;
        }

        StringBuilder win = new StringBuilder();
        String cell = this.gameMap[0][0];
        for (int j = 0; j < 3; j++) {
            if (this.gameMap[j][j].equals(cell) && !this.gameMap[j][j].equals("[ ]")) win.append(this.gameMap[j][j]);
        }
        if (win.toString().equals("[x][x][x]") || win.toString().equals("[o][o][o]")) return true;

        StringBuilder win1 = new StringBuilder();
        cell = this.gameMap[0][2];
        for (int j = 0; j < 3; j++) {
            int i = 2 - j;
            if (this.gameMap[j][i].equals(cell) && !this.gameMap[j][i].equals("[ ]")) win1.append(this.gameMap[j][i]);
        }
        if (win1.toString().equals("[x][x][x]") || win1.toString().equals("[o][o][o]")) return true;

        return false;
    }
}
