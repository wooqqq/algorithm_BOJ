import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int T, W, pos, treePos, answer;
	static int[] tree;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		tree = new int[T + 1];
		for (int i = 1; i <= T; i++) {
			tree[i] = Integer.parseInt(br.readLine());
		}

		pos = 1;
		dp = new int[T + 1][W + 1];
		answer = 0;

		for (int t = 1; t <= T; t++) {
			int treePos = tree[t];

			for (int w = 0; w <= W; w++) {
				if (w == 0) {
					pos = 1;
					if (treePos == pos) {
						dp[t][w] = dp[t - 1][w] + 1;
					} else {
						dp[t][w] = dp[t - 1][w];
					}

					continue;
				}

				// 이동 했을 때
				// 짝수번째 이동
				if (w % 2 == 0) {
					pos = 1;
					if (pos == treePos) {
						dp[t][w] = Math.max(dp[t - 1][w] + 1, dp[t - 1][w - 1]);
					} else {
						dp[t][w] = Math.max(dp[t - 1][w - 1] + 1, dp[t - 1][w]);
					}
				}
				// 홀수번째 이동
				else {
					pos = 2;
					if (pos == treePos) {
						dp[t][w] = Math.max(dp[t - 1][w] + 1, dp[t - 1][w - 1]);
					} else {
						dp[t][w] = Math.max(dp[t - 1][w - 1] + 1, dp[t - 1][w]);
					}
				}

				answer = Math.max(answer, dp[t][w]);
			}
		}

		sb.append(answer);
		System.out.println(sb);
	}
}