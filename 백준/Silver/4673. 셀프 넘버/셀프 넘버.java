public class Main {
    public static void main(String[] args) {

        int N = 10000;
        int[] arr = new int[N+1];

        for (int i = 1; i <= N; i++) {
            arr[i] = i;
        }

        StringBuilder sb = new StringBuilder();

         for (int i = 1; i <N; i++) {
            if(arr[i] != 0){
                int num = arr[i];
                int sum = arr[i];

                sb.append(num).append("\n");
                
                while(sum < N){
                    while(true){
                        sum += num%10;
                        num = num / 10;
                        if(sum > N) break;
                        if(num == 0){
                            arr[sum] = 0;
                            break;
                        }
                    }
                    num = sum;
                }
            }
        }
        System.out.println(sb.toString());
    }
}