import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);

        List<Student> odd = new ArrayList<>();
        List<Student> even = new ArrayList<>();
        int[] cnt = new int[n + 1];

        while (true) {
            str = br.readLine().split(" ");

            if (Integer.parseInt(str[0]) == 0) break;

            int nums = Integer.parseInt(str[0]);
            String name = str[1];

            if (nums % 2 == 1) {
                if (cnt[nums] < m) {
                    odd.add(new Student(nums, name));
                    cnt[nums]++;
                }
            } else {
                if (cnt[nums] < m) {
                    even.add(new Student(nums, name));
                    cnt[nums]++;
                }
            }
        }

        Collections.sort(odd);
        Collections.sort(even);

        for (Student s : odd) {
            sb.append(s.nums).append(" ").append(s.name).append("\n");
        }

        for (Student s : even) {
            sb.append(s.nums).append(" ").append(s.name).append("\n");
        }

        System.out.println(sb);
    }

    public static class Student implements Comparable<Student> {
        int nums;
        String name;

        public Student(int nums, String name) {
            this.nums = nums;
            this.name = name;
        }

        @Override
        public int compareTo(Student other) {
            if (this.nums == other.nums) {
                if (this.name.length() == other.name.length()) {
                    return this.name.compareTo(other.name);
                }
                return this.name.length() - other.name.length();
            }
            return this.nums - other.nums;
        }
    }
}