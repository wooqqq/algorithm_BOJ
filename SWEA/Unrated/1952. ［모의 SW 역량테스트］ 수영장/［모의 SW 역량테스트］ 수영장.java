import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int minValue;
    static int[] ticket, month;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            sb = new StringBuilder();
            sb.append("#" + t + " ");

            ticket = new int[4];
            month = new int[12];

            // 이용권 가격 입력받기
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < ticket.length; i++) {
                ticket[i] = Integer.parseInt(st.nextToken());
            }

            // 각 달 이용횟수 입력받기
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < month.length; i++) {
                month[i] = Integer.parseInt(st.nextToken());
            }

            // 기본값은 연간 이용권
            minValue = Integer.MAX_VALUE;
            dfs(0, 0);
            minValue = Math.min(ticket[3], minValue);
            sb.append(minValue);
            System.out.println(sb);
        }

    }

    static void dfs(int cnt, int fee) {
        if (cnt >= 12) {
            minValue = Math.min(minValue, fee);
            return;
        }

        if (month[cnt] == 0) {
            dfs(cnt + 1, fee);
        } else {
            // 하루 이용권
            dfs (cnt + 1, fee + (month[cnt] * ticket[0]));

            // 한달 이용권
            dfs (cnt + 1, fee + ticket[1]);

            // 세달 이용권
            dfs (cnt + 3, fee + ticket[2]);
        }
    }
}
