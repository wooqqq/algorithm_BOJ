import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int dist = sc.nextInt();
        int result;
        if (dist % 5 == 0) {
            result = dist / 5;
        } else {
            result = (dist / 5) + 1;
        }
        System.out.println(result);
    }
}