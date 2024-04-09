import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] d;
	static int[][] gear;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 톱니바퀴의 정보
		gear = new int[4][8];
		
		// 톱니바퀴 정보 입력받기
		for (int i = 0; i < 4; i++) {
			String input = br.readLine();
			for (int j = 0; j < 8; j++) {
				gear[i][j] = input.charAt(j) - '0';
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			
			d = new int[4];
			
			d[num] = dir;
			checkDir(num);
			turnGear();
		}
		
		int ans = 0;
		if (gear[0][0] == 1) ans += 1;
		if (gear[1][0] == 1) ans += 2;
		if (gear[2][0] == 1) ans += 4;
		if (gear[3][0] == 1) ans += 8;

		System.out.println(ans);
	} // end of main
	
	static void checkDir(int num) {
		// 시작점을 기준으로 좌측 톱니 회전유무
		for (int i = num - 1; i >= 0; i--) {
			if (gear[i][2] != gear[i + 1][6]) d[i] = -d[i + 1];
			else break;
		}
		
		// 시작점을 기준으로 우측 톱니 회전유무
		for (int i = num + 1; i < 4; i++) {
			if (gear[i][6] != gear[i - 1][2]) d[i] = -d[i - 1];
			else break;
		}
		
	} // end of checkDir
	
	static void turnGear() {
		int tmp = 0; 
		
		for (int i = 0; i < 4; i++) {
			// 시계 방향 회전
			if (d[i] == 1) {
				tmp = gear[i][7];
				for (int j = 7; j > 0; j--) {
					gear[i][j] = gear[i][j - 1];
				}
				gear[i][0] = tmp;
			}
			
			// 반시계 방향 회전
			if (d[i] == -1) {
				tmp = gear[i][0];
				for (int j = 0; j < 7; j++) {
					gear[i][j] = gear[i][j + 1];
				}
				gear[i][7] = tmp;
			}
		}
	} // end of turnGear
	
}