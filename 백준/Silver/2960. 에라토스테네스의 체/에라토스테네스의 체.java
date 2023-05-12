import java.util.Scanner;

public class Main {
    static public void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        boolean[] check = new boolean[N+1];

        int count = 0;

        for(int i = 2; i <= N; i++) {
            check[i] = true;
        }

        for(int i = 2; i <= N; i++) {
            for(int j = i; j <= N; j += i) {
                if(!check[j])
                    continue;
                check[j] = false;
                count ++;
                if(count == K) {
                    System.out.println(j);
                }
            }
        }
    }
}