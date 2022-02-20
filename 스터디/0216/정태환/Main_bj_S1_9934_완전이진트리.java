package algoStudy.a0215;

import java.io.*;
import java.util.*;

public class Main_bj_S1_9934_완전이진트리 {

	static int K;
	static int[] arr;
	static ArrayList<Integer>[] ans;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_9934"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine()); // 트리의 깊이
		int num = (int) Math.pow(2, K) - 1;

		StringTokenizer token = new StringTokenizer(br.readLine(), " "); // 상근이가 방문한 빌딩의 번호를 순서대로 입력받음
		arr = new int[num];
		for (int i = 0; i < num; i++) arr[i] = Integer.parseInt(token.nextToken());

		ans = new ArrayList[K];
		for (int i = 0; i < K; i++) ans[i] = new ArrayList<>();
		
		cf(0, 0, num);

		StringBuilder sb = new StringBuilder();
		for (ArrayList<Integer> ia : ans) {
			for (int a : ia)
				sb.append(a).append(" ");
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void cf(int k, int start, int end) {
		if (k >= K)
			return;

		int mid = (start + end) / 2;
		ans[k].add(arr[mid]);
		cf(k + 1, start, mid - 1);
		cf(k + 1, mid + 1, end);

	}

}
