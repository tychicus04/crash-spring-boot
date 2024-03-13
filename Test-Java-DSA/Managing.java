import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Managing {
    public static long maxProfit(int[] profits, int K) {
        int n = profits.length;
        long[][] dp = new long[n+1][K+1];
        for (int i = n-1; i >= 0; i--) {
            for (int j = 1; j <= K; j++) {
                dp[i][j] = Math.max(dp[i+1][j], profits[i] + dp[i+1][j-1]);
            }
        }
        return dp[0][K];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int k = Integer.parseInt(firstLine[1]);
        int[] profits = new int[n];
        String[] profitStrings = reader.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            profits[i] = Integer.parseInt(profitStrings[i]);
        }

        long maxProfit = maxProfit(profits, k);
        System.out.println(maxProfit);
    }
}