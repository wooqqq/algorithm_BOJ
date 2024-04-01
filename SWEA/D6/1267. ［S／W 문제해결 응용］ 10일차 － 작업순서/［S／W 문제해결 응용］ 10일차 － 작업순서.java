import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int V, E;
	static int[][] adj;
	static int[] degree;
	static Queue<Integer> queue;
	
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
			
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < E; i++) {
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				adj[A][B] = 1;
				degree[B]++;
			}
			
			queue = new LinkedList<>();
			
			for (int i = 1; i < V + 1; i++) {
				if (degree[i] == 0)
					queue.offer(i);
			}
			
			while (!queue.isEmpty()) {
				int curr = queue.poll();
				sb.append(curr + " ");
				
				for (int i = 1; i < V + 1; i++) {
					if (adj[curr][i] == 1) {
						degree[i]--;
						adj[curr][i] = 0;
						
						if (degree[i] == 0)
							queue.offer(i);
					}
				}
			}
			
			System.out.println(sb);
		}
	}
}
