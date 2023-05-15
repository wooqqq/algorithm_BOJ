import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N+2][2];
        int[] dp = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int max = 0;

        for (int i = 1; i < N + 2; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }

            int nextDay = i + arr[i][0];
            if (nextDay < N + 2) {
                dp[nextDay] = Math.max(dp[nextDay], max + arr[i][1]);
            }
        }
        System.out.println(dp[N + 1]);
    }
}