import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, H, max;
	static int[][][] box;
	static Queue<int[]> tomato;
	static int[] dr = {-1, 1, 0, 0, 0, 0};
	static int[] dc = {0, 0, -1, 1, 0, 0};
	static int[] dh = {0, 0, 0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		box = new int[N][M][H];
		tomato = new LinkedList<>();
		
		
		for (int h = 0; h < H; h++) {
			for (int c = 0; c < M; c++) {
				st = new StringTokenizer(br.readLine());
				for (int r = 0; r < N; r++) {
					box[r][c][h] = Integer.parseInt(st.nextToken());
					
					// 만약 익은 토마토라면 큐에 넣기
					if (box[r][c][h] == 1) tomato.add(new int[] {r, c, h});
				}
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
			int h = num[2];
			
			for (int d = 0; d < 6; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				int nh = h + dh[d];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || nh < 0 || nh >= H) continue;
				
				if (box[nr][nc][nh] == 0) {
					box[nr][nc][nh] = box[r][c][h] + 1;
					tomato.add(new int[] {nr, nc, nh});
				}
			}
		}
		
		max = Integer.MIN_VALUE;
		if (checkValid()) return -1;
		else {
			for (int h = 0; h < H; h++) {
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < M; c++) {
						if (max < box[r][c][h]) max = box[r][c][h];
					}
				}
			}
			return max - 1;
		}
	}
	
	private static boolean checkValid() {
		for (int h = 0; h < H; h++) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (box[r][c][h] == 0) return true;
				}
			}
		}
		return false;
	}
}