import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	
	static int N, count;
	static int[] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb = new StringBuilder();
			sb.append("#").append(t + " ");
			
			N = Integer.parseInt(br.readLine());
			board = new int[N];
            count = 0;
			
			nQueen(0);
			
			sb.append(count);
			System.out.println(sb);
		}
	}
	
	private static void nQueen(int depth) {
		if (depth == N) {
			count++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			board[depth] = i;
			if (validate(depth)) nQueen(depth + 1);
		}
	}
	
	private static boolean validate(int c) {
		for (int i = 0; i < c; i++) {
			// 해당 열의 행과 i열의 행이 일치할 경우 (같은 행에 존재할 경우)
			if (board[c] == board[i]) return false;
			// 대각선상에 놓여있는 경우
			// 열의 차와 행의 차가 같을 경우 대각선에 놓여있는 경우
			else if (Math.abs(c - i) == Math.abs(board[c] - board[i])) return false;
		}
		return true;
	}
}