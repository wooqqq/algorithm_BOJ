import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            //객체를 담아야하므로 List를 선언
            List<rank> list = new ArrayList<>();

            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                int document_rank = Integer.parseInt(st.nextToken());
                int interview_rank = Integer.parseInt(st.nextToken());
                list.add(new rank(document_rank, interview_rank));
            }

            //내가 임의로 만든 rank 객체를 정렬해야함.
            Collections.sort(list, new Comparator<rank>() {
                @Override
                public int compare(rank o1, rank o2) {
                    return o1.document - o2.document;
                }
            });

            //첫번째 사람의 면접 등수
            int highRank = list.get(0).interview;
            int fall = 0;

            for (int j = 1; j < N; j++) {
                if (list.get(j).interview > highRank) {
                    fall++;
                    continue;
                }

                // 면접 등수가 기존의 highRank 보다 높은 경우
                highRank = list.get(j).interview;
            }

            System.out.println(N - fall);
        }

    }

    //서류 등수와 면접 등수를 저장할 클래스 정의
    public static class rank {
        int document;
        int interview;

        public rank(int document, int interview) {
            this.document = document;
            this.interview = interview;
        }
    }
}