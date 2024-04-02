import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		sb.append(dp(N));
		System.out.println(sb);
	}
	
	static int dp(int num) {
		if (num == 0) return 1;
		else if (num % 2 == 1) return 0;
		else if (num == 2) return 3;
		
		int hidden = 0;
		for (int i = 2; i <= (num / 2); i++) {
			hidden += dp(num - (2 * i));
		}
		return dp(num - 2) * dp(2) + hidden * 2;
	}
}
