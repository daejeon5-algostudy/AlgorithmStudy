package p05;
import java.io.*;
import java.util.*;
public class Main_bj_5557_1학년 {

	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[][] dp = new long[N][21];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		dp[0][Integer.parseInt(st.nextToken())]=1;
		for(int i=1; i<N-1;i++) {
			int input = Integer.parseInt(st.nextToken());
			for(int j=0; j<=20;j++) {
				if(j+input<=20) {
					dp[i][j+input]+=dp[i-1][j];
				}
				if(j-input>=0) {
					dp[i][j-input]+=dp[i-1][j];
				}
			}
		}
		System.out.println(dp[N-2][Integer.parseInt(st.nextToken())]);
		br.close();

	}

}
/*
 * 8   3      2    4     8   7   2   4    0    8  =   8
 *          3-1    3-1
 *                 7-1
 *                  
 *    5-1   
 *          7-1            dp[9][8]
 * 8-1      9-1  
 * 
 *    11-1 
 *    
 *          13-1
 */
