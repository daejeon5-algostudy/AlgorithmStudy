package algoStudy.a0217;

import java.io.*;
import java.util.*;

public class Main_bj_s2_10819_차이를최대로 {

	static int N, max = Integer.MIN_VALUE;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_10819"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine(), " ");
		arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		cf(0, new int[N], new boolean[N]);
		
		System.out.println(max);
		br.close();
	}

	static void cf(int count, int[] tarr, boolean[] visit) {
		if (count == N) {
			// 계산후 갱신
			int sum = 0;
			for (int i = 0; i < N - 1; i++)
				sum += Math.abs(tarr[i] - tarr[i+1]);
			max = Math.max(max, sum);
		}

		for (int i = 0; i < N; i++) {
			if (visit[i] == false) {
				visit[i] = true;
				tarr[count] = arr[i];
				cf(count + 1, tarr, visit);
				visit[i] = false;
			}
		}
	}

}
