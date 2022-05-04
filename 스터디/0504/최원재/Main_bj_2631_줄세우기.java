package p05;
import java.io.*;
import java.util.*;
//0504 스터디 
public class Main_bj_2631_줄세우기 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N];
		dp[0]=Integer.parseInt(br.readLine());
		int last=0;
		for(int i=1; i<N; i++) {
			int n=Integer.parseInt(br.readLine());
			for(int j=0; j<N; j++) {
				if(dp[j]==0) {
					dp[j]=n;
					last=j;
					break;
				}else {
					if(dp[j]>n) {
						dp[j]=n;
						break;
					}
				}
			}
		}
//		System.out.println(Arrays.toString(dp));
//		System.out.println(last);
		System.out.println(N-last-1);
		
		
		
		br.close();

	}

}
