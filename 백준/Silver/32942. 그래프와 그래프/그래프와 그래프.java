import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 10; i++) graph.add(new ArrayList<>());

        String[] input = br.readLine().split(" ");
        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);
        int c = Integer.parseInt(input[2]);

        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if (a * i + b * j == c) {
                    graph.get(i - 1).add(j);
                }
            }
        }

        for (List<Integer> list : graph) {
            if (list.isEmpty()) {
                sb.append('0');
            } else {
                for (int i : list) {
                    sb.append(i).append(' ');
                }
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}