package algoStudy.a0215;

import java.io.*;
import java.util.*;

public class Main_bj_S5_1476_날짜계산 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1476"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		int E = Integer.parseInt(token.nextToken()) - 1; // 지구 1 ~ 15
		int S = Integer.parseInt(token.nextToken()) - 1; // 태양 1 ~ 28
		int M = Integer.parseInt(token.nextToken()) - 1; // 달 1 ~ 19
		
		int i = 0;
		while(true) {
			if(i % 15 == E && i % 28 == S && i % 19 == M)
				break;
			i++;
		}
		
		System.out.println(i + 1);
	}

}
