import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int R, C, cnt;
	static char[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[R][C];
		
		// 지도 입력받기
		for (int r = 0; r < R; r++) 
			arr[r] = br.readLine().toCharArray();
		
		for (int r = 0; r < R; r++)
			if (dfs(r, 0)) cnt++;
		
		System.out.println(cnt);
	} // end of main
	
	static boolean dfs(int r, int c) {
		arr[r][c] = '-';
		
		if (c == C - 1) return true;
		
		if (r > 0 && arr[r - 1][c + 1] == '.') 
			if (dfs(r - 1, c + 1)) return true;
		
		if (arr[r][c + 1] == '.') 
			if (dfs(r, c + 1)) return true;
		
		if (r + 1 < R && arr[r + 1][c + 1] == '.')
			if (dfs(r + 1, c + 1)) return true;
		
		return false;
	}
}