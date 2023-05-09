import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		
		int[] dp1 = new int[N];
		int[] dp2 = new int[N];
		Arrays.fill(dp1, 1);
		Arrays.fill(dp2, 1);
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<i; j++) {
				if(numbers[j] < numbers[i] && dp1[i] < dp1[j] + 1) {
					dp1[i] = dp1[j] + 1;
				}
			}
		}
		
		for(int i=N-1; i>=0; i--) {
			for(int j=N-1; j>i; j--) {
				if(numbers[j] < numbers[i] && dp2[i] < dp2[j]+1) {
					dp2[i] = dp2[j] + 1;
				}
			}
		}
		
		int ans = 0;
		for(int i=0; i<N; i++) {
			ans = Math.max(ans, dp1[i] + dp2[i]);
		}
		
		System.out.println(ans-1);
		br.close();
		
	}

}
