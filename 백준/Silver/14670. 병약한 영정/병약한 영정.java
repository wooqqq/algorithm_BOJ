import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int N; // 약의 종류의 개수
    static int R; // 증상의 개수
    static HashMap<Integer, Integer> map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int stat = Integer.parseInt(st.nextToken());
            int name = Integer.parseInt(st.nextToken());

            map.put(stat, name); // 맵에 등록
        }

        R = Integer.parseInt(br.readLine());

        for (int i = 0; i < R; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int count = Integer.parseInt(st.nextToken());

            for (int j = 0; j < count; j++) {
                int dis = Integer.parseInt(st.nextToken());

                if (map.containsKey(dis)) {
                    sb.append(map.get(dis)).append(" ");
                } else {
                    sb.setLength(0);
                    sb.append("YOU DIED");
                    break;
                }
            }

            System.out.println(sb);
            sb.setLength(0);
        }
    }
}