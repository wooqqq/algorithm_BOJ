import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static int N, answer;
    public static int[] num = {1, 5, 10, 50};
    public static HashSet<Integer> validate = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        calc(0, 0, 0);

        System.out.println(validate.size());
    }

    static void calc(int level, int sum, int idx) {
        if (level == N) {
            validate.add(sum);
            return;
        }

        for (int i = idx; i < 4; i++) {
            calc(level + 1, sum + num[i], i);
        }
    }
}