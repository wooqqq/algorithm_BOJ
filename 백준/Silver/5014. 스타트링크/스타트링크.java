import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int floor = Integer.parseInt(str[0]);
        int start = Integer.parseInt(str[1]);
        int end = Integer.parseInt(str[2]);
        int up = Integer.parseInt(str[3]);
        int down = Integer.parseInt(str[4]);

        int[] arr = new int[floor + 1];
        System.out.println(BFS(floor, start, end, up, down, arr));
    }

    static String BFS(int floor, int start, int end, int up, int down, int[] arr) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        arr[start] = 1; // 시작 층 (버튼을 누르지 않았지만 방문하지 않은 층과 구분을 위해 1로 저장)

        while (!q.isEmpty()) {
            int current = q.poll();

            if (current == end) { // 현재 층이 도착 층인 경우
                return String.valueOf(arr[current] - 1); // 시작 층에서의 값이 1이기 때문에 1을 빼고 반환
            }

						if (current + up <= floor && arr[current + up] == 0) {
                arr[current + up] = arr[current] + 1;
                q.add(current + up);
            }

            if (current - down > 0 && arr[current - down] == 0) {
                arr[current - down] = arr[current] + 1;
                q.add(current - down);
            }
        }
        return "use the stairs";
    }
}