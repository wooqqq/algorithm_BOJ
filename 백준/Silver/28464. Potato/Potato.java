import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] plate;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        plate = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            plate[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(plate);

        int sum = 0;

        for (int i = 0; i < N / 2; i++) {
            sum += plate[i];
        }

        System.out.print(sum + " ");
        sum = 0;

        for (int i = N / 2; i < N; i++) {
            sum += plate[i];
        }

        System.out.print(sum);
    }
}