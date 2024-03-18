import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, result;
	static int[][] map, dist;
	static boolean[][][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dist = new int[N][M];
		visited = new boolean[2][N][M];
        
        // 시작지점과 도착지점이 같을 경우
		if (N - 1 == 0 && M - 1 == 0) {
			System.out.println(1);
			System.exit(0);
		}

		// 지도 입력받기
		for (int r = 0; r < N; r++) {
			String[] input = br.readLine().split("");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(input[c]);
			}
		}

		result = -1;
		bfs();

		System.out.println(result);

	}

	private static void bfs() {
		Queue<int[]> queue = new LinkedList<>();

		queue.offer(new int[] { 0, 0, 0 });
		map[0][0] = -1;

		while (!queue.isEmpty()) {
			int[] poll = queue.poll();
			int r = poll[0];
			int c = poll[1];
			int z = poll[2];

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M)
					continue;

				if (map[nr][nc] == 1) {
					if (z == 0 && !visited[1][nr][nc]) {
						visited[z][nr][nc] = true;
						dist[nr][nc] = dist[r][c] + 1;
						queue.offer(new int[] { nr, nc, 1 });
					}
				} else {
					if (!visited[z][nr][nc]) {
						visited[z][nr][nc] = true;
						dist[nr][nc] = dist[r][c] + 1;
						queue.offer(new int[] { nr, nc, z });
					}
				}

				if (nr == N - 1 && nc == M - 1) {
					result = dist[nr][nc] + 1;
					return;
				}
			}
		}
	}
}