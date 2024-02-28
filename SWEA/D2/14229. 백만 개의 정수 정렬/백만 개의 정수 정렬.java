import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] A;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = new int[1000000];
		
		for (int i = 0; i < A.length; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		// 정렬
		quickSort(0, A.length - 1);
		
		// A[500000] 출력
		System.out.println(A[500000]);
	}
	
	private static void quickSort(int left, int right) {
		if (left < right) {
			int pivot = partition(left, right);
			quickSort(left, pivot - 1);
			quickSort(pivot + 1, right);
		}
	}
	
	private static int partition(int left, int right) {
		int pivot = A[left];
		int L = left + 1, R = right;
		
		while (L <= R) {
			while (L <= R && A[L] <= pivot)
				L++;
			while (A[R] > pivot) 
				R--;
			
			if (L < R) {
				int tmp = A[L];
				A[L] = A[R];
				A[R] = tmp;
			}
		}
		
		int tmp = A[left];
		A[left] = A[R];
		A[R] = tmp;
		
		return R;
	}
}
