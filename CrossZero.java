/**
 * @author Чашников Михаил
 * @version dated 20 may 2017
 * В программе реализованы следующие отличия от исходной программы.
 * 1. Изменен дизайн игрового поля.
 * 2. Для выбора хода нужно вводить не координаты, а номер поля. Для удобства пользователя нумерация полей выполнена
 * в соответствии с нумерацией клавиш на цифровом блоке клавиатуры.
 * 3. Проверка победы выполняется с помощью цикла.
 * 4. Реализован ИИ, блокирующий победный ход игрока, если это возможно.
 */

import java.io.*;
import java.util.*;

public class CrossZero {

    public static void main (String[] args){
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            // Инициализируем игровое поле
            String[][] map = initializeMap();
            while(true){
                // Выводим на экран игровое поле
                printMap(map);

                // Запрашиваем ввод хода пользователя
                System.out.println("\nВведите номер поля, в которое хотите сделать ход.");

                while (true){
                    int h = getUserInput();
                    if (checkCell(map, h)) {   //Проверяем, не занято ли поле, выбранное пользователем
                        turnProcessing(map, h, 'x'); //Заполняем поле символом 'х'
                        break;
                    }
                    else System.out.println("Поле занято, выберите другое поле.");
                }

                // Проверяем нет ли победы
                if (winState(map)) {
                    printMap(map);
                    System.out.println("Вы победили!");
                    break;
                }

                // Проверяем, не заполнено ли игровое поле
                if (overflowState(map)) {
                    printMap(map);
                    System.out.println("Ничья.");
                    break;
                }

                // Выводим на экран игровое поле

                // Запрашиваем ход ИИ
                while (true){
                    int a = aiTurn(map); //Генерируем ход ИИ
                    if (checkCell(map, a)){  //Проверяем, не занято ли поле, выбранное ИИ
                        turnProcessing(map, a, 'o'); //Заполняем поле символом 'о'
                        break;
                    }
                }

                // Проверяем нет ли победы
                if (winState(map)) {
                    printMap(map);
                    System.out.println("Вы проиграли.");
                    break;
                }

                // Проверяем, не заполнено ли игровое поле
                if (overflowState(map)) {
                    printMap(map);
                    System.out.println("Ничья.");
                    break;
                }

                // Выводим на экран игровое поле
                // *Завершаем цикл
            }
            //Запрос новой игры
            System.out.println("Играть еще раз. Да -- 1, нет -- 0.");
            if (!playAgain()) break;
        }
    }

    private static int getUserInput(){

        // Для удобства пользователя на экран выводится карта с номерами полей
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

    // Метод просматривает друг за другом сначала строки, затем стобцы, затем диагонали. При обнаружении одного
    // свободного поля при двух других, занятых символом 'x', метод возвращает в программу координаты свободного поля.
    // Если такой комбинации не нашлось, метод возвращает случайное число от 1 до 9.

    private static int aiTurn(String[][] map){
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

    private static String[][] initializeMap(){
        String[][] map = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                map[i][j] = "[ ]";
            }
        }
        return map;
    }

    private static void printMap(String [][] map){
        System.out.println("\n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    //Метод просматривает друг за другом сначала строки, затем стобцы, затем диагонали. При обнаружении строки, столбца
    //или диагонали, заполненных одинаковыми символами, возвращается значение true.

    private static boolean winState(String[][] map){
        for (int i = 0; i < 3; i++) {
            StringBuilder win = new StringBuilder();
            String cell = map[i][0];
            for (int j = 0; j < 3; j++) {
                if (map[i][j].equals(cell) && !map[i][j].equals("[ ]")) win.append(map[i][j]);
            }
            if (win.toString().equals("[x][x][x]") || win.toString().equals("[o][o][o]")) return true;
        }

        for (int i = 0; i < 3; i++) {
            StringBuilder win = new StringBuilder();
            String cell = map[0][i];
            for (int j = 0; j < 3; j++) {
                if (map[j][i].equals(cell) && !map[j][i].equals("[ ]")) win.append(map[j][i]);
            }
            if (win.toString().equals("[x][x][x]") || win.toString().equals("[o][o][o]")) return true;
        }

        StringBuilder win = new StringBuilder();
        String cell = map[0][0];
        for (int j = 0; j < 3; j++) {
            if (map[j][j].equals(cell) && !map[j][j].equals("[ ]")) win.append(map[j][j]);
        }
        if (win.toString().equals("[x][x][x]") || win.toString().equals("[o][o][o]")) return true;

        StringBuilder win1 = new StringBuilder();
        cell = map[0][2];
        for (int j = 0; j < 3; j++) {
            int i = 2 - j;
            if (map[j][i].equals(cell) && !map[j][i].equals("[ ]")) win1.append(map[j][i]);
        }
        if (win1.toString().equals("[x][x][x]") || win1.toString().equals("[o][o][o]")) return true;

        return false;
    }

    private static boolean overflowState(String[][] map){
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j].equals("[ ]")) counter++;
            }
        }
        if (counter == 0) return true;
        else return false;
    }

    private static boolean playAgain(){
        boolean p;
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try{
                int a = Integer.parseInt(r.readLine());
                if (a == 0 || a == 1){
                    if (a == 0) {
                        return false;
                    }
                    else {
                        return true;
                    }
                }
                else System.out.println("Неверный ввод. Введите 0 или 1.");
            }
            catch (Exception e){
                System.out.println("Неверный ввод. Введите 0 или 1.");
            }
        }
    }

    private static boolean checkCell(String[][] map, int k){
        switch (k){
            case 7:
                if (map[0][0].equals("[ ]")) return true;
                else return false;
            case 8:
                if (map[0][1].equals("[ ]")) return true;
                else return false;
            case 9:
                if (map[0][2].equals("[ ]")) return true;
                else return false;
            case 4:
                if (map[1][0].equals("[ ]")) return true;
                else return false;
            case 5:
                if (map[1][1].equals("[ ]")) return true;
                else return false;
            case 6:
                if (map[1][2].equals("[ ]")) return true;
                else return false;
            case 1:
                if (map[2][0].equals("[ ]")) return true;
                else return false;
            case 2:
                if (map[2][1].equals("[ ]")) return true;
                else return false;
            case 3:
                if (map[2][2].equals("[ ]")) return true;
                else return false;
            default:
                return false;
        }
    }

    //Метод заполняет поле символом, которые подается на входе в метод.

    private static void turnProcessing(String[][] map, int n, char ch){
        String displayChar = "[" + ch + "]";
        switch (n){
            case 7:
                map[0][0] = displayChar;
                break;
            case 8:
                map[0][1] = displayChar;
                break;
            case 9:
                map[0][2] = displayChar;
                break;
            case 4:
                map[1][0] = displayChar;
                break;
            case 5:
                map[1][1] = displayChar;
                break;
            case 6:
                map[1][2] = displayChar;
                break;
            case 1:
                map[2][0] = displayChar;
                break;
            case 2:
                map[2][1] = displayChar;
                break;
            case 3:
                map[2][2] = displayChar;
                break;
            default:
                break;
        }
    }
}
