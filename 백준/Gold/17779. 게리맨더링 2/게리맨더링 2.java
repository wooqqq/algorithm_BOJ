import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, total, min;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		total = 0;
		min = Integer.MAX_VALUE;
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				total += map[r][c];
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int x = 1; x < N; x++) {
					for (int y = 1; y < N; y++) {
						if (r + x + y >= N) continue;
						if (c - x < 0 || c + y >= N) continue;
						
						calc(r, c, x, y);
					}
				}
			}
		}
		
		System.out.println(min);
	} // end of main
	
	static void calc(int r, int c, int x, int y) {
		boolean[][] border = new boolean[N][N];
		
		// 경계선 세팅
		for (int i = 0; i <= x; i++) {
			border[r + i][c - i] = true;
			border[r + y + i][c + y - i] = true;
		}
		
		for (int i = 0; i <= y; i++) {
			border[r + i][c + i] = true;
			border[r + x + i][c - x + i] = true;
		}
		
		int[] peopleSum = new int[5];
		
		// 1 구역 인구수
		for (int i = 0; i < r + x; i++) {
			for (int j = 0; j <= c; j++) {
				if (border[i][j]) break;
				peopleSum[0] += map[i][j];
			}
		}
		
		// 2 구역 인구수
		for (int i = 0; i <= r + y; i++) {
			for (int j = N - 1; j > c; j--) {
				if (border[i][j]) break;
				peopleSum[1] += map[i][j];
			}
		}
		
		// 3 구역 인구수
		for (int i = r + x; i < N; i++) {
			for (int j = 0; j < c - x + y; j++) {
				if (border[i][j]) break;
				peopleSum[2] += map[i][j];
			}
		}
		
		// 4 구역 인구수
		for (int i = r + y + 1; i < N; i++) {
			for (int j = N - 1; j >= c - x + y; j--) {
				if (border[i][j]) break;
				peopleSum[3] += map[i][j];
			}
		}
		
		// 5 구역 인구수
		peopleSum[4] = total;
		
		for (int i = 0; i < 4; i++) {
			peopleSum[4] -= peopleSum[i];
		}
		
		// 정렬
		Arrays.sort(peopleSum);
		
		// 최대 - 최소
		min = Math.min(min, peopleSum[4] - peopleSum[0]);
	} // end of calc

}