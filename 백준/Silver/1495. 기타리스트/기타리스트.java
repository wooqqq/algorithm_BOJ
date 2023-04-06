import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean D[][] = new boolean[101][1001];
        int max = -1;
        int vol[] = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            vol[i] = Integer.parseInt(st.nextToken());
        }

        if (S - vol[0] >= 0) D[0][S-vol[0]] = true;
        if (S + vol[0] <= M) D[0][S+vol[0]] = true;

        for (int i = 0; i < N-1; i++) {
            for (int j = 0; j <= M; j++) {
                if (!D[i][j]) continue;

                if (j-vol[i+1] >= 0) {
                    D[i+1][j-vol[i+1]] = true;
                }
                if (j+vol[i+1] <= M) {
                    D[i+1][j+vol[i+1]] = true;
                }
            }
        }

        for (int i = 0; i <= M; i++) {
            if (D[N-1][i] && i > max) max = i;
        }

        System.out.println(max);
    }
}