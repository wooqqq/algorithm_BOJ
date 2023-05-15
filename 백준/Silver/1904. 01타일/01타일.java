import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        dp = new long[1000001];

        dp[1] = 1;
        dp[2] = 2;

        for(int i=3; i<=num; i++){
            dp[i] = (dp[i-1] + dp[i-2]) %15746;
        }

        System.out.println(dp[num]);
        br.close();
    }
}