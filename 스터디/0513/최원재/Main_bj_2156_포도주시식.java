package p05;

import java.io.*;
import java.util.*;

public class Main_bj_2156_포도주시식 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int dp[] = new int[N];
		if (N == 1) {
			dp[0] = Integer.parseInt(br.readLine());
		} else if (N == 2) {
			dp[0] = Integer.parseInt(br.readLine());
			dp[1] = Integer.parseInt(br.readLine()) + dp[0];
		} else if (N == 3) {
			dp[0] = Integer.parseInt(br.readLine());
			dp[1] = Integer.parseInt(br.readLine()) + dp[0];
			int ex = Integer.parseInt(br.readLine());
			dp[2] = Math.max(dp[1], dp[1] - dp[0] + ex);
		} else {
			dp[0] = Integer.parseInt(br.readLine());
			dp[1] = Integer.parseInt(br.readLine()) + dp[0];
			int ex = Integer.parseInt(br.readLine());
			dp[2] = Math.max(dp[1], dp[1] - dp[0] + ex);
			dp[2] = Math.max(dp[2], dp[0]+ex);

			for (int i = 3; i < N; i++) {
				int input = Integer.parseInt(br.readLine());
				int result = 0;
				result = Math.max(dp[i - 3] + ex + input, dp[i - 2] + input);
				dp[i] = Math.max(result, dp[i - 1]);
				ex = input;

			}
		}
		System.out.println(dp[N - 1]);
		//System.out.println(Arrays.toString(dp));
		br.close();

	}

}
