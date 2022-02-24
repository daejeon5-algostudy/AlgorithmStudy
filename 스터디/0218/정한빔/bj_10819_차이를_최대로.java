import java.io.*;
import java.util.*;
/* 합은
 * |A[0] - A[1]| + |A[1] - A[2]| + ... + |A[N-2] - A[N-1]|
 * 순열에 가지치기 해주는것
 * */
public class Main {
	static int [] arr, num;
	static int N,Max;
	static boolean[] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine()); //종이의 수
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		num = new int[N];
		v=new boolean[N];
		Max=Integer.MIN_VALUE;
		perm(0);
		System.out.println(Max);
		br.close();
	}
	
	static void perm(int cnt) {
		if(cnt==N) {
			int sum=0;
			System.out.println(Arrays.toString(num));
			for (int i = 0; i <N-1; i++) {
				sum+=Math.abs(num[i]-num[i+1]);
			}
			if(sum>Max)Max=sum;
			return;
		}
		for (int i = 0; i < N; i++) {
			if(v[i]) continue;
			num[cnt]=arr[i];
			v[i]=true;
			perm(cnt+1);
			v[i]=false;
		}
		
	}
	
}
