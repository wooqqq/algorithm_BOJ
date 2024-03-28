import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	
	static class Node implements Comparable<Node> {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
		
	}
	
	static int len, first;
	static List<Node>[] list;
	static int[] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		StringTokenizer st;
		
		int T = 10;
		
		for (int t = 1; t <= T; t++) {
			sb = new StringBuilder();
			sb.append("#" + t + " ");
			
			st = new StringTokenizer(br.readLine());
			len = Integer.parseInt(st.nextToken());
			first = Integer.parseInt(st.nextToken());
			
			list = new ArrayList[101];
			
			// 정점은 1부터 100까지
			for (int i = 1; i < 101; i++) {
				list[i] = new ArrayList<>();
			}
			
			dist = new int[101];
			Arrays.fill(dist, 0);
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < (len / 2); i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				list[from].add(new Node(to, 0));
			}
			
			dijkstra(first);
			
			int maxDist = 0;
			int ans = 0;
			for (int i = 0; i < 101; i++) {
				if (dist[i] >= maxDist) {
					maxDist = dist[i];
					ans = i;
				}
			}
			
			sb.append(ans);
			System.out.println(sb);
		}
		
	}
	
	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[101];
		
		dist[start] = 0;
		visited[start] = true;
		
		pq.add(new Node(start, 0));
		
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			
			for (Node node : list[curr.v]) {
				if (!visited[node.v]) {
					visited[node.v] = true;
					dist[node.v] = dist[curr.v] + 1;
					// 새로운 연결 정점 add
					pq.add(new Node(node.v, curr.w + 1));
				}
			}
		}
	}
	
}