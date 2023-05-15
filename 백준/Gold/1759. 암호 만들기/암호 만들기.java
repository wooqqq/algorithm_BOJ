import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        String[] arr = br.readLine().split(" ");
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        List<String> answer = new ArrayList<>();

        for (int i = 1; i < (1 << C); i++) {
            for (int j = 0; j < C; j++) {
                if ((i & (1 << j)) != 0) {
                    sb.append(arr[j]);
                }
            }

            if (sb.length() == L && check(sb.toString())) {
                answer.add(sb.toString());
            }
            sb.delete(0, C - 1);
        }

        Collections.sort(answer);
        System.out.println(String.join("\n", answer));
    }

    public static boolean check(String str) {
        int c = 0; // 자음의 개수
        int v = 0; // 모음의 개수

        for (int i = 0; i < str.length(); i++) {
            char x = str.charAt(i);
            if ("aeiou".indexOf(x) >= 0) {
                v++;
            } else {
                c++;
            }
        }

        if (c >= 2 && v >= 1) return true;
        return false;
    }
}