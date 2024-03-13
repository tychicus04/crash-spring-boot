import java.util.Scanner;

public class Devil {
    public static void main(String[] args) {
//        Enter amount of games
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        for (int t = 0; t < T; t++) {
            int n = scanner.nextInt(); // Số đồng tiền vàng ở ván đấu thứ t
            boolean result = winOrLose(n);
            System.out.println(result);
        }
    }

    public static boolean winOrLose(int n) {
        // Nếu số đồng tiền vàng là bội số của 4, Mario sẽ thua
        // Vì dù Mario lấy bất kỳ số lượng nào từ 1 đến 3, quỷ cũng có thể lấy đủ số tiền để làm cho số tiền còn lại lại là bội số của 4
        // Trong trường hợp còn lại, Mario sẽ luôn thắng
        return n % 4 != 0;
    }
}
