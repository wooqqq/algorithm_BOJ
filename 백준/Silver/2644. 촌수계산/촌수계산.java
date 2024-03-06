import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M, cnt, min, num1, num2;
	static boolean[] visited;
	static List<List<Integer>> link;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		num1 = Integer.parseInt(st.nextToken());
		num2 = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		visited = new boolean[N + 1];
		link = new ArrayList<>();
		
		for (int i = 0; i < N + 1; i++) {
			link.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			link.get(x).add(y);
			link.get(y).add(x);
		}
		
		cnt = 0;
		min = Integer.MAX_VALUE;
		dfs(num1, num2, cnt);
		
		if (min == Integer.MAX_VALUE) {
			min = -1;
		}
		
		sb.append(min);
		System.out.println(sb);
	}
	
	private static void dfs(int node, int child, int count) {
		visited[node] = true;
		
		if (link.get(node).contains(child)) {
			count += 1;
			min = Math.min(count, min);
			return;
		}
		
		for (int next : link.get(node)) {
			
			if (visited[next] == true) continue;
			
			dfs(next, child, count + 1);
		}
	}
}