import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, R, Q;
    static int[] dp;
    static int[] visited;
    static List<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        dp = new int[N + 1];
        tree = new ArrayList[N + 1];
        visited = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            tree[x].add(y);
            tree[y].add(x);
        }

        visited[R] = 1;
        calc(R);

        for (int i = 0; i < Q; i++) {
            int idx = Integer.parseInt(br.readLine());
            System.out.println(dp[idx]);
        }
    }

    public static int calc(int idx) {
        int res = 1;
        if (dp[idx] == 0) {
            List<Integer> list = tree[idx];

            for (Integer val : list) {
                if (visited[val] == 0) {
                    visited[val] = 1;
                    res += calc(val);
                }
            }
            dp[idx] = res;
        }
        return dp[idx];
    }
}