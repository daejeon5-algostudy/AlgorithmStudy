import java.io.*;
import java.util.*;

public class Main_bj_1026_보물 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N= Integer.parseInt(br.readLine());
		Integer[] A = new Integer[N];
		Integer[] B = new Integer[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=0; i<N;i++) {
			A[i]=Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine()," ");
		for(int i=0; i<N;i++) {
			B[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);
		Arrays.sort(B, Collections.reverseOrder());
		int sum =0;
		for(int i=0; i<N;i++) {
			sum+=A[i]*B[i];
		}
		System.out.println(sum);
		br.close();
	}
}
