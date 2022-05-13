import java.io.*;
import java.util.*;

public class Main_bj_2156_포도주시식 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2156.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] podo = new int[N+1];
		for(int i=1; i<=N; i++) {
			podo[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[N+1];		// i 번째 잔까지 고려했을 때 최대로 마실 수 있는 포도주의 양
		
		int ans = dp[1] = podo[1];
		
		if(N>1) {						// N이 1이면 dp[2]가 존재할 수 없다.
			dp[2] = podo[1] + podo[2];
			ans = Math.max(ans, dp[2]);
		}
		
		for(int i=3; i<=N; i++) {
			// o, o, x	-> dp[i-1]
			// o, x, o	-> dp[i-2] + podo[i]
			// x, o, o	-> dp[i-3] + podo[i-1] + podo[i];
			
			dp[i] = Math.max(dp[i-1], Math.max(dp[i-2]+podo[i], dp[i-3] + podo[i-1] + podo[i]));
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(ans);
		br.close();
	}

}
