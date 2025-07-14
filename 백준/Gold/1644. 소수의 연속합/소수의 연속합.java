import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int N;
    static boolean[] valid;
    static List<Integer> nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nums = new ArrayList<>();

        calculate();

        int start = 0;
        int end = 0;
        int sum = 0;
        int cnt = 0;

        while (start <= end && end < nums.size()) {
            if (sum < N) {
                sum += nums.get(end++);
            } else {
                if (sum == N) {
                    cnt++;
                }
                sum -= nums.get(start++);
            }
        }

        System.out.println(cnt);
    }

    static void calculate() {
        valid = new boolean[N + 1];
        Arrays.fill(valid, true);

        valid[0] = valid[1] = false;
        for (int i = 2; i * i <= N; i++) {
            if (valid[i]) {
                for (int j = i * i; j <= N; j += i) {
                    valid[j] = false;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (valid[i]) nums.add(i);
        }

        nums.add(0);
    }
}