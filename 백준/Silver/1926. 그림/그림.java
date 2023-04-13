import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int paper[][];
    static int dx[] = {0, 0, -1, 1};
    static int dy[] = {-1, 1, 0, 0};
    static boolean visited[][];
    static int drawing = 0;
    static int size = 0;
    static Queue<Node> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        paper = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (paper[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j);
                }
            }
        }
        System.out.println(drawing);
        System.out.println(size);

    }

    static void bfs(int x, int y) {
        queue = new LinkedList<>();
        queue.add(new Node(x, y));
        int count = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            count++;
            for (int d = 0; d < 4; d++) {
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || paper[nx][ny] == 0 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.add(new Node(nx, ny));
            }
        }

        drawing++;
        if (count > 1) {
            count--;
        }
        size = Math.max(size, count);


    }
}

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }
}