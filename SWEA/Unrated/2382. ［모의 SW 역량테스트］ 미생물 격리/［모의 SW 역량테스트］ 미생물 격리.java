import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static int N, M, K, ans;
	static int[][] arr;
	static List<int[]> micro;
	static int[] dr = { 0, -1, 1, 0, 0 }; // X, 상, 하, 좌, 우
	static int[] dc = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb = new StringBuilder();
			sb.append("#" + t + " ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			arr = new int[N][N];
			micro = new ArrayList<>();

			// 미생물 정보 입력받기
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int nums = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());

				// 전체 미생물 관리를 위한 리스트 저장
				micro.add(new int[] { r, c, nums, dir });
			}

			move();
			ans = 0;
			for (int i = 0; i < micro.size(); i++) {
				ans += micro.get(i)[2];
			}
			sb.append(ans);
			System.out.println(sb);
		} // end of testCase

	} // end of main

	
	static void move() {
		int idx = 0;
		while (idx < M) {
			// 1. 정렬
			Collections.sort(micro, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					// 행을 먼저 비교
					if (o1[0] != o2[0]) {
						return Integer.compare(o1[0], o2[0]);
					}
					// 열을 비교
					else if (o1[1] != o2[1]) {
						return Integer.compare(o1[1], o2[1]);
					}
					// 크기를 비교
					else {
						return Integer.compare(o1[2], o2[2]);
					}
				}
			});
			
			// 2. 합
			for (int i = 0; i < micro.size(); i++) {
				int[] curr = micro.get(i);
				
				// curr과 앞의 것의 행, 열 좌표가 같은 경우
				if (i >= 1 && curr[0] == micro.get(i - 1)[0] && curr[1] == micro.get(i - 1)[1]) {
					micro.get(i)[2] += micro.get(i - 1)[2];
					micro.remove(i - 1);
					i--;
				}
				
			}
			
			// 3. 이동
			// 일단 모든 미생물 이동시키기
			for (int i = 0; i < micro.size(); i++) {
				int[] mic = micro.get(i);

				int nr = mic[0] + dr[mic[3]];
				int nc = mic[1] + dc[mic[3]];

				// 미생물 이동 위치 갱신
				micro.get(i)[0] = nr;
				micro.get(i)[1] = nc;

				// 만약 해당 위치가 약품이 칠해진 셀인 경우 nums / 2
				if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
					// 미생물 수 반토막
					micro.get(i)[2] /= 2;

					// 방향 반대로
					if (mic[3] == 1)
						micro.get(i)[3] = 2;
					else if (mic[3] == 2)
						micro.get(i)[3] = 1;
					else if (mic[3] == 3)
						micro.get(i)[3] = 4;
					else if (mic[3] == 4)
						micro.get(i)[3] = 3;
				}
			}

			idx++;
		} // end of while

	} // end of move()

}