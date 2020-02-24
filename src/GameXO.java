import java.util.Random;
import java.util.Scanner;
public class GameXO {
    public static char[][] map;
    public static final int Size = 3;                      //-управляющие
    public static final int DotsToWin = 3;                 // переменные
    private static final int Widening = Size - DotsToWin; // -переменная изменения поля

    public static final char DotEmpty = '•';
    public static final char DotX = 'X';
    public static final char DotO = 'O';
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();
    public static void main(String[] args) {
        initMap();
        printMap();
        while (true){
            humanTurn();
            printMap();
            if(checkWin(DotX)){
                System.out.println("Победил человек");
                break;
            }
            if(isMapFull()){
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if(checkWin(DotO)){
                System.out.println("Победил Искусственный интеллект");
                break;
            }
            if(isMapFull()){
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }
    public static void initMap(){
        map = new char[Size][Size];
        for (int i = 0; i < Size ; i++) {
            for (int j = 0; j < Size; j++) {
                map[i][j] = DotEmpty;
            }
        }
    }
    public static void printMap(){
        for (int i = 0; i <= Size; i++) {
            System.out.print(i + " "); // заголовок
        }
        System.out.println();
        for (int i = 0; i < Size; i++) {
            System.out.print((i + 1) + " "); // первый столбец только
            for (int j = 0; j < Size; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();   //  после окончания строки
        }
        System.out.println();       // пустая строка
    }
    public static void humanTurn(){
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map [x][y] = DotX;
    }
    public static boolean isCellValid(int x, int y){
        if (x < 0 || x >= Size || y < 0 || y >= Size) return false;
        if (map[x][y] == DotEmpty) return true;
        return false;
    }
    public static void aiTurn(){
        int x, y;
        do {
            x = rand.nextInt(Size);
            y = rand.nextInt(Size);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил на точку " + (x + 1) + " " + (y + 1));
        map[x][y] = DotO;
    }
    public static boolean checkWin(char symb) {                      // горизонталь
        for (int i = 0; i < Size; i++) {
            int win = 0;
            for (int j = 0; j < Size; j++) {
                if(map[i][j] == symb) {
                    win++;
                }
                if(win == DotsToWin) {
                    return true;
                }
            }
        }
        for (int i = 0; i < Size; i++) {                              // вертикаль
            int win = 0;
            for (int j = 0; j < Size; j++) {
                if(map[j][i] == symb) {
                    win++;
                }
                if(win == DotsToWin) {
                    return true;
                }
            }
        }
        while (true){                                                  // прямая диагональ
            int win = 0;
            for (int i = 0; i < Size; i++) {
                int j = i;
                if(map[i][j] == symb){
                    win++;
                }
                if(win == DotsToWin){
                    return true;
                }
            }break;
        }
        while (true) {                                                 // обратная диагональ
            int j = 0, win = 0;
            for (int i = Size - 1; i >= 0; i--) {
                if (map[i][j] == symb) {
                    win++;
                    j++;
                }
                if (win == DotsToWin) {
                    return true;
                }
            } break;
        }
        return false;
    }

// -------------------------ВАРИАНТ ДЛЯ ИГРОВОГО ПОЛЯ 5х5------------------------------------
    // вариант не закончен, не работает проверка обратной "диагонали" (5 2), (4 3), (3 4), (2 5), игра не останавливается
    // то есть не реализован "перенос" области проверки в дальнюю область
// -------------------------НАЧАЛО ТЕЛА ВАРИАНТА ДЛЯ ИГРОВОГО ПОЛЯ 5х5------------------------------------
/*
    public static boolean checkWin(char symb) {                      // горизонталь
        for (int k = 0; k <= Widening ; k++) {
            for (int l = 0; l <= Widening ; l++) {
                for (int i = 0; i < Size; i++) {
                    int win = 0;
                    for (int j = 0; j < Size; j++) {
                        if(map[i][j] == symb) {
                            win++;
                        }
                        if(win == DotsToWin) {
                            return true;
                        }
                    }
                }
                for (int i = 0; i < Size; i++) {                              // вертикаль
                    int win = 0;
                    for (int j = 0; j < Size; j++) {
                        if(map[j][i] == symb) {
                            win++;
                        }
                        if(win == DotsToWin) {
                            return true;
                        }
                    }
                }
                while (true){                                                  // прямая диагональ
                    int win = 0;
                    for (int i = 0; i < Size; i++) {
                        int j = i;
                        if(map[i][j] == symb){
                            win++;
                        }
                        if(win == DotsToWin){
                            return true;
                        }
                    }break;
                }
                while (true) {                                                 // обратная диагональ
                    int j = 0, win = 0;
                    for (int i = Size - 1; i >= 0; i--) {
                        if (map[i][j] == symb) {
                            win++;
                            j++;
                        }
                        if (win == DotsToWin) {
                            return true;
                        }
                    } break;
                }
            }
        }return false;
    }
*/

//----------------------------------КОНЕЦ ТЕЛА ВАРИАНТА ДЛЯ ИГРОВОГО ПОЛЯ 5х5------------------------------------

    public static boolean isMapFull(){
        for (int i = 0; i < Size; i++) {
            for (int j = 0; j < Size; j++) {
                if(map[i][j] == DotEmpty) return false;
            }
        }
        return true;
    }
}
