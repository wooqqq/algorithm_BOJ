import java.util.Scanner;
class Main{
	public static void main(String[] args){
		Scanner s=new Scanner(System.in);
		int[] a=new int[3];
		int i=0;
		while (i<3) {
			a[i++]=s.nextInt();
		} System.out.print(a[1]/a[0]*a[2]);
	}
}