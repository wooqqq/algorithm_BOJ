import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, result, max, stR, stC;
	static int[][] map;
	static int[] dr = {1, 1, -1, -1};
	static int[] dc = {1, -1, -1, 1};
	static List<Integer> check;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			check = new ArrayList<>();
			result = 0;
			max = Integer.MIN_VALUE;
			
			// 지역 입력받기
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					stR = r;
					stC = c;
					check.add(map[r][c]);
					move(r, c, 0, 0);
					check.clear();
				}
			}
			
			if (max < 3) max = -1;
			
			// 출력
			sb.append("#").append(t + " ").append(max).append("\n");
		} // end of testCase
		
		System.out.println(sb);
	} // end of main
	
	static void move(int r, int c, int cnt, int dir) {
		start: for (int d = dir; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			
			if (nr == stR && nc == stC) {
				result = cnt + 1;
				max = Math.max(max, result);
				return;
			}
			
			if (check.contains(map[nr][nc])) continue start;
			
			check.add(map[nr][nc]);
			move(nr, nc, cnt + 1, d);
			check.remove(check.size() - 1);
		}
		
	} // end of move
	
}