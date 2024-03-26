import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long A, B, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        sb.append(pow(A, B));
        System.out.println(sb);
    }

    private static long pow(long a, long exp) {
        if (exp == 1) return A % C;

        long tmp = pow(A, exp / 2);

        if (exp % 2 == 1) return (tmp * tmp % C) * A % C;
        return tmp * tmp % C;
    }

}
