import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    static int N, X;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        X = Integer.parseInt(br.readLine());

        long sum = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (calc(arr[i], X) == 1) {
                sum += arr[i];
                cnt += 1;
            }
        }

        System.out.println(sum / (double) cnt);
    }

    private static int calc(int a, int b) {
        if (b == 0) return a;

        return calc(b, a % b);
    }
}