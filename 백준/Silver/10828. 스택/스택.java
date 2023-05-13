import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[] stack;
    public static int size = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // N 입력받음

        stack = new int[N]; // stack 은 N개의 숫자가 들어간 배열

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine(), " "); // StringTokenizer(문자열, 구분자)

            switch (st.nextToken()) {
                case "push":
                    push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(pop()).append('\n');
                    break;
                case "size":
                    sb.append(size()).append('\n');
                    break;
                case "empty":
                    sb.append(empty()).append('\n');
                    break;
                case "top":
                    sb.append(top()).append('\n');
                    break;
            }
            // sb.append(함수).append('\n') 는 [함수로 처리한 값] + '\n'(줄바꿈) 문자를 결합
        }
        System.out.println(sb);

    }

    public static void push(int item) {
        stack[size] = item;
        size++;
    }

    public static int pop() {
        if (size == 0) {
            return -1;
        } else {
            int res = stack[size-1];
            size--;
            return res;
        }
    }

    public static int size() {
        return size;
    }

    public static int empty() {
        if (size == 0) {
            return 1;
        } else {return 0;}
    }

    public static int top() {
        if (size == 0) {
            return -1;
        } else {
            return stack[size-1];
        }
    }
}