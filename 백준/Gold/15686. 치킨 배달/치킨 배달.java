import java.util.*;

public class Main {

    static int n, m;
    static int[][] map;
    static int min = Integer.MAX_VALUE;
    static ArrayList<Node> chicken = new ArrayList<>(); //치킨집의 위치 저장
    static ArrayList<Node> house = new ArrayList<>(); // 집의 위치 저장
    static boolean[] visited; // 뽑은 치킨집 체크

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        n = scan.nextInt();
        m = scan.nextInt();

        map = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                map[i][j] = scan.nextInt();
                if(map[i][j] == 1) house.add(new Node(i, j));
                else if(map[i][j] == 2) chicken.add(new Node(i, j));
            }
        }

        visited = new boolean[chicken.size()];
        backtracking(0, 0); // m 개의 치킨집을 뽑는 과정
        System.out.println(min);
    }

    public static void backtracking(int count, int idx) {
        if(count == m) { //치킨 거리의 최솟값을 구한다.
            int total = 0; // 도시의 치킨거리
            for(int i = 0; i < house.size(); i++) {
                int sum = Integer.MAX_VALUE;
                for(int j = 0; j < chicken.size(); j++) {
                    if(visited[j] == true) { //i번째 집에서부터 j번짜 치킨집 까지의 거리 중 최소값을 구한다.
                        int dist = Math.abs(house.get(i).x - chicken.get(j).x)
                                + Math.abs(house.get(i).y - chicken.get(j).y);
                        sum = Math.min(sum, dist);
                    }
                }
                total += sum;
            }
            min = Math.min(total, min);
            return;
        }

        for(int i = idx; i < chicken.size(); i++) {
            if(visited[i] == false) {
                visited[i] = true;
                backtracking(count + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}