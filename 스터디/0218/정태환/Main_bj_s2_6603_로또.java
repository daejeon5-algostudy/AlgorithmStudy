package algoStudy.a0217;

import java.io.*;
import java.util.*;

public class Main_bj_s2_6603_로또 {

	static int N;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_6603"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			String s = br.readLine();
			if (s.equals("0"))
				break;
			st = new StringTokenizer(s, " ");

			N = Integer.parseInt(st.nextToken());
			arr = new int[N];
			for (int i = 0; i < N; i++)
				arr[i] = Integer.parseInt(st.nextToken());

			cf(0, 0, new int[6]);
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void cf(int start, int count, int[] visit) {
		if (count == 6) {
			for(int i=0; i<6; i++)
				sb.append(visit[i]).append(" ");
			sb.append("\n");
			return;
		}
		for (int i = start; i < N; i++) {
			visit[count] = arr[i];
			cf(i + 1, count + 1, visit);
		}
	}
}
