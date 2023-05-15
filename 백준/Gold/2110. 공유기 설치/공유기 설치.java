import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int home[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        home = new int[n]; // 집의 좌표
        for (int i = 0; i < n; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(home); // 이분탐색을 위한 정렬

        int low = 1; // 최소 거리가 가질 수 있는 최솟값
        int high = home[n - 1] - home[0] + 1 ; // 최소 거리가 가질 수 있는 최댓값 (초과하여 찾기때문에 +1)

        while (low < high) { // Upper Bound 형식
            int mid = (low + high) / 2;
            
            if (wifi(mid) < c) { // 최소거리=mid일 때 설치 가능한 공유기의 개수가 목표값보다 작다면
                high = mid;
            }else{ // 설치 가능한 공유기 개수가 목표 개수보다 크거나 같으면 최소거리가 가질 수 있는 최대거리를 찾아냄
                low = mid + 1;
            }
        }

        System.out.println(high-1); // 초과한 값을 찾았기 때문에 -1해줌

    }


    private static int wifi(int dist) { // 최소거리 dist일 때 설치 가능한 공유기 개수
        int count = 1; // 첫 번째 집은 무조건 설치
        int prevLocate = home[0]; // 직전 위치 초기화

        for (int i = 1; i < home.length; i++) {
            int nowLocate = home[i]; // 현재 위치

            if (nowLocate - prevLocate >= dist) { // 직전 위치와의 거리가 최소거리보다 클 때 설치 가능
                count++;
                prevLocate = nowLocate;
            }
        }
        return count;
    }
}