import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseNumber = Integer.parseInt(br.readLine());

        for (int i = 0; i < caseNumber; i++) {
            int num = Integer.parseInt(br.readLine());
            String[] strArr = br.readLine().trim().split(" ");

            System.out.println(new Solution().solution2(num, strArr));
        }
        br.close();
    }
}

class Solution {
    public int solution1(int num, String[] strArr) {   // 배열을 이용한 풀이
        int[] arr = new int[num];
        for (int i = 0; i < strArr.length; i++) {
            arr[i] = Integer.parseInt(strArr[i]);
        }

        Arrays.sort(arr);

        int[] result = new int[num];
        int left = 0;
        int right = num - 1;

        for (int i = 0; i < num; i++) {
            if (i % 2 == 0) result[left++] = arr[i];
            else result[right--] = arr[i];
        }

        int max = Math.abs(result[0] - result[num - 1]);
        for (int i = 0; i < num - 1; i++) {
            max = Math.max(max, Math.abs(result[i] - result[i + 1]));
        }
        return max;
    }

    public int solution2(int num, String[] strArr) {   // 우선순위 큐를 이용한 풀이
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < strArr.length; i++) {
            pq.offer(Integer.parseInt(strArr[i]));
        }

        int[] result = new int[num];
        int left = 0;
        int right = num - 1;

        for (int i = 0; i < num; i++) {
            if (i % 2 == 0) result[left++] = pq.poll();
            else result[right--] = pq.poll();
        }

        int max = Math.abs(result[0] - result[num - 1]);
        for (int i = 0; i < num - 1; i++) {
            max = Math.max(max, Math.abs(result[i] - result[i + 1]));
        }
        return max;
    }
}