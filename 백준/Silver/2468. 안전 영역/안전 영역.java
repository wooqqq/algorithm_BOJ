import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int maxHeight = 0;
        int maxSafe = 0;

        map = new int[N + 1][N + 1];

        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());

                if (map[y][x] > maxHeight) {
                    maxHeight = map[y][x];
                }
            }
        }

        for (int rain = 0; rain < maxHeight; rain++) {
            visited = new boolean[N + 1][N + 1];
            int cnt = 0;

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (visited[y][x] == false && map[y][x] > rain) {
                        dfs(x, y, rain);
                        cnt++;
                    }
                }
            }

            maxSafe = Math.max(cnt, maxSafe);
        }

        System.out.println(maxSafe);
    }

    private static void dfs(int x, int y, int rain) {
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
            if (visited[ny][nx] == true) continue;
            if (map[ny][nx] > rain) {
                dfs(nx, ny, rain);
            }

        }
    }
}