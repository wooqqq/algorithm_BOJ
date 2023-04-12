import java.io.*;

public class Main {
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        sb.append((int) (Math.pow(2, N) - 1)).append('\n');

        Hanoi(N, 1, 2, 3);
        System.out.println(sb);
    }

    public static void Hanoi(int N, int start, int sub, int goal) {
        if (N == 1) {
            sb.append(start + " " + goal + "\n");
            return;
        }

        Hanoi(N - 1, start, goal, sub);
        sb.append(start + " " + goal + "\n");
        Hanoi(N - 1, sub, start, goal);
    }
}