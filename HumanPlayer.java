/**
 @author Чашников Михаил
  * @version dated 26 may 2017
 */
import java.io.*;

class HumanPlayer{
    String displayChar;
    int currentTurnCell;

    HumanPlayer(char ch){
        this.displayChar = "[" + ch + "]";
    }

    int getCurrentTurnCell(String[][] map){

        System.out.println("\n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

        System.out.println("\nВведите номер клетки, в которую хотите сделать ход.");

        System.out.println("[7][8][9]");
        System.out.println("[4][5][6]");
        System.out.println("[1][2][3]");

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                int x = Integer.parseInt(r.readLine());
                if (1 <= x && x < 10) return x;
                else System.out.println("Неверный ввод. Введите целое число от 1 до 9.");
            }
            catch (Exception e){
                System.out.println("Неверный ввод. Введите целое число от 1 до 9.");
            }
        }
    }
}
