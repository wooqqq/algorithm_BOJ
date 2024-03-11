import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, dirty, count;
	static int[][] room;
	static boolean[][] visited;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new int[N][M];
		visited = new boolean[N][M];

		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		dirty = 0;
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				room[r][c] = Integer.parseInt(st.nextToken());
				
				if (room[r][c] == 0) dirty++;
			}
		}
		
		count = 0;
		clean(x, y, d);
		sb.append(count);
		System.out.println(sb);
	}

	private static void clean(int r, int c, int d) {
		if (count == dirty) return;
		
		if (room[r][c] == 0) {
			room[r][c] = 2;
			count += 1;
		}
		boolean isClean = false;

		start: for (int k = 0; k < 4; k++) {
			int nr = r + dr[k];
			int nc = c + dc[k];

			if (nr < 0 || nr >= N || nc < 0 || nc >= M)
				continue;

			if (room[nr][nc] == 0) {
				// 청소 가능한 경우
				while (true) {
					d = (d + 3) % 4;
					if (room[r + dr[d]][c + dc[d]] == 0) {
						clean(r + dr[d], c + dc[d], d);
						isClean = true;
						break start;
					}
				}
			} else {
				// 청소 불가능한 경우
				continue;
			}
		}
		
		// 후진해야 함
		if (!isClean) {
			int nr = r - dr[d];
			int nc = c - dc[d];
			
			if (nr >= 0 && nr < N && nc >= 0 && nc < M && room[nr][nc] != 1) {
                clean(nr, nc, d);
            }
		}
	}
}