package algoStudy.a0212;

import java.io.*;
import java.util.*;

public class Main_bj_14696_딱지놀이 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14696"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine()); // 총 라운드 수

		int[][] arr;
		for(int tc=0; tc<N; tc++) {
			arr=new int[2][5];
			for(int i=0; i<2; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int num=Integer.parseInt(st.nextToken());
				for(int j=0; j<num; j++) arr[i][Integer.parseInt(st.nextToken())]++;
			}
			
			for(int i=4; i>0; i--) {
				if(arr[0][i]!=arr[1][i]) {
					System.out.println(arr[0][i]>arr[1][i] ? "A":"B");
					break;
				}
				if(i==1) System.out.println("D");
			}
		}
	}
}
