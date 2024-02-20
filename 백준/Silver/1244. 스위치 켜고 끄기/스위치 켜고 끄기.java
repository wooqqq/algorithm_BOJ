import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, S;
    static int[] light;
    static List<int[]> student;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        light = new int[N];

        // 스위치 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            light[i] = Integer.parseInt(st.nextToken());
        }

        // 학생 수 입력받기
        S = Integer.parseInt(br.readLine());
        student = new ArrayList<>();

        // 학생 입력받기
        for (int i = 0; i < S; i++) {
            st = new StringTokenizer(br.readLine());
            int gen = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            student.add(new int[] {gen, num});
        }

        // 스위치 계산
        for (int i = 0; i < S; i++) {
            if (student.get(i)[0] == 1) {
                boys(student.get(i)[1]);
            } else {
                girls(student.get(i)[1]);
            }
        }

        for (int i = 0; i < N; i++) {
            sb.append(light[i]).append(" ");
            if ((i + 1) % 20 == 0) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void boys(int num) {
        int m = 1;
        while (num * m <= N) {
            light[num * m - 1] = 1 - light[num * m - 1];
            m++;
        }
    }

    private static void girls(int num) {
        light[num - 1] = 1 - light[num - 1];
        for (int i = 1; i < N - num + 1 && i <= num - 1; i++) {
            if (light[num + i - 1] == light[num - i - 1]) {
                light[num + i - 1] = 1 - light[num + i - 1];
                light[num - i - 1] = 1 - light[num - i - 1];
            } else {
                break;
            }
        }
    }
}