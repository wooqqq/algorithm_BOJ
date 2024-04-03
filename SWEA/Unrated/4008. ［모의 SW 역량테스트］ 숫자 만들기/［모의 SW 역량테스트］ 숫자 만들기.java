import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, max, min;
	static int[] num;	// 숫자
	static int[] oper, count;	// 연산자의 개수
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb = new StringBuilder();
			sb.append("#" + t + " ");
			
			N = Integer.parseInt(br.readLine());
			
			oper = new int[4];	// 연산자의 종류는 4종류 (+, -, *, /)
			num = new int[N];	// 숫자는 총 N개
			count = new int[4];
			
			// 각 연산자의 개수 입력받기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				oper[i] = Integer.parseInt(st.nextToken());
			}
			
			// 숫자 입력받기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			// 최대값, 최소값 리셋
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			
			calc(num[0], 0, count);
			sb.append(max - min);
			System.out.println(sb);
			
		} // end of testCase
		
	} // end of main
	
	static void calc(int sum, int idx, int[] tried) {
		if (idx == N - 1) {
			if (sum > max) max = sum;
			if (sum < min) min = sum;
			return;
		}
		
		// + 연산
		if (oper[0] > 0 && tried[0] < oper[0]) {
			tried[0]++;
			calc(sum + num[idx + 1], idx + 1, tried);
			tried[0]--;
		}
		
		// - 연산
		if (oper[1] > 0 && tried[1] < oper[1]) {
			tried[1]++;
			calc(sum - num[idx + 1], idx + 1, tried);
			tried[1]--;
		}
		
		// * 연산
		if (oper[2] > 0 && tried[2] < oper[2]) {
			tried[2]++;
			calc(sum * num[idx + 1], idx + 1, tried);
			tried[2]--;
		}
		
		// / 연산
		if (oper[3] > 0 && tried[3] < oper[3]) {
			tried[3]++;
			calc(sum / num[idx + 1], idx + 1, tried);
			tried[3]--;
		}
		
	}
	
}