import java.util.*;

public class Connection {
    static int[] parent;
    static int[] rank;

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        parent = new int[N];
        rank = new int[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            union(u, v);
        }

        int components = 0;
        for (int i = 0; i < N; i++) {
            if (parent[i] == i) {
                components++;
            }
        }

        // Số đường dây cáp cần thay đổi là số thành phần liên thông trừ 1
        int cablesNeeded = components - 1;

        // Nếu số đường dây cáp cần thay đổi nhỏ hơn hoặc bằng số dây cáp hiện có, in ra kết quả
        // Ngược lại, in ra -1
        if (cablesNeeded <= M - N + components) {
            System.out.println(cablesNeeded);
        } else {
            System.out.println(-1);
        }
    }

}
