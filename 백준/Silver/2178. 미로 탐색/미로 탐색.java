import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		// map 입력받기
		for (int r = 0; r < N; r++) {
			char[] arr = br.readLine().toCharArray();
			for (int c = 0; c < M; c++) {
				map[r][c] = arr[c] - '0';
			}
		}
		
		visited[0][0] = true;
		bfs(0, 0);

		sb.append(map[N - 1][M - 1]);
		System.out.println(sb);
	}
	
	private static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {r, c});
		
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int x = now[0];
			int y = now[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = x + dr[i];
				int nc = y + dc[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				if (visited[nr][nc] == true || map[nr][nc] == 0) continue;
				
				queue.add(new int[] {nr, nc});
				map[nr][nc] = map[x][y] + 1;
				visited[nr][nc] = true;
			}
		}
	}
}