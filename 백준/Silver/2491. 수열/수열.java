import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    static int N, max;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[3][N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        dp[0][1] = Integer.parseInt(st.nextToken());
        dp[1][1] = 1;
        dp[2][1] = 1;
        max = 1;

        for (int i = 2; i <= N; i++) {
            dp[0][i] = Integer.parseInt(st.nextToken());
            if (dp[0][i] >= dp[0][i - 1]) dp[1][i] = dp[1][i - 1] + 1;
            else dp[1][i] = 1;

            if (dp[0][i] <= dp[0][i - 1]) dp[2][i] = dp[2][i - 1] + 1;
            else dp[2][i] = 1;

            max = Math.max(max, Math.max(dp[1][i], dp[2][i]));
        }

        System.out.println(max);
    }
}