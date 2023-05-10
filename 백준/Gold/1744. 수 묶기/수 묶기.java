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
        int answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int start = 0;
        int end = N - 1;
        answer = 0;

        // 0 또는 음수일 경우
        for (; start < end; start += 2) {
            if (arr[start] < 1 && arr[start + 1] < 1) {
                answer += arr[start] * arr[start + 1];
            } else break;
        }

        // 양수일 경우
        for (; end > 0; end -= 2) {
            if (arr[end] > 1 && arr[end - 1] > 1) {
                answer += arr[end] * arr[end - 1];
            } else break;
        }

        // 나머지 더해주기
        for (; end >= start; end--) {
            answer += arr[end];
        }
        System.out.println(answer);
    }
}