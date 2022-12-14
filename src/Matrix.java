import java.util.ArrayList;
import java.util.Random;

class Matrix {

    private int[][] matrixsudoku = new int[9][9];
    private Random rand = new Random();
    private ArrayList<Integer> oneGroup = new ArrayList<>();
    private ArrayList<Integer> twoGroup = new ArrayList<>();
    private ArrayList<Integer> threeGroup = new ArrayList<>();


    public int[][] getMatrix() {                                                 // просто возвращает заполненую матрицу
        completion();
        return matrixsudoku;
    }

    public void runSudoku(int dif) {                            // выводит в концоль судоку с заданной сложностью dif
        if (dif==4)                                                    // Waaagh!    (╯✧▽✧)╯
            while (true) {
                System.out.println("Waaagh!");
            }
        completion();
        difLevel(dif);
        printSudoku();
    }

    private void completion() {                                                                     // заполняет матрицу
        createGroup();                                                                  //создаем новые рандомные группы
        for (int i = 0; i < matrixsudoku.length; i++) {
            for (int j = 0; j < matrixsudoku[0].length; j++) {

                /*
                | 1 | 2 | 3 |             последовательно вызывет метод addInMatrix() в зависимости от номера строки
                -------------             и столбца, чередуя группы для записи в матрицу
                | 4 | 5 | 6 |
                -------------
                | 7 | 8 | 9 |

                 */
                if (i % 3 == 0) {
                    if (j < 3) addInMatrix(i, j, oneGroup);
                    else if (j < 6) addInMatrix(i, j, twoGroup);
                    else addInMatrix(i, j, threeGroup);

                } else if (i % 3 == 1) {
                    if (j < 3) addInMatrix(i, j, threeGroup);
                    else if (j < 6) addInMatrix(i, j, oneGroup);
                    else addInMatrix(i, j, twoGroup);
                } else {
                    if (j < 3) addInMatrix(i, j, twoGroup);
                    else if (j < 6) addInMatrix(i, j, threeGroup);
                    else addInMatrix(i, j, oneGroup);
                }

            }
            if (i % 3 == 0) this.shiftInGroup();                // ратируем расположение чисел в группах каждую 3 строку
        }
    }

    private void createGroup() {                               //создает 3 группы чисел с рандромной последовательностью
        Integer sub;
        for (int i = 1; i < 10; i++) {
            oneGroup.add(i);
        }
        for (int i = 1; i < 4; i++) {                        //добавляем в другие группы числа и удаляем его из oneGroup
            sub =oneGroup.get(rand.nextInt(oneGroup.size()));
            twoGroup.add(sub);
            oneGroup.remove(sub);
            sub = oneGroup.get(rand.nextInt(oneGroup.size()));
            threeGroup.add(sub);
            oneGroup.remove(sub);
        }


    }

    private void addInMatrix(int i, int j, ArrayList<Integer> numGroup) { // записывает числа из группы в матрицу
        Integer number = numGroup.get(j % 3); // перебор стобцов
        matrixsudoku[i][j] = number;
    }

    private void printSudoku() {                                // выводит матрицу, если задана сложность выводит судоку
        for (int i = 0; i < matrixsudoku.length; i++) {         // если нет выводит заполненную матрицу
            if (i != 0) System.out.print("|");
            if ((i) % 3 == 0) System.out.print("\n--------------------------------\n");
            else System.out.print("\n");
            for (int j = 0; j < matrixsudoku[0].length; j++) {
                if ((j) % 3 == 0) System.out.print("|");
                if (matrixsudoku[i][j] == -1) {
                    System.out.print(" " + " " + " ");
                } else {
                    System.out.print(" " + matrixsudoku[i][j] + " ");
                }
            }
        }
        System.out.print("|\n-------------------------------\n");
    }

    private void shiftInGroup() {         // ратирует числа в группах
        Integer sub;
        for (int i = 0; i < oneGroup.size() - 1; i++) {
            sub = oneGroup.get(i);
            oneGroup.remove(sub);
            oneGroup.add(i + 1, sub);
        }
        for (int i = 0; i < twoGroup.size() - 1; i++) {
            sub = twoGroup.get(i);
            twoGroup.remove(sub);
            twoGroup.add(i + 1, sub);
        }
        for (int i = 0; i < threeGroup.size() - 1; i++) {
            sub = threeGroup.get(i);
            threeGroup.remove(sub);
            threeGroup.add(i + 1, sub);
        }
    }

    private void difLevel(int dif) {                               // задает сложность вставляя -1 в рандомные ячейки
        int space = 0;
        int sub1, sub2;
        switch (dif) {
            case 1 -> space = 35;
            case 2 -> space = 48;
            case 3 -> space = 61;
        }

        while (space != 0) {
            sub1 = rand.nextInt(9);
            sub2 = rand.nextInt(9);
            if (matrixsudoku[sub1][sub2] != -1) {
                matrixsudoku[sub1][sub2] = -1;
                space--;
            }
        }
    }
}
