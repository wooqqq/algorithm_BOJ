import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	static int T, N;
	static boolean isReverse, isError;
	static Deque<Integer> deque;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			
			// 명령 입력받기
			String order = br.readLine();
			
			// N 입력받기
			N = Integer.parseInt(br.readLine());
			
			// 배열 입력받기
			st = new StringTokenizer(br.readLine(), "[],");
			deque = new ArrayDeque<>();
			isReverse = false;
			isError = false;
			
			// 입력받은 배열 deque에 넣기
			for (int i = 0; i < N; i++) {
				deque.add(Integer.parseInt(st.nextToken()));
			}
			
			// 명령 수행하기
			applyOrder(order);
			
			// 출력하기
			if (isError) {
				continue;
			} else {
				printResult();
			}
		}
		System.out.println(sb);
	}
	
	// 명령 수행 메서드
	private static void applyOrder(String order) {
		
		for (char c : order.toCharArray()) {
			// 1. reverse
			if (c == 'R') {
				isReverse = !isReverse;
			}
			// 2. delete
			else {
				// 2-1. 뒤집힌 상태
				if (isReverse) {
					if (deque.pollLast() == null) {
						sb.append("error\n");
						isError = true;
						return;
					}
				} 
				// 2-2. 뒤집히지 않은 상태
				else {
					if (deque.pollFirst() == null) {
						sb.append("error\n");
						isError = true;
						return;
					}
				}
			}
		}
	}
	
	// 출력 수행 메서드
	private static void printResult() {
		sb.append("[");
		// 비어있는 상태라면 null이 아닌 빈 상태 출력
		if (deque.isEmpty()) {
			sb.append("");
		}
		// 뒤집힌 상태라면 뒤가 첫번째 숫자
		else if (isReverse) {
			sb.append(deque.pollLast());
			
			while (!deque.isEmpty()) {
				sb.append(",").append(deque.pollLast());
			}
		}
		// 뒤집히지 않은 상태라면 앞이 첫번째 숫자
		else {
			sb.append(deque.pollFirst());
			
			while (!deque.isEmpty()) {
				sb.append(",").append(deque.pollFirst());
			}
		}
		sb.append("]\n");
	}
}