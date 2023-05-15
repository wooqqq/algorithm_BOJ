import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Jewel> jewelList = new ArrayList<>();
        List<Integer> bagWeight = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.valueOf(st.nextToken());
            int price = Integer.valueOf(st.nextToken());
            jewelList.add(new Jewel(weight, price));
        }

        for (int i = 0; i < K; i++) {
            bagWeight.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(jewelList);
        Collections.sort(bagWeight);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        long sum = 0;
        int idx = 0;
        for (int i = 0; i < bagWeight.size(); i++) {
            while (idx < N && jewelList.get(idx).weight <= bagWeight.get(i)){
                pq.offer(jewelList.get(idx).price);
                idx++;
            }
            if(!pq.isEmpty()){
                sum += pq.poll();
            }
        }

        System.out.println(sum);

    }
    static class Jewel implements Comparable<Jewel> {
        private int weight;
        private int price;

        public Jewel(int weight, int price){
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Jewel o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}