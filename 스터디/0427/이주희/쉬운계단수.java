import java.io.*;
import java.util.*;

public class Main_bj_10844_쉬운계단수 {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_bj_10844.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		long[][] dp = new long[N+1][10]; //N까지 자릿수를 고려, 0~9로 끝나는 수들의 갯수를 셈
		
		for(int i=1; i<=9; i++)
			dp[1][i] = 1;				// 1의 자리수 중에서 1~9으로 끝나는 수는 각각 1개씩
		
		for(int i=2; i<=N; i++) {
			
			for(int j=0; j<=9; j++) {
				if(j==0)
					dp[i][0] = dp[i-1][1];	// 끝의 자리가 0이 될 수 있는 경우는 1의 갯수에서 -1을 빼는 경우뿐이므로 같다.
				else if(j==9)
					dp[i][9] = dp[i-1][8];	// 9가 될 수 있는 경우는 8에서 1을 더한 경우밖에 없다
				else
					dp[i][j] = (dp[i-1][j-1]+dp[i-1][j+1])%1000000000; 	// 나머지 경우는 ex) 2는 1과 3에서 될 수 있기 때문에 두 갯수를 더한다.
			}
		}
		
		long ans=0;
		
		for(int i=0; i<=9; i++)
			ans += dp[N][i];
		
		System.out.println(ans%1000000000);
	}

}
