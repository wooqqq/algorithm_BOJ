import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    private static int INF = Integer.MAX_VALUE;
    static List<List<Node>> list = new ArrayList<>();
    static int[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        distance = new int[N + 1];
        Arrays.fill(distance, INF);

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.get(a).add(new Node(b, c));
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(start, goal));
    }

    static int dijkstra(int start, int goal) {
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        boolean visited[] = new boolean[N + 1];

        distance[start] = 0;
        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int nodeIndex = node.index;
            int weight = node.weight;

            if (weight > distance[nodeIndex]) {
                continue;
            }

            for (Node linkedNode : list.get(nodeIndex)) {
                if (weight + linkedNode.weight < distance[linkedNode.index]) {
                    distance[linkedNode.index] = weight + linkedNode.weight;
                    queue.offer(new Node(linkedNode.index, distance[linkedNode.index]));
                }
            }
        }

        return distance[goal];
    }

    static class Node implements Comparable<Node> {
        private int index;
        private int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}