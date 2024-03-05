import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int C, N, cnt;
	static boolean[] visited;
	static List<List<Integer>> numbers;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		C = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		numbers = new ArrayList<>();
		
		for (int i = 0; i < C + 1; i++) {
			numbers.add(new ArrayList<>());
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			
			numbers.get(num1).add(num2);
			numbers.get(num2).add(num1);
		}
		
		visited = new boolean[C + 1];
		
		cnt = 0;
		dfs(1);
		
		sb.append(cnt);
		System.out.println(sb);
	}
	
	private static void dfs(int node) {
		visited[node] = true;
		
		for (int next = 1; next <= C; next++) {
			if (!numbers.get(node).contains(next) || visited[next] == true) continue;
			
			cnt++;
			dfs(next);
		}		
	}
}