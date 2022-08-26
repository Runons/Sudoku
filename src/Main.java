
import java.util.Scanner;
/*сначала пытался сделать судоку через HashMap() и рандомное заполнение, но через 2 дня попыток пришел к выводу
 * что так не получится и нужно сопоставлять числа из 3 по групам и идвигать их в ячейке и в нутри самой группы */

public class Main {
    public static void main(String[] args) {
        Scanner enter = new Scanner(System.in);
        Matrix prob1 = new Matrix();
        int dif=0;
        do {

            System.out.println("Enter the difficulty level:");
            System.out.println("1 for easy level");
            System.out.println("2 for middle level");
            System.out.println("3 for hard level");
            System.out.println("4 for green love");
            System.out.print("5 for out\n:");
            if(enter.hasNextInt())
                dif = enter.nextInt();
            else {
                System.out.println("\nError: enter number\n");
                enter.nextLine();
                continue;
            }

            switch (dif) {
                case 1, 2, 3, 4 -> prob1.runSudoku(dif);
                case 5 ->  System.out.println("End");
                default -> System.out.println("\nError: invalid command\n");
            }
        } while (dif != 5);


    }
}
