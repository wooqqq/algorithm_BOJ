import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static int[] visited = new int[100001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		sb.append(bfs(N));
		System.out.println(sb);

	}

	private static int bfs(int node) {
		Queue<Integer> queue = new LinkedList<>();

		queue.add(node);
		int num = node;
		int n = 0;
		visited[num] = 1;

		while (!queue.isEmpty()) {
			n = queue.poll();

			if (n == K) {
				return visited[n] - 1;
			}
			if (n - 1 >= 0 && visited[n - 1] == 0) {
				visited[n - 1] = visited[n] + 1;
				queue.add(n - 1);
			}
			if (n + 1 < visited.length && visited[n + 1] == 0) {
				visited[n + 1] = visited[n] + 1;
				queue.add(n + 1);
			}
			if (n * 2 < visited.length && visited[n * 2] == 0) {
				visited[n * 2] = visited[n] + 1;
				queue.add(n * 2);
			}
		}
		return -1;
	}
}
