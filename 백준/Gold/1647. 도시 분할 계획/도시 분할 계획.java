import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] p;
	static List<Edge> edges;

	static class Edge implements Comparable<Edge> {
		int start, end, w;

		public Edge(int start, int end, int w) {
			this.start = start;
			this.end = end;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edges = new ArrayList<>();

		// 간선 입력받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edges.add(new Edge(a, b, w));
		}
		
		p = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			p[i] = i;
		}
		
		Collections.sort(edges);

		int ans = 0;
		int max = 0;
		for (int i = 0; i < edges.size(); i++) {
			Edge edge = edges.get(i);
			
			if (findset(edge.start) != findset(edge.end)) {
				ans += edge.w;
				union(edge.start, edge.end);
				
				max = edge.w;
			}
		}
		
		sb.append(ans - max);
		System.out.println(sb);

	}
	
	// find
	static int findset(int x) {
		if (x == p[x]) return x;
		return p[x] = findset(p[x]);
	}
	
	// union
	static void union(int x, int y) {
		x = findset(x);
		y = findset(y);
		
		if (x != y) p[y] = x;
	}
	
}
