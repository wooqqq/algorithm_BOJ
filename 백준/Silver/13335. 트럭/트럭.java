import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Queue<Integer> truck = new LinkedList<>();
        Queue<Integer> bridge = new LinkedList<>();

        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            truck.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < W; i++) {
            bridge.add(0);
        }

        int time = 0;
        int weight = 0;

        while (!bridge.isEmpty()) {
            time += 1;
            weight -= bridge.poll();

            if (!truck.isEmpty()) {
                if (truck.peek() + weight <= L) {
                    weight += truck.peek();
                    bridge.offer(truck.poll());
                } else {
                    bridge.add(0);
                }
            }
        }

        bw.write(Integer.toString(time));
        bw.flush();
        bw.close();
        br.close();
    }
}