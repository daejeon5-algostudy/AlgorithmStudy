package algoStudy.a0210;

import java.io.*;
import java.util.*;

public class Main_bj_2309_일곱난쟁이 {
	static int[] arr;
	static int[] ans=new int[7];
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2309"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		arr=new int[9]; // 문제에서 9명이라고 주어짐
		for(int i=0; i<9; i++) arr[i]=Integer.parseInt(br.readLine());

		cf(0,0,new boolean[9]); // 재귀호출을 통해 답을 구함
		
		Arrays.sort(ans); // 구해진 배열을 오름차순으로 정렬
		for (int i=0; i<7; i++) System.out.println(ans[i]);
	}
	static void cf(int cnt, int sum, boolean[] visit) {
		if(cnt==7 && sum==100) { // 7명의 값을 더했을때 결과값이 100이면 도출함
			int idx=0;
			for(int i=0; i<9; i++)
				if(visit[i]==true) ans[idx++]=arr[i];
		}
		if(cnt>=7 || sum>=100) return;
		
		for(int i=0; i<9; i++)
			if(visit[i]==false) {
				visit[i]=true;
				cf(cnt+1, sum+arr[i], visit); // 방문했는지 여부에따라 재귀를 호출
				visit[i]=false;
			}
	}
}
