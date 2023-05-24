import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int last = N * (M % 10);
        int mid = ((M / 10) % 10) * N;
        int first = N * (M / 100);

        System.out.println(last);
        System.out.println(mid);
        System.out.println(first);
        System.out.println(N * M);
    }
}