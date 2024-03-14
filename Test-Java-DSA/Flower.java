import java.util.Scanner;

public class Flower {
    static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long N = scanner.nextLong();
        System.out.println(countWays(N));
        scanner.close();
    }

    static long countWays(long N) {
        long[] dpPrev = new long[5];
        long[] dpCurr = new long[5];

        // initialize
        for (int i = 0; i < 5; i++) {
            dpPrev[i] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 5; j++) {
                dpCurr[j] = 0;
                for (int k = 0; k < 5; k++) {
                    if (j != k && isValidTransition(j, k)) {
                        dpCurr[j] += dpPrev[k];
                        dpCurr[j] %= MOD; // Apply modulo operation to avoid overflow
                    }
                }
            }
            // Swap arrays instead of copying values
            long[] temp = dpPrev;
            dpPrev = dpCurr;
            dpCurr = temp;
        }

        // count sum of ways to plan
        long total = 0;
        for (int i = 0; i < 5; i++) {
            total += dpPrev[i];
            total %= MOD; // Apply modulo operation to avoid overflow
        }

        return total;
    }

    static boolean isValidTransition(int from, int to) {
        return (from == 0 && to == 1) || (from == 1 && (to == 0 || to == 2)) || (from == 2 && to != 2) ||
                (from == 3 && (to == 2 || to == 4)) || (from == 4 && to == 0);
    }
}
