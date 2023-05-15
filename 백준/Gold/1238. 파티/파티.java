import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<List<Node>> graph, reverseGraph = new ArrayList<>();
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 학생 수 (마을 수)
        int M = Integer.parseInt(st.nextToken()); // 도로의 개수
        int X = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        reverseGraph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(end, time));
            reverseGraph.get(end).add(new Node(start, time));
        }

        int[] time = Dijkstra(graph, X);
        int[] reverseTime = Dijkstra(reverseGraph, X);

        int result = -1;
        for (int i = 1; i <= N; i++) {
            result = Math.max(result, time[i] + reverseTime[i]);
        }
        System.out.println(result);

    }

    private static int[] Dijkstra(List<List<Node>> graph, int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] time = new int[N + 1];

        Arrays.fill(time, Integer.MAX_VALUE);

        
        time[start] = 0;
        pq.offer(new Node(start,0));

        while (!pq.isEmpty()){
            Node node = pq.poll();
            int nodeIndex = node.index;
            int weight = node.weight;

            if(weight > time[nodeIndex]) continue; // 이미 방문한 노드 건너뜀

            for (Node linkedNode : graph.get(nodeIndex)) {
                if(time[linkedNode.index] > weight + linkedNode.weight){
                    time[linkedNode.index] = weight + linkedNode.weight;
                    pq.offer(new Node(linkedNode.index, time[linkedNode.index]));
                }
            }
        }

        return time;
    }

    static class Node implements Comparable<Node>{
        private int index;
        private int weight;

        public Node(int index, int weight){
            this.index = index;
            this.weight = weight ;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}