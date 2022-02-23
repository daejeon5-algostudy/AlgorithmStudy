package a0221;

import java.io.*;
import java.util.*;

public class Main_G5_bj_1759_암호만들기_대전_5반_정태환 {

	static int L, C;
	static String[] alpha;
	static boolean[] visit;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1759"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		L = Integer.parseInt(st.nextToken()); // 암호의 크기
		C = Integer.parseInt(st.nextToken()); // 알파벳 종류개수

		alpha = new String[C];
		visit = new boolean[C];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < C; i++)
			alpha[i] = st.nextToken();
		
		Arrays.sort(alpha);
//		System.out.println(Arrays.toString(alpha));
		cf(0, 0);
		br.close();
	}
	
	static void cf(int start, int cnt) {
		if(cnt == L) {
			// 모음이 포함되어 있으면 출력 없으면 return
			String s = "";
			int vowel = 0; // 모음수
			int cons = 0; // 자음수
			for(int i=0; i<C; i++) {
				if(visit[i]) {
					s+=alpha[i];
					if(alpha[i].equals("a") || alpha[i].equals("e") || alpha[i].equals("i") || alpha[i].equals("o") || alpha[i].equals("u")) 
						vowel++;
					else
						cons++;
				}
			}
			if(vowel > 0 && cons > 1)
				System.out.println(s);
			
			return;
		}
		if(start == C) return;
		
		visit[start] = true;
		cf(start+1, cnt+1);
		visit[start] = false;
		cf(start+1, cnt);
	}

}
