import java.io.*;
import java.util.*;

public class Main_bj_1026_보물2 {

	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/input_bj_1026.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] A = new int[N];
		int[] B = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");;
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A);
		Arrays.sort(B);
		
		int answer = 0;
		for(int i=0, j=N-1; i<N; i++, j--) {
			answer = answer + A[i] * B[j];
		}
		
		
		System.out.println(answer);
	}

}

