/**
  @author Чашников Михаил
 * @version dated 26 may 2017
 */

import java.io.*;
import java.util.*;

public class CrossZero {

    public static void main (String[] args){

        while (true){
            Map game = new Map();

            HumanPlayer hPl = new HumanPlayer('x');
            AIPlayer aiPlayer = new AIPlayer('o');

            while (true){
                while(true){
                    int hC = hPl.getCurrentTurnCell(game.gameMap);
                    if (game.checkCell(hC)){
                        if (!game.overflowState()) {
                            game.setMap(hC, hPl.displayChar);
                            break;
                        }
                        else {
                            System.out.println("Ничья.");
                            break;
                        }
                    }
                    else System.out.println("Клетка занята, выберите другую клетку.");
                }
                if (game.overflowState()) {
                    System.out.println("Ничья.");
                    break;
                }
                if (game.winState()) {
                    System.out.println("Вы победили.");
                    break;
                }

                while(true){
                    int aiC = aiPlayer.getCurrentTurnCell(game.gameMap);
                    if (game.checkCell(aiC)){
                        game.setMap(aiC, aiPlayer.displayChar);
                        break;
                    }
                }
                if (game.winState()) {
                    System.out.println("Вы проиграли.");
                    break;
                }
            }
            System.out.println("Играть еще раз. Да -- 1, нет -- 0.");
            if (!playAgain()) break;
        }
    }

    private static boolean playAgain(){
        boolean p;
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try{
                int a = Integer.parseInt(r.readLine());
                if (a == 0 || a == 1){
                    return !(a == 0);
                }
                else System.out.println("Неверный ввод. Введите 0 или 1.");
            }
            catch (Exception e){
                System.out.println("Неверный ввод. Введите 0 или 1.");
            }
        }
    }
}





