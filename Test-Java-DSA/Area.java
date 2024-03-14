import java.util.Scanner;

public class Area {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int[][] land = new int[N][M];

        // Đọc dữ liệu vào mảng
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                land[i][j] = scanner.nextInt();
            }
        }

        // Tính diện tích lớn nhất của phần đất tốt
        int maxArea = getMaxGoodLandArea(land, N, M);

        System.out.println(maxArea);
    }

    // Hàm tính diện tích lớn nhất của phần đất tốt
    private static int getMaxGoodLandArea(int[][] land, int N, int M) {
        int maxArea = 0;
        int[] heights = new int[M];

        for (int i = 0; i < N; i++) {
            // Cập nhật chiều cao của hình chữ nhật tính từ hàng đang xét
            for (int j = 0; j < M; j++) {
                if (land[i][j] == 1) {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            // Tính diện tích lớn nhất của hình chữ nhật tính từ hàng đang xét
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    // Hàm tính diện tích lớn nhất của hình chữ nhật trong mảng heights
    private static int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        int n = heights.length;
        int[] stack = new int[n + 1];
        int top = -1;

        for (int i = 0; i <= n; ++i) {
            int h = (i == n) ? 0 : heights[i];
            while (top != -1 && h < heights[stack[top]]) {
                int height = heights[stack[top--]];
                int width = (top == -1) ? i : i - stack[top] - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack[++top] = i;
        }
        return maxArea;
    }
}
