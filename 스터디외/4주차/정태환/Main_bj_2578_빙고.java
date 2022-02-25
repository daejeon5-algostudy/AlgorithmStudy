package algoStudy.a0211;

import java.io.*;
import java.util.*;

public class Main_bj_2578_빙고 {

	static int[][] arr;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2578"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		arr=new int[5][5]; // 빙고 배열

		for(int i=0; i<5; i++) { // 빙고 배열입력
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) arr[i][j]=Integer.parseInt(st.nextToken());
		}
		
		int[] talk=new int[25];
		for(int i=0; i<5; i++) { // 사회자 배열입력
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) talk[i*5+j]=Integer.parseInt(st.nextToken());
		}
		
		int bingo=0; // 3이 되면 종료
		int idx=0; // 사회자가 부를 인덱스번호
		while(true) {
			if (bingo>=3) break;
			int num=talk[idx++];
	  label:for (int i=0; i<5; i++)
				for (int j=0; j<5; j++)
					if(arr[i][j]==num) {
						arr[i][j]=0;
						bingo=bingoCheck();
						break label;
					}
		}
		System.out.println(idx);
	}
	
	static int bingoCheck() {
		int cnt=0;
		
		for(int i=0; i<5; i++) { // 가로, 세로, 대각선 빙고체크
			int c1=0, c2=0, c3=0, c4=0;
			for(int j=0; j<5; j++) if(arr[i][j]==0) c1++;
			for(int j=0; j<5; j++) if(arr[j][i]==0) c2++;
			if(arr[i][i]==0) c3++;
			if(arr[4-i][i]==0) c4++;
			if(c1==5||c2==5||c3==5||c4==5) cnt++;
		}
		return cnt;
	}
}
