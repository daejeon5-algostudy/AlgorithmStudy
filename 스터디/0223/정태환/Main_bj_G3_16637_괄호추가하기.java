package algoStudy.a0221;

import java.io.*;
import java.util.*;

public class Main_bj_G3_16637_괄호추가하기 {

	static int N;
	static int max = Integer.MIN_VALUE;
	static char[] arr;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_16637"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = br.readLine().toCharArray();

		cf(2, arr[0] - 48);
		System.out.println(max);
	}

	static void cf(int n, int value) {
		if (n >= N) {
			max = max > value ? max : value;
			return;
		}

		// 괄호 value ? (arr[n+2] ? arr[n+4]) ? .....
		if (n + 2 < N) {
			int sum1 = result(arr[n] - 48, arr[n + 2] - 48, arr[n + 1]);
			int sum2 = result(value, sum1, arr[n - 1]);
			cf(n + 4, sum2);
		}
		// 괄호 x value ? arr[n] ? ....
		cf(n + 2, result(value, arr[n] - 48, arr[n - 1]));
	}

	static int result(int v1, int v2, char c) {
		if (c == '+')
			return v1 + v2;
		if (c == '-')
			return v1 - v2;
		return v1 * v2;
	}

}
