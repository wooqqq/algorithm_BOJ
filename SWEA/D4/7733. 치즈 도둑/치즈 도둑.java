import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static int[][] cheese;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dc = {0, 0, -1, 1};
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            sb = new StringBuilder();
            sb.append("#" + t + " ");

            N = Integer.parseInt(br.readLine());

            cheese = new int[N][N];
            visited = new boolean[N][N];

            // 치즈 각 칸의 맛있는 정도 입력받기
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    cheese[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            queue = new LinkedList<>();

            int cnt = 0;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i <= 100; i++) {
                cnt = 0;

                resetVisit();
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        if (cheese[r][c] > i && !visited[r][c]) {
                            bfs(r, c, i);
                            cnt++;
                        }
                    }
                }

                if (cnt > max) max = cnt;
            }
            if (max == 0) max = 1;
            sb.append(max);
            System.out.println(sb);
        }

    }

    private static void bfs(int r, int c, int day) {
        queue.add(new int[] {r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] input = queue.poll();
            int x = input[0];
            int y = input[1];

            // 델타배열을 통한 사방탐색
            for (int d = 0; d < 4; d++) {
                int nr = x + dr[d];
                int nc = y + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                if (cheese[nr][nc] > day && !visited[nr][nc]) {
                    queue.add(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
    }

    private static void resetVisit() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
    }

}
