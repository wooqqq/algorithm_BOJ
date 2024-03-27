import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N, day, maxCnt;
	static int[][] cheese;
	static boolean[][] visited;
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
			int cnt = 0;
			day = 0;

			N = Integer.parseInt(br.readLine());

			cheese = new int[N][N];
			visited = new boolean[N][N];

			// 치즈 입력받기
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					cheese[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			day = 0;
			cnt = 0;
			maxCnt = Integer.MIN_VALUE;
			while (day <= 100) {
				cnt = 0;
				visited = new boolean[N][N];
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (cheese[r][c] == day && !visited[r][c]) {
							bfs(r, c);
						}
					}
				}

				// 치즈 존재하는 부분 개수 세기
				visited = new boolean[N][N];
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (cheese[r][c] > 0 && !visited[r][c]) {
							// 비슷한 메서드 만들기
							isCheese(r, c);
							cnt++;
						}
					}
				}
				if (cnt > maxCnt) {
					maxCnt = cnt;
				}
				day++;
			}
			
			if (maxCnt <= 0) {
				maxCnt = 1;
			}
			sb.append(maxCnt);
			System.out.println(sb);
		}

	}

	private static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {r, c});
		visited[r][c] = true;
		cheese[r][c] = 0;
		
		while (!queue.isEmpty()) {
			int[] input = queue.poll();
			int x = input[0];
			int y = input[1];
			
			for (int d = 0; d < 4; d++) {
				int nr = x + dr[d];
				int nc = y + dc[d];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				
				if (cheese[nr][nc] == day && !visited[nr][nc]) {
					queue.add(new int[] {nr, nc});
					visited[nr][nc] = true;
					
					cheese[nr][nc] = 0;
				}
			}
		}

	}

	private static void isCheese(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {r, c});
		visited[r][c] = true;
		
		while (!queue.isEmpty()) {
			int[] input = queue.poll();
			int x = input[0];
			int y = input[1];
			
			for (int d = 0; d < 4; d++) {
				int nr = x + dr[d];
				int nc = y + dc[d];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				
				if (cheese[nr][nc] > 0 && !visited[nr][nc]) {
					queue.add(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
		}
		
	}
	
}
