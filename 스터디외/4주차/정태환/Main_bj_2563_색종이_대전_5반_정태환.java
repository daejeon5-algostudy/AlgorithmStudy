package a0210;

import java.io.*;
import java.util.*;

public class Main_bj_2563_색종이_대전_5반_정태환 {

	static boolean[][] map=new boolean[101][101];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2563"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine()); // 색종이의 숫자
		
		for(int i=0; i<N; i++) {// 색종이 붙이기
			StringTokenizer st=new StringTokenizer(br.readLine(), " ");
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());

			for(int j=a; j<a+10; j++)
				for(int k=b; k<b+10; k++)
					map[j][k]=true;
		}

		int res=0;
		for(int i=0; i<100; i++)
			for(int j=0; j<100; j++)
				if(map[i][j]) res++;

		System.out.println(res);
	}

}
