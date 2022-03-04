package algoStudy.a0304;

import java.io.*;
import java.util.*;

public class Main_bj_S1_14891_톱니바퀴 {

	static int[][] gear;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14891"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		gear=new int[4][8]; // 톱니바퀴상태
		
		for(int i=0; i<4; i++) {
			String[] s=br.readLine().split("");
			for(int j=0; j<8; j++) gear[i][j]=Integer.parseInt(s[j]); // N극은 0, S극은 1
		}
		
		int K=Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int n=Integer.parseInt(st.nextToken())-1; // 톱니번호
			int d=Integer.parseInt(st.nextToken()); // 회전방향, 1:시계 -1:반시계
			
			boolean[] v=new boolean[4];
			rotate(n, d, v);
		}
		
		int ans=0; // 0번 인덱스가 0이면 점수추가, 1번:1, 2번:2, 3번:4, 4번:8
		// 점수계산
		for(int i=0; i<4; i++)
			if(gear[i][0] == 1) 
				ans+=Math.pow(2, i);
		
		System.out.println(ans);
	}
	
	static void rotate(int n, int d, boolean[] v) {
		if(n<0||n>=4) return;
		if(v[n]) return;
		v[n]=true;
		
		if(n-1>=0 && gear[n][6]!=gear[n-1][2]) rotate(n-1, -d, v);
		if(n+1<4 && gear[n][2]!=gear[n+1][6]) rotate(n+1, -d, v);
		
		if(d==1) {
			int tmp=gear[n][7];
			for(int i=0; i<7; i++)
				gear[n][7-i]=gear[n][7-i-1];
			gear[n][0]=tmp;
		} else {
			int tmp=gear[n][0];
			for(int i=0; i<7; i++)
				gear[n][i]=gear[n][i+1];
			gear[n][7]=tmp;
		}
	}

}
