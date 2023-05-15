import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    private static Stack<String> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] brackets = br.readLine().split("");

        int answer = 0;
        int tmp = 1;

        for (int i = 0; i < brackets.length; i++) {
            String bracket = brackets[i];
            if(bracket.equals("(")) {
                stack.push(bracket);
                tmp *= 2;
            }
            else if(bracket.equals(")")) {
                if(stack.isEmpty() || !stack.peek().equals("(")) {
                    System.out.println(0);
                    return;
                }

                if(brackets[i-1].equals("(")) {
                    answer += tmp;
                }

                stack.pop();
                tmp /= 2;
            }
            else if(bracket.equals("[")) {
                stack.push(bracket);
                tmp *= 3;
            }
            else if(bracket.equals("]")) {
                if(stack.isEmpty() || !stack.peek().equals("[")) {
                    System.out.println(0);
                    return;
                }

                if(brackets[i-1].equals("[")) {
                    answer += tmp;
                }

                stack.pop();
                tmp /= 3;
            } else {
                System.out.println(0);
                return;
            }
        }

        if(!stack.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }
}