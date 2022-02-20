import java.util.*;
import java.io.*;

/* 순열 이용*/
public class Main {
	static int N;
	static int[] A, B, numbers;
	static boolean visited[];
	static int min=Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());

		A = new int[N];
		B = new int[N];
		
		visited = new boolean[N];
		
		numbers= new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(st1.nextToken());
		}
		
		permutation(0);
		System.out.println(min);
		br.close();
	}

	public static void permutation(int cnt) {
		if(cnt==N) {
			int sum=0;
			for (int i = 0; i < N; i++) {
				sum+=numbers[i]*B[i];
			}
			if(min>sum) min=sum;
			return;
		}
		for(int i=0; i<N; i++) {
			if(visited[i]) continue;
			
			numbers[cnt]=A[i];
			visited[i]= true;
			
			permutation(cnt+1);
			visited[i]=false;
		}
	}
}
