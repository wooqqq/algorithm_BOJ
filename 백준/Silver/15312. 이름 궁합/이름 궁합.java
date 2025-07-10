import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String first, second;
    static final int[] count = {3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        first = br.readLine();
        second = br.readLine();

        int length = first.length() * 2;
        int[] dp = new int[length];

        for (int i = 0, j = 0; i < length; i++) {
            dp[i] = i % 2 == 0 ? count[first.charAt(j) - 'A'] : count[second.charAt(j++) - 'A'];
        }

        while (length-- > 2) {
            for (int i = 0; i < length; i++) {
                dp[i] = (dp[i] + dp[i + 1]) % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        System.out.println(sb.append(dp[0]).append(dp[1]));
    }
}