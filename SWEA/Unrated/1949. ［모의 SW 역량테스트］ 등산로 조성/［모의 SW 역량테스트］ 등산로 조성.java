import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static int N, K, maxH, max;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static List<int[]> highList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			maxH = Integer.MIN_VALUE;
			max = Integer.MIN_VALUE;
			highList = new ArrayList<>();

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] > maxH)
						maxH = map[r][c];
				}
			}

			// 높이가 높은 지점 찾기
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == maxH)
						highList.add(new int[] { r, c });
				}
			}

			// 높은 지점을 출발점으로 하여 탐색 시작
			for (int[] point : highList) {
				visited = new boolean[N][N];
				visited[point[0]][point[1]] = true;
				findRoad(point[0], point[1], 1, false);
			}

			sb.append("#" + t + " " + max + "\n");
		} // end of testCase

		System.out.println(sb);
	} // end of main

	static void findRoad(int r, int c, int cnt, boolean cutting) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;

			if (!visited[nr][nc] && map[nr][nc] < map[r][c]) {
				visited[nr][nc] = true;
				findRoad(nr, nc, cnt + 1, cutting);
				visited[nr][nc] = false;
			} else if (!visited[nr][nc] && !cutting && map[nr][nc] - K < map[r][c]) {
				int tmp = map[nr][nc];
				map[nr][nc] = map[r][c] - 1;
				visited[nr][nc] = true;
				findRoad(nr, nc, cnt + 1, true);
				map[nr][nc] = tmp;
				visited[nr][nc] = false;
			}
		}

		max = Math.max(cnt, max);
	} // end of findRoad

}