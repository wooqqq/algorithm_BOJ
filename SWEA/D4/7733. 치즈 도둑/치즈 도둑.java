import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, max;
	static int[][] cheese;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb = new StringBuilder();
			sb.append("#" + t + " ");
			
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

			int day = 0;
			int cnt = 0;
			int max = Integer.MIN_VALUE;
			
			while (day <= 100) {
				cnt = 0;
				resetVisit();
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (cheese[r][c] > day && !visited[r][c]) {
							dfs(r, c, day);
							cnt++;
						}
					}
				}
				
				if (cnt > max) {
					max = cnt;
				}
				day++;
			}
		
			sb.append(max);
			System.out.println(sb);
		}
		
	}
	
	static void dfs(int r, int c, int day) {
		visited[r][c] = true;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			
			if (cheese[nr][nc] > day && !visited[nr][nc]) dfs(nr, nc, day);
		}
	}
	
	static void resetVisit() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
	}
	
}
