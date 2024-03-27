import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, M; // N : 정점의 개수, M : 간선의 개수
	static int[][] edges;
	static int[] p;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			sb = new StringBuilder();
			sb.append("#" + t + " ");
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// [0] : 명령어, [1] : 시작, [2] : 끝
			edges = new int[M][3];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				// 명령어
				edges[i][0] = Integer.parseInt(st.nextToken());
				edges[i][1] = Integer.parseInt(st.nextToken());
				edges[i][2] = Integer.parseInt(st.nextToken());
			}
			
			p = new int[N + 1];
			
			for (int i = 1; i < N + 1; i++) {
				p[i] = i;
			}
			
			for (int i = 0; i < M; i++) {
				if (edges[i][0] == 0) {
					int px = findset(edges[i][1]);
					int py = findset(edges[i][2]);
					
					if (px != py) {
						union(px, py);
					}
				} else {
					int px = findset(edges[i][1]);
					int py = findset(edges[i][2]);
					
					if (findset(px) != py) {
						sb.append("0");
					} else {
						sb.append("1");
					}
					
				}
				
			}
			
			System.out.println(sb);
		}
		
	}
	
	static void union(int x, int y) {
		p[y] = x;
	}
	
	static int findset(int x) {
		if (x != p[x])
			p[x] = findset(p[x]);
		return p[x];
	}
	
	static void makeset(int x) {
		p[x] = x;
	}
	
}