import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static boolean[] visited;
	static List<List<Integer>> arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			arr.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			arr.get(x).add(y);
			arr.get(y).add(x);
		}
		
		boolean found = false;
		
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			if (finder(i, 1)) {
				found = true;
				break;
			}
		}
		
		sb.append(found? 1 : 0);
		System.out.println(sb);
	}
	
	private static boolean finder(int start, int len) {
		visited[start] = true;
		
		if (len == 5) return true;
		
		for (int next : arr.get(start)) {
			if (!visited[next]) {
				if (finder(next, len + 1)) return true;
			}
		}
		
		visited[start] = false;
		return false;
	}
}