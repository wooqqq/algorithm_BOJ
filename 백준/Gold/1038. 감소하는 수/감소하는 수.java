import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static int N;
    static List<Long> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        if (N <= 10) {
            System.out.println(N);
            return;
        } else if (N >= 1023) {
            System.out.println(-1);
            return;
        }

        for (int i = 0; i < 10; i++) {
            DFS(i);
        }

        Collections.sort(list);
        System.out.println(list.get(N));
    }

    private static void DFS(long num) {
        list.add(num);
        long lastNum = num % 10;
        if (lastNum == 0) {
            return;
        }

        for (long i = lastNum - 1; i >= 0; i--) {
            DFS((num * 10) + i);
        }
    }
}