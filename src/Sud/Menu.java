package Sud;

import java.util.Scanner;

/**
 * @author Mihail Epifantsev
 * @data 23.09.2022
 */
public class Menu {
    public static void start() {
        Scanner enter = new Scanner(System.in);
        Sudoku sudoku = new Sudoku();
        StringBuilder sb = new StringBuilder();
        String[][] sudArray = new String[0][0];

        Integer size = 0;
        int dif = 0;
        int sub=0;
        do {

            while (size == 0 || size == 1) {
                System.out.print("Enter the size sudoku (the size cannot be 0 & 1): ");
                if (enter.hasNextInt()) {
                    size = enter.nextInt();
                    if (size != 0 && size != 1) {
                        sudoku.setSize(size);
                        break;
                    }
                } else {
                    System.out.println("\nError: enter number\n");
                    enter.nextLine();
                    continue;
                }
                System.out.println("\n Error: size equal to 0 or 1\n");
            }
            while (dif == 0) {
                if (dif == 0) {
                    System.out.println("Enter the difficulty level:");

                    System.out.println("1 for easy level;");
                    System.out.println("2 for middle level;");
                    System.out.println("3 for hard level;");
                    System.out.println("4 for green love;");
                    System.out.println("5 for solved sudoku;");
                    System.out.print("6 for out\n\ndifficulty: ");
                }

                if (enter.hasNextInt()) {
                    dif = enter.nextInt();
                } else {
                    System.out.println("\nError: enter number\n");
                    enter.nextLine();

                }
            }

            switch (dif) {
                case 1, 2, 3, 4, 5 -> sudArray = sudoku.get(dif);
                case 6 -> {
                    System.out.println("End");
                    continue;
                }
                default -> System.out.println("\nError: invalid command\n");
            }
            String str = "%1$" + size.toString().length() + "s";
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    sb.append(" ").append(String.format(str, sudArray[i][j])).append(" ");
                    if ((j + 1) % sudoku.verticalCell == 0 && j != size-1) {
                        sb.append(" | ");
                        sub++;
                    }
                }
                sb.append("\n");
                if ((i + 1) % sudoku.horizontalCell == 0 && i != size-1) {
                    for (int a = 0; a < sb.toString().split("\n")[0].length(); a++) {
                        sb.append("-");
                        sub=0;
                    }
                    sb.append("\n");
                }

            }
            System.out.println(sb);
            break;
        } while (true);
    }
}
