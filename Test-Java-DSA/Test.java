import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
//        Enter amount of test
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        for (int i = 0; i < T; i++) {

            int n = scanner.nextInt(); // time
            int k = scanner.nextInt(); // place

            char result = findCell(n, k);

            System.out.println(result);
        }
    }

//    find cell by time and place
    public static char findCell(int n, int k) {
//        the first cell is "X"
        if (n == 1) return 'X';

//        count amount of cells in the n seconds
        int cellsCount = (int) Math.pow(2, n - 1);

        if (k <= cellsCount / 2) {
            return findCell(n - 1, k);
        } else {

            char cell = findCell(n - 1, k - cellsCount / 2);
            if (cell == 'X') return 'Y';
            else return 'X';
        }
    }
}
