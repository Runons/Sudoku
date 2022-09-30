package Sud;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Mihail Epifantsev
 * {@code @data} 23.09.2022
 */
public class Sudoku {
    private int n;
    private String[][] matrixSudoku;
    private List<List<Integer>> groups = new ArrayList<>();
    private int verticalCell;
    private int horizontalCell;
    Random rand = new Random();
    public Sudoku() {

    }

    public Sudoku(int size) {
        if (size == 1 || size == 0) {
            size = 2;
        }
        n = size;
        matrixSudoku = new String[size][size];

    }

    // заполняет матрицу
    public void generate() {
        int mix;
        String subString;
        Integer sub;
        List<Integer> subList;
        //определяем  количесто ячеек j необходима для болие красивой sudoku для числе наподобие 12
        for (int i = 2, j = 0, a = n - 1; i < n / 2 && j < 2 && a > n / 2; i++, a--) {
            if (n % i == 0) {
                verticalCell = n / i;
                horizontalCell = i;
                j++;
            } else if (n % a == 0) {
                verticalCell = n / a;
                horizontalCell = a;
                j++;
            }
        }
        //для простых чисел
        if (verticalCell == 0 | horizontalCell == 0) {
            verticalCell = 1;
            horizontalCell = n;
        }

        //создаем первую группу
        groups.add(new ArrayList<>());

        for (int i = 1; i <= n; i++) {
            groups.get(0).add(i);
        }

        //добавляем в другие группы числа и удаляем его из zero group
        for (int h = 1; h < horizontalCell; h++) {
            groups.add(new ArrayList<>());

            for (int j = 1; j <= verticalCell; j++) {
                sub = groups.get(0).get(rand.nextInt(groups.get(0).size()));
                groups.get(0).remove(sub);
                groups.get(h).add(sub);
            }
        }
        //заполняем массив String
        for (int i = 0; i < matrixSudoku.length; i++) {
            sub = 0;
            for (int h = 0; h < horizontalCell; h++) {
                for (int v = 0; v < verticalCell; v++) {
                    matrixSudoku[i][sub] = groups.get(h).get(v).toString();
                    sub++;
                }
            }
            if (i == n - 1) {
                break;
            }
            //ратируем группы
            for (int j = 0; j < groups.size() - 1; j++) {
                subList = groups.get(j);
                groups.remove(j);
                groups.add(j + 1, subList);
            }
            // ратируем расположение чисел в группах каждую новую ячейку строку
            if ((i + 1) % horizontalCell == 0) {
                for (int h = 0; h < horizontalCell; h++) {
                    for (int v = 0; v < verticalCell - 1; v++) {
                        sub = groups.get(h).get(v);
                        groups.get(h).remove(v);
                        groups.get(h).add(v + 1, sub);
                    }
                }
            }
        }
        //перемешиваем колонки
        if (verticalCell != 1) {
            for (int i = 0; i < n; i++) {
                // ограничеваем в пределах ячейки
                mix = rand.nextInt(n);
                if (mix % verticalCell == 0) {
                    sub = mix + 1;
                } else if (mix % verticalCell == verticalCell - 1) {
                    sub = mix - 1;
                } else {
                    if (rand.nextBoolean()) {
                        sub = mix - 1;
                    } else {
                        sub = mix + 1;
                    }
                }
                //меняем местами колонки
                for (int j = 0; j < n / 2; j++) {
                    subString = matrixSudoku[j][mix];
                    matrixSudoku[j][mix] = matrixSudoku[j][sub];
                    matrixSudoku[j][sub] = subString;
                }
            }
        }
        //перемешиваем строки
        for (int i = 0; i < n / 2; i++) {
            mix = rand.nextInt(n);
            // ограничеваем в пределах ячейки
            if (mix % horizontalCell == 0) {
                sub = mix + 1;
            } else if (mix % horizontalCell == horizontalCell - 1) {
                sub = mix - 1;
            } else {
                if (rand.nextBoolean()) {
                    sub = mix - 1;
                } else {
                    sub = mix + 1;
                }
            }
            //меняем местами строки
            for (int j = 0; j < n; j++) {
                subString = matrixSudoku[mix][j];
                matrixSudoku[mix][j] = matrixSudoku[sub][j];
                matrixSudoku[sub][j] = subString;
            }
        }


    }

    public int getVerticalCell() {
        return verticalCell;
    }

    public int getHorizontalCell() {
        return horizontalCell;
    }

    public String[][] get(int dif) {
        int space;
        int sub1;
        int sub2;


        generate();

        if (dif == 4) {                                                   // Waaagh!    (╯✧▽✧)╯
            while (true) {
                System.out.println("Waaagh!");
            }
        }

        switch (dif) {
            case 1 -> space = (int) (0.44 * n * n);
            case 2 -> space = (int) (0.6 * n * n);
            case 3 -> space = (int) (0.76 * n * n);
            default -> space = 0;
        }

        while (space != 0) {
            sub1 = rand.nextInt(n);
            sub2 = rand.nextInt(n);

            if (!matrixSudoku[sub1][sub2].equals(" ")) {
                matrixSudoku[sub1][sub2] = " ";
                space--;
            }
        }

        return matrixSudoku;
    }

    public void setSize(int size) {
        n = size;
        matrixSudoku = new String[size][size];
    }

}
