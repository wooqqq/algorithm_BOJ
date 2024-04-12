import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int K, W, H;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 }; // 4방향 (상, 하, 좌, 우)
	static int[] dc = { 0, 0, -1, 1 };
	static int[] hr = { -2, -1, 1, 2, 2, 1, -1, -2 }; // 말 (시계방향)
	static int[] hc = { 1, 2, 2, 1, -1, -2, -2, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];

		for (int r = 0; r < H; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < W; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(bfs(0, 0));
	} // end of main

	static int bfs(int r, int c) {
		Queue<int[]> que = new LinkedList<>();
		boolean[][][] visited = new boolean[H][W][K + 1];
		
		visited[r][c][0] = true;
		que.add(new int[] { r, c, 0, 0 });

		while (!que.isEmpty()) {
			int[] now = que.poll();
			int x = now[0], y = now[1];
			int move = now[2], horse = now[3];

			if (x == H - 1 && y == W - 1) return move;
			
			// 4방향 전진
			for (int i = 0; i < 4; i++) {
				int nx = x + dr[i];
				int ny = y + dc[i];
				
				if (nx < 0 || nx >= H || ny < 0 || ny >= W)
					continue;
				if (visited[nx][ny][horse]) continue;

				if (map[nx][ny] == 0) {
					visited[nx][ny][horse] = true;
					que.add(new int[] { nx, ny, move + 1, horse });
				}
			}
			
			// 말 방향 전진
			if (horse < K) {
				for (int i = 0; i < 8; i++) {
					int hx = x + hr[i];
					int hy = y + hc[i];
					
					if (hx < 0 || hx >= H || hy < 0 || hy >= W) continue;
					if (visited[hx][hy][horse + 1]) continue;
					
					if (map[hx][hy] == 0) {
						visited[hx][hy][horse + 1] = true;
						que.add(new int[] {hx, hy, move + 1, horse + 1});
					}
				}
			}
		}
		return -1;
	}
}
