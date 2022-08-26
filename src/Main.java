import java.util.Scanner;
/*сначала пытался сделать судоку через HashMap() и рандомное заполнение, но через 2 дня попыток пришел к выводу
* что так не получится и нужно сопоставлять числа из 3 по групам и идвигать их в ячейке и в нутри самой группы */

public class Main {
    public static void main(String[] args) {
        Scanner enter = new Scanner(System.in);
        Matrix prob1 = new Matrix();
        String dif,goStop;
        do {
            do {
                System.out.print("enter the difficulty level (easy; middle; hard; green love): ");
                dif = enter.nextLine();
            } while (!dif.equals("easy") && !dif.equals("middle") && !dif.equals("hard"));
            prob1.runSudoku(dif);
            System.out.print( "one more time? (go|stop): ");
            goStop= enter.nextLine();
            System.out.println();
        }while (!goStop.equalsIgnoreCase("stop"));

    }
}
