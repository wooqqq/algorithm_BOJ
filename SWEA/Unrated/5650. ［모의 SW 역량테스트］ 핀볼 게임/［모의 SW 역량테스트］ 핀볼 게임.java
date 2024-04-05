import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// BufferedReader + trim + StringBuilder
public class Solution {

	static int N, max;
	static int[] start;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 }; // 상, 하, 좌, 우
	static int[] dc = { 0, 0, -1, 1 };
	static List<int[]> worm;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;

		int T = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= T; t++) {
			sb = new StringBuilder();
			sb.append("#" + t + " ");

			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			worm = new ArrayList<>();

			// 게임판 입력받기
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());

					// 해당 자리의 값이 웜볼인 경우
					if (map[r][c] > 5 && map[r][c] < 11)
						worm.add(new int[] { map[r][c], r, c });

				}
			}

			// 핀볼 돌리기
			max = Integer.MIN_VALUE;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					start = new int[] { r, c };
					if (map[r][c] == 0) {
						for (int d = 0; d < 4; d++) {
							// 핀볼 시작
							move(r, c, d);
						}
					}
				}
			}
			sb.append(max);
			// 결과 출력
			System.out.println(sb);
		} // end of testCase

	} // end of main

	// 핀볼 움직이기
	static void move(int r, int c, int d) {
		// 이동 시작
		int nr = r + dr[d];
		int nc = c + dc[d];
		int cnt = 0;
		
		while (!setMaxValue(nr, nc, r, c)) {
			// 벽 => 방향 전환
			// index 값 확인해줘야 함
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
				if (d % 2 == 0) d++;
				else d--;
				
				// 원래 서있던 방향에서 반대 방향으로 한칸 이동
				nr += dr[d];
				nc += dc[d];
				cnt++;
			}
			
			// 블록 => 방향 전환
			else if (map[nr][nc] > 0 && map[nr][nc] < 6) {
				d = wayByBlock(map[nr][nc], d);
				nr += dr[d];
				nc += dc[d];
				cnt++;
			}
			
			// 0 => 전진
			else if (map[nr][nc] == 0) {
				nr += dr[d];
				nc += dc[d];
			}
			
			// 웜홀 => 좌표 바꾸고 전진
			else {
				for (int[] num : worm) {
					if (num[0] == map[nr][nc]) {
						if (nr != num[1] || nc != num[2]) {
							nr = num[1] + dr[d];
							nc = num[2] + dc[d];
							break;
						}
					}
				}
			}
			
		} // end of while
		
		// max 값 갱신
		max = Math.max(max, cnt);
		
	} // end of move

	// max 갱신 메서드
	static boolean setMaxValue(int r, int c, int stR, int stC) {
		if (r < 0 || r >= N || c < 0 || c >= N) return false;
		else if (map[r][c] == -1)
			return true;
		else if (r == stR && c == stC)
			return true;
		return false;
	}
	
	// 블록을 만났을 경우 핀볼의 방향 구하기
		static int wayByBlock(int blockNum, int curr) {
			// blockNum : 블록 숫자
			// curr : 현재 핀볼의 방향
			if (blockNum == 1) {
				switch(curr) {
				case 1:
					return 3;
				case 2:
					return 0;
				}
			} else if (blockNum == 2) {
				switch(curr) {
				case 0:
					return 3;
				case 2:
					return 1;
				}
			} else if (blockNum == 3) {
				switch(curr) {
				case 0:
					return 2;
				case 3:
					return 1;
				}
			} else if (blockNum == 4) {
				switch(curr) {
				case 1: 
					return 2;
				case 3:
					return 0;
				}
			}
			switch(curr) {
			case 0:
				return 1;
			case 1:
				return 0;
			case 2:
				return 3;
			case 3:
				return 2;
			}
			return -1;
		} // end of wayByBlock

}
