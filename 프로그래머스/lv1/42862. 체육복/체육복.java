import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public static boolean isDebug;

    public int solution(int n, int[] lostArr, int[] reserveArr) {
        List<Integer> lost = Arrays.stream(lostArr).boxed().sorted().collect(Collectors.toCollection(ArrayList::new));
        List<Integer> reserve = Arrays.stream(reserveArr).boxed().sorted().collect(Collectors.toCollection(ArrayList::new));

        borrowSelf(lost, reserve);

        int notAttendCount = lost.size();

        notAttendCount -= borrow(lost, reserve);

        return n - notAttendCount;
    }

    private void borrowSelf(List<Integer> lost, List<Integer> reserve) {
        List<Integer> self = lost
                .stream()
                .filter(n -> reserve.contains(n))
                .collect(Collectors.toList());

        self.forEach(n -> {
            lost.remove(Integer.valueOf(n));
            reserve.remove(Integer.valueOf(n));
        });
    }

    public int borrow(List<Integer> lost, List<Integer> reserve) {
        int borrowCount = 0;

        for (int no : lost) {
            if (borrow(no, reserve)) {
                borrowCount++;
            }
        }

        return borrowCount;
    }

    private boolean borrow(int n, List<Integer> reserve) {
        if (reserve.contains(n - 1)) {
            reserve.remove(Integer.valueOf(n - 1));
            return true;
        }

        if (reserve.contains(n + 1)) {
            reserve.remove(Integer.valueOf(n + 1));
            return true;
        }

        return false;
    }
}