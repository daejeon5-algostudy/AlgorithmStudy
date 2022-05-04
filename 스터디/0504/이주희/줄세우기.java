import java.io.*;
import java.util.*;


/*
 * 어린이들을 정렬하는 문제
 * 이미 제자리에 있는 어린이들은 위치를 바꿀 필요가 없음
 * LIS(Longest Increasing SubSequence)를 구해서(=제자리에 있는 어린이들) 전체 인원수에서 빼면 이동할 어린이의 수
 */

public class Main_bj_2631_줄세우기 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2631.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());			// 전체 인원 수
		int[] children = new int[N];
		
		for(int i=0; i<N; i++) {
			children[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[N];								// LIS
		int max=0;
		for(int i=0; i<N; i++) {
			dp[i] = 1;
			
			for(int j=0; j<i; j++) {
				if(children[j] < children[i]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(N-max);
		br.close();

	}

}
