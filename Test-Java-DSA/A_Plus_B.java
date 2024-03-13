import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A_Plus_B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int num1 = Integer.parseInt(input[0]);
            int num2 = Integer.parseInt(input[1]);
            if(num1 >= 1000000000 || num2 >= 1000000000) break;

            int sum = num1 + num2;
            System.out.println(sum);
        }
    }
}
