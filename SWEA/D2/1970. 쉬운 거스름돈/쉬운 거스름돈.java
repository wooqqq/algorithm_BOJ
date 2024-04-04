import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	
	static int N;
	static int[] money;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb = new StringBuilder();
			sb.append("#" + t + "\n");
			
			N = Integer.parseInt(br.readLine());
			money = new int[8];
			
			if (N >= 50000) {
				money[0] = N / 50000;
				N -= (50000 * money[0]);
			}
			if (N >= 10000) {
				money[1] = N / 10000;
				N -= (10000 * money[1]);
			}
			if (N >= 5000) {
				money[2] = N / 5000;
				N -= (5000 * money[2]);
			}
			if (N >= 1000) {
				money[3] = N / 1000;
				N -= (1000 * money[3]);
			}
			if (N >= 500) {
				money[4] = N / 500;
				N -= (500 * money[4]);
			}
			if (N >= 100) {
				money[5] = N / 100;
				N -= (100 * money[5]);
			}
			if (N >= 50) {
				money[6] = N / 50;
				N -= (50 * money[6]);
			}
			if (N >= 10) {
				money[7] = N / 10;
				N -= (10 * money[7]);
			}
			
			for (int i = 0; i < money.length; i++) {
				sb.append(money[i] + " ");
			}
			
			System.out.println(sb);
		}
	}
}