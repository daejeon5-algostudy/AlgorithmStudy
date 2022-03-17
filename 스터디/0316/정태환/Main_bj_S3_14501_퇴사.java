package algoStudy.a0316;

import java.io.*;
import java.util.*;

public class Main_bj_S3_14501_퇴사 {

	static int[][] dp=new int[16][6]; // 현재일자, 남은일자, 저장된값:수익
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14501"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N=Integer.parseInt(br.readLine());
		
		for(int i=1; i<=N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int day=Integer.parseInt(st.nextToken());
			int money=Integer.parseInt(st.nextToken());
			
			// 전체 일자 감소
			dp[i][0]=Math.max(dp[i-1][0], dp[i-1][1]);
			for(int j=1; j<=4; j++)
				dp[i][j]=dp[i-1][j+1];
			
			// 현재 일자 포함하여 dp 메모이제이션
			int next=dp[i][0]+money;
			dp[i][day]=Math.max(dp[i][day], dp[i][0]+money);
			
			
		}
//		System.out.println(Arrays.toString(dp[7]));
		System.out.println(Math.max(dp[N][0], dp[N][1]));
	}

}
