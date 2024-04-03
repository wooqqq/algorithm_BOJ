import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, B, diff, min;
	static int[] empl;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb = new StringBuilder();
			sb.append("#" + t + " ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			empl = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				empl[i] = Integer.parseInt(st.nextToken());
			}

			min = Integer.MAX_VALUE;

			calc(0, 0);
			sb.append(min - B);
			System.out.println(sb);
		}

	}

	static void calc(int sum, int idx) {
		if (idx == N) {
			if (sum >= B)
				min = Math.min(min, sum);
			return;
		}

		calc(sum, idx + 1);
		calc(sum + empl[idx], idx + 1);
	}

}