import java.util.*;

public class Main {
    
    static int A[], arr[], N;
    static boolean visited[];
    static Set<String> set;
    
    public static void main (String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        int M = sc.nextInt();
        
        visited = new boolean[N];
        arr = new int[M];
        A = new int[N];
        for (int i = 0; i < N; i++) A[i] = sc.nextInt();
        
        Arrays.sort(A);
        
        set = new LinkedHashSet<>();
        
        dfs(0, M);
        
        
        set.forEach(System.out::println);
        
        
        sc.close();
    }
    
    
    static void dfs (int depth, int M) {
        
        if (depth == M) {
            String temp = "";
            for (int num : arr) {
                temp += num + " ";
            }
            set.add(temp);
            return;
        }
        
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[depth] = A[i];
                dfs(depth+1, M);
                visited[i] = false;
            }
        }
    }
}