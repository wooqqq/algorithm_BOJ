import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int K, W, H, min;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[] hx = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] hy = { 1, 2, 2, 1, -1, -2, -2, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];
		min = Integer.MAX_VALUE;

		// 지도 입력받기
		for (int r = 0; r < H; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < W; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}


		System.out.println(bfs(0, 0));
	} // end of main

	static int bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] check = new boolean[H][W][K + 1];

		check[y][x][0] = true;
		q.add(new int[] { x, y, 0, 0 });
		while (!q.isEmpty()) {
			int[] pos = q.poll();
			int px = pos[0], py = pos[1];
			int chance = pos[2], move = pos[3];

			// 도착지점 리턴
			if (px == W - 1 && py == H - 1) {
				return move;
			}

			// 기본 이동 : 원숭이 4가지 방향 탐색
			for (int i = 0; i < 4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];

				if (nx < 0 || nx > W - 1 || ny < 0 || ny > H - 1)
					continue;
				if (check[ny][nx][chance])
					continue;

				if (map[ny][nx] != 1) {
					check[ny][nx][chance] = true;
					q.add(new int[] { nx, ny, chance, move + 1 });
				}
			}

			// 말 찬스 이동 : 찬스 남아있으면 말 8가지 방향 탐색
			if (chance < K) {
				for (int i = 0; i < 8; i++) {
					int nx = px + hx[i];
					int ny = py + hy[i];

					if (nx < 0 || nx > W - 1 || ny < 0 || ny > H - 1)
						continue;
					if (check[ny][nx][chance + 1])
						continue;

					if (map[ny][nx] != 1) {
						check[ny][nx][chance + 1] = true;
						q.add(new int[] { nx, ny, chance + 1, move + 1 });
					}
				}
			}
		}
		return -1;
	}

}