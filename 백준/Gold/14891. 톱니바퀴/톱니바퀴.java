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
		
		gear = new int[4][8];
		
		for(int i = 0; i < 4 ;i++) {
			String input = br.readLine();
			for(int j = 0; j < 8; j++) {
				gear[i][j] = input.charAt(j) - '0';
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int gearN = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			
			// 기어의 회전방향 정보
			d = new int[4];
			
			d[gearN] = dir;
			checkDir(gearN);
			gearTurn();
		}
		
		int ans =0;
		
		// S 극일 경우 각각 점수 부여
		if (gear[0][0] == 1) ans += 1;
		if (gear[1][0] == 1) ans += 2;
		if (gear[2][0] == 1) ans += 4;
		if (gear[3][0] == 1) ans += 8;
		
		System.out.println(ans);
	}

	static void checkDir(int gearN){
		// 좌측 톱니 회전 방향 검사
		for(int i = gearN - 1; i >= 0; i--) {
			if(gear[i][2] != gear[i + 1][6]) {
				d[i] = -d[i + 1];
			} else {
				// 회전을 하지않으면 다음 톱니도 회전을 하지 않게 된다.  
				break;
			}
		}
		// 우측 톱니 회전 방향 검사
		for(int i = gearN + 1; i < 4; i++) {
			if(gear[i][6] != gear[i - 1][2]) {
				d[i] = -d[i - 1];
			} else {
				// 회전을 하지않으면 다음 톱니도 회전을 하지 않게 된다.  
				break;
			}
		}	
	}
	
	static void gearTurn() {
		int temp = 0;
		
		for(int i = 0; i < 4; i++) { // 모든 톱니에 대해서
			// 시계방향 회전
			if(d[i] == 1) {
				temp = gear[i][7];
				for(int j = 7; j > 0; j--) {
					gear[i][j] = gear[i][j - 1];
				}
				gear[i][0] = temp;
			}
			// 반시계방향회전
			if(d[i] == -1) {
				temp = gear[i][0];
				for(int j = 0; j < 7; j++) {
					gear[i][j] = gear[i][j + 1];
				}
				gear[i][7] = temp;
			}
		}
	}
}