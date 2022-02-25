package algoStudy.a0212;

import java.io.*;
import java.util.*;

public class Main_bj_10163_색종이 {

	static int size=1001;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_10163"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		
		int[][] arr=new int[size][size];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			int h=Integer.parseInt(st.nextToken());

			for(int j=x; j<x+w; j++)
				for(int k=y; k<y+h; k++)
					arr[j][k]=i;
		}
		
		for(int c=1; c<=N; c++) {
			int cnt=0;
			for(int i=0; i<size; i++)
				for(int j=0; j<size; j++)
					if(arr[i][j]==c) cnt++;
			System.out.println(cnt);
		}
		
	}

}
