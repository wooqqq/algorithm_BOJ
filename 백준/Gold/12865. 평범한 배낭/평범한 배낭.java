import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] weight, value;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        weight = new int[N];
        value = new int[N];

        dp = new int[N][K + 1];

        for (int r = 0; r < N; r++) {
            Arrays.fill(dp[r], -1);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(calc(N - 1, K));
    }

    static int calc(int i, int k) {
        if (i < 0) return 0;

        if (dp[i][k] == -1) {
            if (weight[i] > k) dp[i][k] = calc(i - 1, k);
            else dp[i][k] = Math.max(calc(i - 1, k), calc(i - 1, k - weight[i]) + value[i]);
        }

        return dp[i][k];
    }
}
