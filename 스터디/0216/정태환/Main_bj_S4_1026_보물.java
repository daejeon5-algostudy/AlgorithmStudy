package algoStudy.a0215;

import java.io.*;
import java.util.*;

public class Main_bj_S4_1026_보물 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1026"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr1 = new int[N];
		int[] arr2 = new int[N];

		StringTokenizer token1 = new StringTokenizer(br.readLine());
		StringTokenizer token2 = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr1[i] = Integer.parseInt(token1.nextToken());
			arr2[i] = Integer.parseInt(token2.nextToken());
		}
		Arrays.sort(arr1);
		Arrays.sort(arr2);

		int sum = 0;
		for (int i = 0; i < N; i++)
			sum += arr1[i] * arr2[N - 1 - i];
		
		System.out.println(sum);

	}

}
