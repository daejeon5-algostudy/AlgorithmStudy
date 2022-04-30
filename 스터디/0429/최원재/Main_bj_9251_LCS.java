package p0428;
import java.io.*;
import java.util.*;
public class Main_bj_9251_LCS {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String A = br.readLine();
		String B = br.readLine();
		char[] Aarr = A.toCharArray(); //가로 
		char[] Barr = B.toCharArray(); // 세로 
		int[][] dp = new int[Barr.length+1][Aarr.length+1];
		for(int j=1; j<=Aarr.length; j++) {
			for(int i=1; i<=Barr.length; i++) {
				if(Aarr[j-1]==Barr[i-1]) {
					dp[i][j]=dp[i-1][j-1]+1;
				}else {
					dp[i][j]=Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		//for(int[] a:dp)System.out.println(Arrays.toString(a));
		System.out.println(dp[Barr.length][Aarr.length]);
		br.close();

	}

}
