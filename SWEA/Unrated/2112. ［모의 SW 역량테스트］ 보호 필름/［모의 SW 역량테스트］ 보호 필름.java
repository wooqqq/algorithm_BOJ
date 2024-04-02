import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int D, W, K, min;
    static int[][] film, copy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        int T = Integer.parseInt(br.readLine());

        test:
        for (int t = 1; t <= T; t++) {
            sb = new StringBuilder();
            sb.append("#" + t + " ");

            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken()); // 필름의 세로 두께(행)
            W = Integer.parseInt(st.nextToken()); // 필름의 가로 크기(열)
            K = Integer.parseInt(st.nextToken()); // 세로로 연속해야 하는 합격기준 개수
            film = new int[D][W];
            copy = new int[D][W];

            // 보호 필름 입력받기
            for (int r = 0; r < D; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < W; c++) {
                    film[r][c] = Integer.parseInt(st.nextToken());
                    copy[r][c] = film[r][c];
                }
            }

            min = Integer.MAX_VALUE;
            // 초기 상태의 보호필름이 합격기준 모두 만족한다면 테스트 종료
            if (K == 1 || checkValid()) {
                min = 0;
                sb.append(min);
                System.out.println(sb);
                continue test;
            }

            // 1행씩 뽑아서 0 또는 1로 바꿔서 확인
            drop(0, 0);
            sb.append(min);
            System.out.println(sb);

        } // end of testCase
    } // end of main

    static void drop(int cnt, int idx) {
        if (cnt >= min) return;

        if (idx == D) {
            if (checkValid()) min = Math.min(min, cnt);
            return;
        }

        drop(cnt, idx + 1);

        // A 넣기
        for (int i = 0; i < W; i++) {
            copy[idx][i] = 0;
        }
        drop(cnt + 1, idx + 1);

        // B 넣기
        for (int i = 0; i < W; i++) {
            copy[idx][i] = 1;
        }
        drop(cnt + 1, idx + 1);

        // 초기화
        for (int i = 0; i < W; i++) {
            copy[idx][i] = film[idx][i];
        }
    }

    // 전체 열이 합격기준을 만족하는지 확인
    static boolean checkValid() {
        for (int c = 0; c < W; c++) {
            int count = 1;
            int num = copy[0][c];
            int max = 0;
            for (int r = 1; r < D; r++) {
                if (num == copy[r][c]) count++;
                else count = 1;
                num = copy[r][c];
                max = Math.max(max, count);
            }
            if (max < K) return false;
        }
        return true;
    }
}