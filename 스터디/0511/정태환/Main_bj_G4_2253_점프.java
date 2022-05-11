package algoStudy.astudy;

import java.io.*;
import java.util.*;

public class Main_bj_G4_2253_점프 {

	static int[][] memo=new int[10001][200]; // 0번위치와 1번속도일때의 이동횟수
	static boolean[] dont=new boolean[10001]; // 못가는곳
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2253"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		for(int i=0; i<M; i++) dont[Integer.parseInt(br.readLine())]=true;
		
		memo[1][0]=1;
		for(int i=1; i<=N; i++) {
			if(dont[i]) continue;
			for(int j=0; j<200; j++) {
				if(memo[i][j]!=0) {
					if(i+j<=N)
						if(memo[i+j][j]==0 || memo[i+j][j]>memo[i][j]+1)
							memo[i+j][j]=memo[i][j]+1;
					if(i+j+1<=N)
						if(memo[i+j+1][j+1]==0 || memo[i+j+1][j+1]>memo[i][j]+1)
							memo[i+j+1][j+1]=memo[i][j]+1;
					if(j-1>0 && i+j-1<=N)
						if(memo[i+j-1][j-1]==0 || memo[i+j-1][j-1]>memo[i][j]+1)
							memo[i+j-1][j-1]=memo[i][j]+1;
				}
			}
		}
		
		int result=Integer.MAX_VALUE;
		for(int i=0; i<200; i++) 
			if(memo[N][i]!=0) 
				result=Math.min(result, memo[N][i]);
		if(result==Integer.MAX_VALUE) result=0;
		System.out.println(result-1);
		br.close();
	}

}
