import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> queue = IntStream
                .range(0, progresses.length)
                .mapToObj(i -> (int) Math.ceil((100.0 - progresses[i]) / speeds[i]))
                .collect(Collectors.toCollection(LinkedList::new));

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int remains = queue.poll();
            int count = 1;

            while (!queue.isEmpty() && remains >= queue.peek()) {
                queue.poll();
                count++;
            }

            result.add(count);
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}