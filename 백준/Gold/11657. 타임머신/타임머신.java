import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Edge> graph;
    static long time[];
    static int N, M;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        time = new long[N + 1];

        Arrays.fill(time, INF);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph.add(new Edge(start, end, time));
        }

        boolean result = bellmanFord(1);

        if(!result) {
            System.out.println(-1);
        }else{
            for (int i = 2; i < time.length; i++) {
                if(time[i] == INF) System.out.println(-1);
                else System.out.println(time[i]);
            }
        }

    }

    static boolean bellmanFord(int start) {
        time[start] = 0;

        boolean result = true;

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                Edge edge = graph.get(j);

                if (time[edge.start] != INF && time[edge.end] > time[edge.start] + edge.weight)   {
                    time[edge.end] =  time[edge.start] + edge.weight;
                    if(i == N){
                        result = false;
                        break;
                    }
                }
            }
        }

        return result;
    }


}

class Edge {
    int start;
    int end;
    int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}