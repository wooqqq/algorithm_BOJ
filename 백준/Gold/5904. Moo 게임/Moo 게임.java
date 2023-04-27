import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static char answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Moo(N);
        System.out.println(answer);
    }

    public static void Moo(int N) {
        int size = 3;
        int index = 0;

        if (N == 1) {
            answer = 'm';
        } else if (N <= 3) {
            answer = 'o';
        } else {
            while (size < N) {
                size = size * 2 + index + 4;
                index++;
            }

            int front_back = (size - index - 3) / 2;

            if (size - front_back + 1 <= N) {
                Moo(N - size + front_back);
            } else if (N == front_back + 1) {
                answer = 'm';
            } else answer = 'o';
        }
    }
}