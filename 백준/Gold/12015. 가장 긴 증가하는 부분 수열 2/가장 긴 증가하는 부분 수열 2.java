import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = arr[0];
        int LIS_length = 1;

        for (int i = 1; i < N; i++) {

            if(dp[LIS_length-1]<arr[i]) {
                dp[LIS_length] = arr[i];
                LIS_length++;
            }

            else {
                int start = 0;
                int end = LIS_length;

                while (start < end) {
                    int mid = (start + end) / 2;

                    if (dp[mid] >= arr[i]) {
                        end = mid;
                    }
                    else {
                        start = mid + 1;
                    }
                }
                dp[start] = arr[i];
            }
        }
        System.out.println(LIS_length);
    }
}