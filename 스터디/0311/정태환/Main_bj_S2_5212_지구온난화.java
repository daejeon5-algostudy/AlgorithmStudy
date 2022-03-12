package algoStudy.a0311;

import java.io.*;
import java.util.*;

public class Main_bj_S2_5212_지구온난화 {

	static int[] delx=new int[] {-1, 0, 1, 0}; // 상 우 하 좌
	static int[] dely=new int[] { 0, 1, 0,-1}; // 상 우 하 좌
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_5212"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		StringBuilder sb=new StringBuilder();
		
		int R=Integer.parseInt(st.nextToken()); // 행
		int C=Integer.parseInt(st.nextToken()); // 열
		String[][] map=new String[R][C];
		String[][] next=new String[R][C];
		
		for(int i=0; i<R; i++) { // 맵 입력
			String[] s=br.readLine().split("");
			for(int j=0; j<C; j++) {
				map[i][j]=s[j];
				next[i][j]=s[j];
			}
		}
		// 50년후
		for(int i=0; i<R; i++) { for(int j=0; j<C; j++) {
				if(map[i][j].equals("X")) {
					int cnt=0;
					for(int d=0; d<4; d++) {
						int dx=i+delx[d];
						int dy=j+dely[d];
						if(dx<0||dx>=R||dy<0||dy>=C) cnt++;
						else if(map[dx][dy].equals(".")) cnt++;
					}
					if(cnt>=3) next[i][j]=".";
				}
		}}

		// 맵 크기 조정
		int rs=0, re=R, cs=0, ce=C;
		// 앞쪽 행검사
		for(int i=0; i<R; i++) { for(int j=0; j<C; j++) {
				if(next[i][j].equals("X")) {
					re=i+1;
					break;
				}
				if(j==C-1) // 행이 전부 바다이면 맵크기 조절
					if(rs==i) rs++;
		}}
		// 열검사
		for(int i=0; i<C; i++) { for(int j=0; j<R; j++) {
				if(next[j][i].equals("X")) {
					ce=i+1;
					break;
				}
				if(j==R-1) // 열이 전부 바다이면 맵크기 조절
					if(cs==i) cs++;
		}}
		
		// 출력
		for(int i=rs; i<re; i++) {
			for(int j=cs; j<ce; j++)
				sb.append(next[i][j]);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
