import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int M, N, max;
	static int[][] box;
	static Queue<int[]> tomato;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		box = new int[N][M];
		tomato = new LinkedList<>();
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				box[r][c] = Integer.parseInt(st.nextToken());
				
				// 만약 해당 자리가 익은 토마토라면 큐에 집어넣기
				if (box[r][c] == 1) tomato.add(new int[] {r, c});
			}
		}
		
		max = bfs();
		sb.append(max);
		System.out.println(sb);
		
	}
	
	private static int bfs() {
		while (!tomato.isEmpty()) {
			int[] num = tomato.poll();
			int r = num[0];
			int c = num[1];
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if (box[nr][nc] == 0) {
					box[nr][nc] = box[r][c] + 1;
					tomato.add(new int[] {nr, nc});
				}
			}
		}
		
		max = Integer.MIN_VALUE;
		if (checkValid()) return -1;
		else {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (max < box[r][c]) max = box[r][c];
				}
			}
			return max - 1;
		}
		
	}
	
	private static boolean checkValid() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (box[r][c] == 0) return true;
			}
		}
		return false;
	}
}