import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> q = new PriorityQueue<>();

        for(int i=0; i<num; i++){
            int x = Integer.parseInt(br.readLine());
            if( x == 0 ){
                if(q.isEmpty()) {
                    bw.write(0+"\n");
                }else {
                    bw.write(q.poll()+"\n");
                }
            }else{
                q.add(x);
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}