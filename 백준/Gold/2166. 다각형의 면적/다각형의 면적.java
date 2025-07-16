import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i][0] = x;
            arr[i][1] = y;
        }

        double sum = 0;

        for (int i = 0; i < N; i++) {
            sum += (double) arr[i][0] * arr[(i + 1) % N][1];
            sum -= (double) arr[i][1] * arr[(i + 1) % N][0];
        }

        System.out.printf("%.1f", Math.abs(sum / 2));
    }
}