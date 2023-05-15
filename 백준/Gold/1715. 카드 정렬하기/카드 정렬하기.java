import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Long> cards = new PriorityQueue<>();
        long result = 0;

        for(int i = 0; i < N; i++) {
            cards.add(Long.parseLong(br.readLine()));
        }

        while(cards.size() > 1) {
            long num1 = cards.poll();
            long num2 = cards.poll();
            result += num1 + num2;
            cards.add(num1 + num2);
        }

        System.out.println(result);
    }
}