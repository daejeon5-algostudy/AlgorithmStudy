package algoStudy.a0228;

import java.io.*;
import java.util.*;

public class Main_bj_G5_20055_컨베이어벨트위의로봇 {

	static int N,K,ans,zero;
	static int[][] belt;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_20055"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
		N=Integer.parseInt(st.nextToken()); // 벨트의 길이
		K=Integer.parseInt(st.nextToken()); // 내구도가 0인 칸의 개수가 K개 이상이라면 종료

		belt=new int[N*2+1][2]; // 0:내구도, 1:로봇올리면1, 내리면0
		
		st=new StringTokenizer(br.readLine()," ");
		for(int i=1; i<=N*2; i++) belt[i][0]=Integer.parseInt(st.nextToken()); // 내구도입력

		while(true) {
			ans++; // 단계증가
			// 1:벨트의회전
			int[] tmp=new int[] {belt[N*2][0],belt[N*2][1]};
			for(int i=0; i<2*N; i++)
				for(int j=0; j<2; j++)
					belt[N*2-i][j]=belt[N*2-1-i][j];
			belt[1][0]=tmp[0]; belt[1][1]=tmp[1];
			belt[N][1]=0;// 로봇내리기
			
			// 2:로봇의이동
			for(int i=N-1; i>1; i--)
				if(belt[i][1]==1 && belt[i+1][0]!=0 && belt[i+1][1]==0) {
					belt[i][1]=0; // 현재칸 로봇이동
					belt[i+1][0]-=1; // 이동칸 내구도감소
					if(belt[i+1][0]==0) zero++; // 내구도 0인칸 세기
					belt[i+1][1]=1; // 로봇이동완료
				}
			belt[N][1]=0;// 로봇내리기
			
			// 3:올리는 위치에 로봇올리기
			if(belt[1][0]!=0) {
				belt[1][0]-=1; // 올리는칸 내구도감소
				if(belt[1][0]==0) zero++; // 내구도 0인칸 세기
				belt[1][1]=1; // 로봇올리기 완료
			}
			
			// 4:내구도 0인칸의 개수가 K개 이상이면 종료
			if(zero>=K) break;
		}
		System.out.println(ans);
	}
}
