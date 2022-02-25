package algoStudy.a0212;

import java.io.*;
import java.util.*;

public class Main_bj_2605_줄세우기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2605"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());
		int[] arr=new int[N];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) arr[i]=Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> ans=new ArrayList<>();
		for(int i=0; i<N; i++) ans.add(i-arr[i], i+1);
		for(int i=0; i<N; i++) System.out.print(ans.get(i)+" ");
	}

}
