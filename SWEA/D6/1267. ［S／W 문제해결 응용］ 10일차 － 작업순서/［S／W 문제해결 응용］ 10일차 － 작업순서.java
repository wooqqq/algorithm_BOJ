import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	
	static int V, E;
	static int[][] adj;
	static int[] degree;
	static boolean[] visited;
	static Stack<Integer> stack;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		StringBuilder sb;
		
		int T = 10;
		
		for (int t = 1; t <= T; t++) {
			sb = new StringBuilder();
			sb.append("#" + t + " ");
			
			st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			adj = new int[V + 1][V + 1];
			degree = new int[V + 1];
			visited = new boolean[V + 1];
			stack = new Stack<>();
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < E; i++) {
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				adj[A][B] = 1;
				degree[B]++;
			}
			
			for (int i = 1; i < V + 1; i++) {
				if (degree[i] == 0)
					dfs(i);
			}
			
			while (!stack.isEmpty()) {
				sb.append(stack.pop() + " ");
			}
			
			System.out.println(sb);
		}
		
	}
	
	static void dfs(int v) {
		visited[v] = true;
		
		for (int i = 1; i < V + 1; i++) {
			if (adj[v][i] == 1 && !visited[i]) {
				dfs(i);
			}
		}
		
		stack.add(v);
	}
}