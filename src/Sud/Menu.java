package Sud;

import java.util.Scanner;

/**
 * @author Mihail Epifantsev
 * @data 23.09.2022
 */
public class Menu {
    public static void start() {
        Scanner enter = new Scanner(System.in);
        Sudoku sudoku;
        StringBuilder sb;
        String[][] sudArray ;
        String format;
        Integer size = 0;
        int dif = 0;

        System.out.println("\n\nHello, welcome to Sudoku!\n\n");

        out:
        do {//создаем новый объект судоку
            sudoku = new Sudoku();
            int sub = 1;
            // задаем размер судоку
            while (size == 0 || size == 1) {
                System.out.print("Enter the size sudoku (the size cannot be 0 & 1): ");
                if (enter.hasNextInt()) {
                    size = enter.nextInt();
                    if (size != 0 && size != 1) {
                        break;
                    }
                } else {
                    System.out.println("\nError: enter number\n");
                    enter.nextLine();
                    continue;
                }
                System.out.println("\n Error: size equal to 0 or 1\n");
            }
            sudoku.setSize(size);
            //выбираем уровень сложности
            while (dif == 0) {

                    System.out.println("\nEnter the difficulty level:");

                    System.out.println("""
                    1 for easy level;
                    2 for middle level;
                    3 for hard level;
                    4 for green love;"
                    5 for solved sudoku;
                    6 for out;
                    :""");


                if (enter.hasNextInt()) {
                    dif = enter.nextInt();
                    System.out.println(" ");
                } else {
                    System.out.println("\nError: enter number\n");
                    enter.nextLine();

                }
                switch (dif) {
                    case 1, 2, 3, 4, 5 -> {
                    }
                    case 6 -> {
                        System.out.println("End");
                        break out;
                    }
                    default -> {
                        dif = 0;
                        System.out.println("\nError: invalid command\n");
                    }
                }
            }
            sudArray = sudoku.get(dif);
            //выводим судоку с заданным уровнем сложности
            format = "%1$" + size.toString().length() + "s"; // выравниваем числа
            sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    sb.append(" ").append(String.format(format, sudArray[i][j])).append(" ");
                    //отсекаем ячейку по вертикали
                    if ((j + 1) % sudoku.getVerticalCell() == 0 && j != size - 1) {
                        sb.append(" | ");
                        sub++;
                    }
                }
                sb.append("\n");
                //отсекаем ячейки по горизонтали
                if ((i + 1) % sudoku.getHorizontalCell() == 0 && i != size - 1) {
                    for (int a = 0; a < sb.toString().split("\n")[0].length(); a++) {
                        sb.append("-");
                        sub = 0;
                    }
                    sb.append("\n");
                }
            }
            System.out.println(sb);

            while (sub!=0) {
                enter.nextLine();
                System.out.print("""

                        Do you want to finish?
                        Enter 0 to continue
                        Enter 1 to finish
                        :""");

                if (enter.hasNextInt()) {
                    sub = enter.nextInt();
                } else {
                    System.out.println("\nError: enter number\n");
                    continue;
                }
                switch (sub) {
                    case 0 -> {
                    }
                    case 1 -> {
                        System.out.println("End");
                        break out;
                    }
                    default -> {
                        sub = 1;
                        System.out.println("\nError: invalid command\n");
                    }
                }
            }

            sub =1;
            settings:
            while (sub!=0) {
                enter.nextLine();
                System.out.print("""
                Use old settings?
                "Yes 0;"
                "No 1;
                :""");

                if (enter.hasNextInt()) {
                    sub = enter.nextInt();
                }
                else {
                    System.out.println("\nError: enter number\n");
                    continue;
                }

                switch (sub) {
                    case 0 -> {
                    }
                    case 1 -> {
                        dif = 0;
                        size = 0;
                        break settings;
                    }
                    default -> {
                        sub = 1;
                        System.out.println("\nError: invalid command\n");
                    }
                }
            }
            System.out.println("\n");

        } while (true);
    }
}
