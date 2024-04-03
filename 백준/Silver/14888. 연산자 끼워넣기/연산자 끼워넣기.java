import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, max, min;
	static int[] nums, oper, tried;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		oper = new int[4];
		tried = new int[4];

		// 숫자 입력받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// 연산자 입력받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}

		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		calc(nums[0], 1);

		sb.append(max + "\n" + min);
		System.out.println(sb);
	} // end of main

	static void calc(int sum, int idx) {
		if (idx == N) {
			max = Math.max(sum, max);
			min = Math.min(sum, min);
			return;
		}

		// + 연산
		if (oper[0] > tried[0]) {
			tried[0]++;
			calc(sum + nums[idx], idx + 1);
			tried[0]--;
		}

		// - 연산
		if (oper[1] > tried[1]) {
			tried[1]++;
			calc(sum - nums[idx], idx + 1);
			tried[1]--;
		}

		// * 연산
		if (oper[2] > tried[2]) {
			tried[2]++;
			calc(sum * nums[idx], idx + 1);
			tried[2]--;
		}

		// / 연산
		if (oper[3] > tried[3]) {
			tried[3]++;
			calc(sum / nums[idx], idx + 1);
			tried[3]--;
		}
		
	} // end of calc
	
}