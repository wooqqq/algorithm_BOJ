import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, M, maxCnt;
	static int[][] map;
	static int[] costMap;
	static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			// 운영 범위에 대한 비용 초기화
			initCostMap();
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxCnt = Integer.MIN_VALUE;
			
			// 전체 범위 확인
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					solve(r, c);
				}
			}
			
			// 답안 추가
			sb.append("#" + t + " ").append(maxCnt).append("\n");
		} // end of testCase
		
		// 출력
		System.out.println(sb);
	} // end of main
	
	static void initCostMap() {
		// 거리에 따라 이 비용은 고정되어있다.
		// 도시를 벗어난 영역에 서비스를 제공해도 운영 비용은 변경되지 않는다.
		// 운영영역의 크기 k는 그림을 그려보면 2N까지 필요하다.
		costMap = new int[map.length * 2];
		for (int k = 1; k < costMap.length; k++) {
			costMap[k] = k * k + (k - 1) * (k - 1);
		}
	} // end of initCostMap
	
	static void solve(int r, int c) {
		Queue<Home> q = new LinkedList<>();
		visited = new boolean[map.length][map.length];
		visited[r][c] = true;
		q.offer(new Home(r, c));
		
		// 시작 지점이 집이라면
		int houseCnt = 0;
		if (map[r][c] == 1) houseCnt = 1;
		
		int qSize = 0;
		int searchDepth = 1; // 감시 영역의 너비
		
		while (!q.isEmpty()) {
			if (houseCnt * M >= costMap[searchDepth]) {
				maxCnt = Math.max(maxCnt, houseCnt);
			}
			
			qSize = q.size();
			
			while (qSize-- > 0) {
				Home front = q.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = front.r + dirs[d][0];
					int nc = front.c + dirs[d][1];
					
					if (isIn(nr, nc) && !visited[nr][nc]) {
						visited[nr][nc] = true;
						q.offer(new Home(nr, nc));
						if (map[nr][nc] == 1) houseCnt++;
					}
				}
			}
			searchDepth += 1;
		}
		
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
	
	static class Home {
		int r, c;
		
		public Home(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
