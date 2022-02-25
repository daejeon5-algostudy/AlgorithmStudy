package algoStudy.a0212;

import java.io.*;
import java.util.*;

public class Main_bj_2635_수이어가기 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2635"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		
		int cnt = 1; // 최대개수
		int val = 0; // 최대개수일때의 첫번째값
		int tVal = N;
		while(tVal > 0) {
			int tmpN=N, tmpC=2, nextN = tVal;
			while(true) {
				int tmp=nextN;
				nextN=tmpN-nextN;
				if(nextN<0) break;
				tmpN=tmp;
				tmpC++;
			}
			if(cnt<tmpC) {
				cnt=tmpC;
				val=tVal;
			}
			tVal--;
		}
		System.out.println(cnt);
		for(int i=0; N>=0; i++) {
			System.out.print(N + " ");
			val=N-val;
			N-=val;
		}
	}
}
