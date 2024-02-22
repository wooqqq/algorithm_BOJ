import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N, width, height, rest;
	static int[] direction;
	static List<int[]> map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 참외의 개수
		N = Integer.parseInt(br.readLine());
		
		map = new ArrayList<>();
		// 1 ~ 4 까지의 숫자 모두를 입력받아야하기에 크기가 5인 배열 생성
		direction = new int[5];
		width = Integer.MIN_VALUE;
		height = Integer.MIN_VALUE;
		
		// 단순 방향만 입력받기
		for (int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			int way = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			if (way == 3 || way == 4) {
				if (dist > height) {
					height = dist;
				}
			} else {
				if (dist > width) {
					width = dist;
				}
			}
			map.add(new int[] {way, dist});
			direction[way]++;
		}
		
		// 빈 부분 확인
		for (int i = 0; i < 6; i++) {
			// 중복인 방향을 만났을 경우
			if (direction[map.get(i)[0]] > 1 && i > 1) {
				// 1. index가 2부터 4까지인 숫자들 중 앞뒤의 방향이 서로 같은 경우
				if (i < 5 && i > 1 && map.get(i - 1)[0] == map.get(i + 1)[0]) {
					// i 번째 숫자와 i - 2 번째 숫자가 같은지 확인
					if (map.get(i)[0] == map.get(i - 2)[0]) {
						rest = map.get(i)[1] * map.get(i - 1)[1];
						break;
					} else {
						// 231424 인 경우
						// i번째와 i+1번째가 빈 부분
						rest = map.get(i)[1] * map.get(i + 1)[1];
						break;
					}
				} else { // 앞뒤가 서로 다른 숫자인 경우
					if (map.get(i)[0] == map.get((i + 2) % 6)[0]) {
						rest = map.get(i + 1)[1] * map.get((i + 2) % 6)[1];
						break;
					} else {
						if (map.get((i + 3) % 6)[0] == map.get(i - 1)[0]) {
							rest = map.get(i - 1)[1] * map.get(i - 2)[1];
							break;
						}
					}
				}
				
			}
		}
		
		int area = (width * height) - rest;
		sb.append(area * N);
		System.out.println(sb);
	}
}
