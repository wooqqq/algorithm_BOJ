import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, max;
	static int[][] arr, dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		dp = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < (r + 1); c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 맨 아랫줄 dp 값 채우기
		for (int i = 0; i < N; i++) {
			dp[N - 1][i] = arr[N - 1][i];
		}
		
		sb.append(find(0, 0));
		System.out.println(sb);
	}
	
	private static int find(int depth, int idx) {
		if (depth == N - 1) 
			return dp[depth][idx];
		
		if (dp[depth][idx] == 0) {
			dp[depth][idx] = Math.max(find(depth + 1, idx), find(depth + 1, idx + 1)) + arr[depth][idx];
		}
		
		return dp[depth][idx];
	}
	
}