import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static int cnt = 0;
    static String[][] arr;
    static boolean[][] visited;
    static HashSet<String> passed = new HashSet<>();
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new String[R + 1][C + 1]; // 보드
        visited = new boolean[R + 1][C + 1]; // 방문배열

        // 보드 정보 입력받기
        for (int i = 1; i < R + 1; i++) {
            String[] alphabets = br.readLine().split("");
            for (int j = 1; j < C + 1; j++) {
                arr[i][j] = alphabets[j-  1];
            }
        }

        dfs(1, 1);

        System.out.println(cnt);
    }

    private static void dfs(int i, int j) {
        passed.add(arr[i][j]);
        cnt = Math.max(cnt, passed.size());

        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];

            if (validateIdx(nx, ny) && !visited[nx][ny] && !passed.contains(arr[nx][ny])) {
                visited[nx][ny] = true;
                dfs(nx, ny);
                visited[nx][ny] = false;
            }
        }
        passed.remove(arr[i][j]);
    }

    private static boolean validateIdx(int x, int y) {
        return x > 0 && y > 0 && x <= R && y <= C;
    }
}