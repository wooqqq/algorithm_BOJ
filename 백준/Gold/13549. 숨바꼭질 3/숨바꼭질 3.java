import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static boolean[] visited;
    //시작지점으로부터 걸리는 최소 시간을 저장하는 배열
    public static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        visited = new boolean[100001];
        dist = new int[100001];
        Arrays.fill(dist, Integer.MAX_VALUE);

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        System.out.println(dist[end]);

    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.second - o2.second;
            }
        });

        queue.add(new Node(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            visited[now.v] = true;

            for (int i = 0; i < 3; i++) {
                Node next = new Node();

                if (i == 0) {
                    next.v = now.v - 1;
                    next.second = 1;
                }
                if (i == 1) {
                    next.v = now.v + 1;
                    next.second = 1;
                }
                if (i == 2) {
                    next.v = now.v * 2;
                    next.second = 0;
                }

                if (next.v < 0 || next.v > 100000) {
                    continue;
                }

                if (!visited[next.v] && dist[next.v] > dist[now.v] + next.second) {
                    dist[next.v] = dist[now.v] + next.second;
                    queue.add(new Node(next.v, dist[next.v]));
                }
            }
        }
    }

    public static class Node {
        int v;
        int second;

        public Node(int v, int second) {
            this.v = v;
            this.second = second;
        }

        public Node() {
        }
    }
}