import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int N, count;
	static int[] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		board = new int[N];
		
		count = 0;
		nQueen(0);
		
		sb.append(count);
		System.out.println(sb);
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
			if (board[c] == board[i]) return false;
			else if (Math.abs(c - i) == Math.abs(board[c] - board[i])) return false;
		}
		return true;
	}
}