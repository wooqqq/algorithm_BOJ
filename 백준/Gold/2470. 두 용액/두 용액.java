import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int start = 0;
        int end = arr.length-1;
        int minLength = Integer.MAX_VALUE;
        int[] ans = new int[2];

        while (start < end) {
            int sum = arr[start] + arr[end];
            int tmpLength = Math.abs(sum);
            if (tmpLength < minLength) {
                minLength = tmpLength;
                ans[0] = arr[start];
                ans[1] = arr[end];
            }

            if (sum > 0) {
                end--;
            }
            else {
                start++;
            }
        }
        System.out.println(ans[0] + " " + ans[1]);
    }
}