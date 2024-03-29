import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge implements Comparable<Edge> {
		int a, b, w;
		
		public Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}

	}
	
	static int N, M;
	static int[][] edges;
	static int[] p;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		edges = new int[M][3];
		
		Edge[] edges = new Edge[M];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(a, b, w);
		} // 간선 입력 끝
		
		Arrays.sort(edges);
		
		p = new int[N + 1];
		
		for (int i = 0; i < N + 1; i++) {
			p[i] = i;
		}
		
		int ans = 0;
		int pick = 0;
		
		for (int i = 0; i < M; i++) {
			int px = findset(edges[i].a);
			int py = findset(edges[i].b);
			
			if (px != py) {
				union(px, py);
				ans += edges[i].w;
				pick++;
			}
			
			if (pick == (N - 1)) break;
		}
		
		System.out.println(ans);
	}
	
	private static void union(int x, int y) {
		p[y] = x;
	}
	
	private static int findset(int x) {
		if (x != p[x]) p[x] = findset(p[x]);
		return p[x];
	}
	
}