import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static int MIN;
    static int[][] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        distance = new int[V + 1][V + 1];

        // 최소거리의 초기값 지정
        MIN = 1000000000;

        // 같은 마을이 입력될 때에는 거리 0, 아니라면 INF로 일단 저장(초기화)
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = 1000000000;
                }
            }
        }

        // 각 마을을 연결하는 도로 저장
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            distance[a][b] = c;
        }

        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    distance[j][k] = Math.min(distance[j][k], distance[j][i] + distance[i][k]);
                }
            }
        }

        boolean check = false;

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i != j && distance[i][j] != 1000000000 && distance[j][i] != 1000000000) {
                    MIN = Math.min(distance[i][j] + distance[j][i], MIN);
                    check = true;
                }
            }
        }

        System.out.println(check ? MIN : -1);
    }
}