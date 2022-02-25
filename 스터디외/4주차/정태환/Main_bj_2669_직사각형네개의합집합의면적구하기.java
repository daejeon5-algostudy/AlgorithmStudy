package algoStudy.a0212;

import java.io.*;
import java.util.*;

public class Main_bj_2669_직사각형네개의합집합의면적구하기 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2669"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		boolean[][] map=new boolean[101][101]; // x좌표와 y좌표는 1이상 100이하, 0번인덱스 사용안함
		
		for(int i=0; i<4; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int x1=Integer.parseInt(st.nextToken());
			int y1=Integer.parseInt(st.nextToken());
			int x2=Integer.parseInt(st.nextToken());
			int y2=Integer.parseInt(st.nextToken());
			for(int j=x1; j<x2; j++)
				for (int k=y1; k<y2; k++) 
					map[j][k]=true;
		}
		int cnt=0;
		for(int i=1; i<map.length; i++)
			for(int j=1; j<map.length; j++)
				if(map[i][j]) cnt++;
		System.out.println(cnt);
		br.close();
	}
}
