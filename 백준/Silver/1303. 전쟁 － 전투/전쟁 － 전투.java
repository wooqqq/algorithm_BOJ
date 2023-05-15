import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int M;
    static char[][] map;
    static boolean[][] visited;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int cnt = 0;
    static int resultWhite = 0;
    static int resultBlue = 0;

    public static void main(String[] args) throws IOException {
        String[] NM = br.readLine().split(" ");
        M = Integer.parseInt(NM[0]);
        N = Integer.parseInt(NM[1]);
        map = new char[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];

        for (int y = 0; y < N; y++) {
            String tmp = br.readLine();
            for (int x = 0; x < M; x++) {
                map[y][x] = tmp.charAt(x);
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if(!visited[y][x]) {
                    cnt = 0;
                    dfs(y, x, map[y][x]);
                    if (map[y][x] == 'W') {
                        resultWhite += Math.pow(cnt, 2);
                    }

                    if (map[y][x]=='B') {
                        resultBlue += Math.pow(cnt, 2);
                    }
                }

            }
        }

        System.out.println(resultWhite + " " + resultBlue);
    }

    private static void dfs(int y, int x, char soldier) {
        cnt++;
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
            if (visited[ny][nx]) continue;
            if (map[ny][nx]==soldier) {
                dfs(ny, nx, soldier);
            }
        }
    }
}