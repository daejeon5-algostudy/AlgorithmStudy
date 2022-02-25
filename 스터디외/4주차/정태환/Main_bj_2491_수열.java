package algoStudy.a0211;

import java.io.*;
import java.util.*;

public class Main_bj_2491_수열 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2491"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		
		int[] arr=new int[N];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) arr[i]=Integer.parseInt(st.nextToken());
		// 가장 긴 길이, 증가길이, 감소길이 
		int max=1, size1=1, size2=1; 
		for(int i=1; i<N; i++) {
			if(arr[i-1]<arr[i]) {
				size1++; size2=1;
			}else if(arr[i-1]>arr[i]) {
				size1=1; size2++;
			}else {
				size1++; size2++;
			}
			max=max<size1 ? size1:max;
			max=max<size2 ? size2:max;
		}
		System.out.println(max);
	}
	
}
