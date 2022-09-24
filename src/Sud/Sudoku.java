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
    int verticalCell = 0;
    int horizontalCell = 0;

    public Sudoku() {

    }

    public Sudoku(int size) {
        if (size == 1 || size == 0) {
            size = 2;
        }
        n = size;
        matrixSudoku = new String[size][size];

    }

    public void setSize(int size) {
        n = size;
        matrixSudoku = new String[size][size];
    }

    public String[][] get(int dif) {
        int space;
        int sub1;
        int sub2;
        Random rand = new Random();

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

    // заполняет матрицу
    public void generate()  {
        Random rand = new Random();

        Integer sub;
        List<Integer> subList;
        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                verticalCell = n / i;
                horizontalCell = i;
                break;
            }
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


        for (int i = 0; i < matrixSudoku.length; i++) {
            sub = 0;
            for (int h = 0; h < horizontalCell; h++) {
                for (int v = 0; v < verticalCell; v++) {

                    matrixSudoku[i][sub] = groups.get(h).get(v).toString();

                    sub++;
                }
            }
            if (i == n-1) {
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
    }

}
