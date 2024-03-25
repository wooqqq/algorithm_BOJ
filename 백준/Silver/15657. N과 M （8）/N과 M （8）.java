import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] ans;
	static int[] nums;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ans = new int[M];
		nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);

		dfs(0, 0);
		System.out.println(sb);
	}

	static void dfs(int start, int depth) {
		if (depth == M) {
			for (int num : ans) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}

		for (int i = start; i < N; i++) {
			ans[depth] = nums[i];
			dfs(i, depth + 1);
		}
	}
}