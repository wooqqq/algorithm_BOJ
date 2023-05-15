import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int[][] map;
    static int[][] rupee;
    static int N;
    static int[] dy = new int[]{-1, 0, 1, 0};
    static int[] dx = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0;

        while (true) {
            cnt++;
            N = Integer.parseInt(br.readLine());  // map의 크기
            if (N == 0) return;  // 0 입력시 종료

            map = new int[N][N];  // 가중치를 입력할 map
            rupee = new int[N][N];  // 최단 거리를 저장할 map

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    rupee[i][j] = Integer.MAX_VALUE;
                }
            }

            System.out.println("Problem " + cnt + ": " + dijkstra());

        }
    }

    private static int dijkstra() {
        PriorityQueue<Point> pq = new PriorityQueue<>();

        // 시작 위치 초기화
        rupee[0][0] = map[0][0];
        pq.offer(new Point(0, 0, map[0][0]));

        while (!pq.isEmpty()) {
            Point point = pq.poll();
            int y = point.y;
            int x = point.x;
            int weight = point.weight;

            if(weight > rupee[y][x]) continue;  // 이미 방문한 노드일 경우 넘어감

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;

                if(weight + map[ny][nx] < rupee[ny][nx]) {
										// 최단 거리 갱신
                    rupee[ny][nx] = weight + map[ny][nx];
										// 갱신 된 노드를 우선순위 큐에 넣는다.
                    pq.offer(new Point(ny, nx, rupee[ny][nx]));
                }

            }
        }

        return rupee[N-1][N-1];
    }

    static class Point implements Comparable<Point> {
        private int y;
        private int x;
        private int weight;

        public Point(int y, int x, int weight) {
            this.y = y;
            this.x = x;
            this.weight = weight;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}