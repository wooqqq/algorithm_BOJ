import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int N, K;
    static TreeSet<String> password;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            sb = new StringBuilder();
            sb.append("#" + t + " ");
            password = new TreeSet<>(Collections.reverseOrder());

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            char[] input = br.readLine().toCharArray();

            generatePass(input);

            // 10진수로 만들기
            String[] ans = password.toArray(new String[password.size()]);
            int result = Integer.parseInt(ans[K - 1], 16);
            sb.append(result);
            System.out.println(sb);
        }

    }

    // 암호 생성하여 password에 저장하는 메서드
    private static void generatePass(char[] input) {
        int idx = 0;
        int i = 0;
        while (i > -(N / 4)) {
            // 시작 인덱스
            idx = (N + i) % N;

            // 4번 반복 => 네 변의 암호를 모두 생성해보고 저장해야 함
            for (int t = 0; t < 4; t++) {
                // 하나의 암호를 생성하는 역할
                String pass = "";
                for (int d = 0; d < (N / 4); d++) {
                    // 암호 자리수 + 시작 인덱스 + 암호 순서
                    int nd = (d + idx + (t * (N / 4))) % N;

                    pass = pass + input[nd];
                }
                // 암호 생성하면서 동시에 리스트에 넣기
                // 단, 기존에 있던 암호이면 안된다.

                if (!password.contains(pass)) {
                    password.add(pass.toString());
                }
            }

            // 마지막에 회전
            i--;
        }

    }

}