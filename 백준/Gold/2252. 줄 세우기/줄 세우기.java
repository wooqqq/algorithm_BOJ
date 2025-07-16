import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Integer>[] list;
    static int[] rank;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        rank = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            rank[b]++;
        }

        bfs();
        
        System.out.println(sb.toString());
    }

    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < N + 1; i++) {
            if (rank[i] == 0) queue.add(i);
        }
        
        while (!queue.isEmpty()) {
            int pos = queue.poll();

            sb.append(pos + " ");
            for (int next : list[pos]) {
                rank[next]--;
                if (rank[next] == 0) queue.add(next);
            }
        }
    }
}