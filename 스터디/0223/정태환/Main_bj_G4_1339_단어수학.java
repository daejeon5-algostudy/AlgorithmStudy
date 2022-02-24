package algoStudy.a0221;

import java.io.*;
import java.util.*;

public class Main_bj_G4_1339_단어수학 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1339"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine()); // 단어의 개수
		String[] sa = new String[N]; // 입력받은 배열
		for (int i = 0; i < N; i++)
			sa[i] = br.readLine();

		int[] alpha = new int[26];

		for (int i = 0; i < N; i++) {
			int tmp = (int) Math.pow(10, sa[i].length() - 1);
			for (int j = 0; j < sa[i].length(); j++) {
				alpha[(int) sa[i].charAt(j) - 65] += tmp;
				tmp /= 10;
			}
		}
//		System.out.println(Arrays.toString(alpha));

		Arrays.sort(alpha);

		int idx = 9;
		int sum = 0;
		for (int i = alpha.length - 1; i >= 0; i--) {
			if (alpha[i] == 0)
				break;
			sum += alpha[i] * idx;
			idx--;
		}
		System.out.println(sum);

	}

}
