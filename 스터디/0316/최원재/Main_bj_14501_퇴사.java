package practice0310;
import java.io.*;
import java.util.*;
public class Main_bj_14501_퇴사 {
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] day = new int[N+1];
		int[] pay = new int[N+1];
		for(int i=1; i <=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			day[i]=Integer.parseInt(st.nextToken());
			pay[i]=Integer.parseInt(st.nextToken());
		}
		
		solve(day, pay, N, 1, 0);
		System.out.println(max);
		br.close();

	}
	static void solve(int[] day, int[] pay, int N, int cnt, int sum) {
		if(cnt==N+1) {
			if(sum>max) {
				max = sum;
				return;
			}
		}
		
		for(int i=cnt; i<=N; i++) {
			if(i+day[i]<=N+1) {
				solve(day, pay, N, i+day[i], sum+pay[i]);
			}
		}
		
		if(sum>max) {
			max = sum;
		}
	}

}
