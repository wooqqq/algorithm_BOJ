import java.util.*;
public class Main {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        long P[] = new long[101];

        P[1] = 1;
        P[2] = 1;
        P[3] = 1;
        P[4] = 2;
        P[5] = 2;

        for (int i = 6; i <= 100; i++) {
            P[i] = P[i-1] + P[i-5];
        }

        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            System.out.println(P[N]);
        }
    }
}