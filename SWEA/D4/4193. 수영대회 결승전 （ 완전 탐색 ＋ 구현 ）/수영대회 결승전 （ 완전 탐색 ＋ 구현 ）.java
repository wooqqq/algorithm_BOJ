import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, time, minTime;
	static int[][] map;
	static boolean[][] visited;
	static Queue<int[]> queue;
	static int[] start;
	static int[] finish;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb = new StringBuilder();
			sb.append("#" + t + " ");

			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			visited = new boolean[N][N];

			// 배열 입력받기
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			start = new int[] { x, y };

			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());

			finish = new int[] { x, y };

			minTime = swim();
			sb.append(minTime);
			System.out.println(sb);
		}
	}

	static int swim() {
		queue = new LinkedList<>();
		int r = start[0];
		int c = start[1];
		visited[r][c] = true;
		queue.add(new int[] { r, c });

		int time = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int t = 0; t < size; t++) {
				int[] curr = queue.poll();

				for (int i = 0; i < 4; i++) {
					int nr = curr[0] + dr[i];
					int nc = curr[1] + dc[i];

					if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] == 1)
						continue;

					if (nr == finish[0] && nc == finish[1])
						return time + 1;

					if (map[nr][nc] == 2) {
						if (time < 2 || (time - 2) % 3 != 0) {
							queue.offer(curr.clone());
							continue;
						}

					}
					queue.offer(new int[] { nr, nc });
					visited[nr][nc] = true;
				}
			}
			time++;
		}

		return -1;
	}

}