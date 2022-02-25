package algoStudy.a0211;

import java.io.*;
import java.util.*;

public class Main_bj_2559_수열 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2559"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());
		int[] arr=new int[N];
		st=new StringTokenizer(br.readLine());
		for(int i=0; i<N ;i++) arr[i]=Integer.parseInt(st.nextToken());
		
		int max=Integer.MIN_VALUE, sum=0;
		for(int i=0; i<N; i++)
			if (i<K) {
				sum+=arr[i];
				if (i == K-1)
					max=max>sum ? max:sum;
			} else {
				sum-=arr[i-K];
				sum+=arr[i];
				max=max>sum ? max:sum;
			}
		max=max>sum ? max:sum;
		System.out.println(max);
	}

}
