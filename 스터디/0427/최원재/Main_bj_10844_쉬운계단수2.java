package p0424;
import java.io.*;
import java.util.*;
public class Main_bj_10844_쉬운계단수2 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
	    long[][] dp = new long[101][11];
	 
	    // dp[N][L] = dp[N - 1][L - 1] + dp[N - 1][L + 1]
	    // 길이 N, 마지막 숫자가 L일 경우
	    /*   1자리수  2자리수
	     * 0   0
	     * 1   1     
	     * 2   1
	     * 3   1
	     * 4   1
	     * 5   1
	     * 6   1
	     * 7   1
	     * 8   1
	     * 9   1
	     */
	 
	    for (int i = 1; i <= 9; i++) {
	        dp[1][i] = 1;
	    }
	 
	    for (int i = 2; i <= n; i++) {
	        dp[i][0] = dp[i - 1][1];
	        for (int j = 1; j <= 9; j++) {
	            dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
	        }
	    }
	 
	    long sum = 0;
	    for (int i = 0; i < 10; i++) {
	        sum += dp[n][i];
	    }
	    System.out.println(sum % 1000000000);



	}

}
