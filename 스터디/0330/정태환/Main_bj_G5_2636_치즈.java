package algoStudy.a0330;

import java.io.*;
import java.util.*;

public class Main_bj_G5_2636_치즈 {

	static int[] delx=new int[] {-1, 0, 1, 0}; // 상우하좌
	static int[] dely=new int[] { 0, 1, 0,-1}; // 상우하좌
	static int[][] map;
	static int r,c;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2636"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		map=new int[r][c];
		
		for(int i=0; i<r; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<c; j++)
				map[i][j]=Integer.parseInt(st.nextToken());
		}
		
		int time=0;
		while(true) {
			time++;
			
			// 테두리에서 탐색하며 치즈와 닿는부분 체킹
			boolean[][] cheese=new boolean[r][c];
			int i=0;
			for(int j=0; j<c; j++)
				if(!cheese[i][j]) {
					if(map[i][j]==1) {
						cheese[i][j]=true;
						continue;
					}
					dfs(cheese, i,j);
				}
			i=r-1;
			for(int j=0; j<c; j++)
				if(!cheese[i][j]) {
					if(map[i][j]==1) {
						cheese[i][j]=true;
						continue;
					}
					dfs(cheese, i,j);
				}
			int j=0;
			for(i=0; i<r; i++)
				if(!cheese[i][j]) {
					if(map[i][j]==1) {
						cheese[i][j]=true;
						continue;
					}
					dfs(cheese, i,j);
				}
			j=c-1;
			for(i=0; i<r; i++)
				if(!cheese[i][j]) {
					if(map[i][j]==1) {
						cheese[i][j]=true;
						continue;
					}
					dfs(cheese, i,j);
				}
			
			int savecnt=0; // 녹기전 개수 세기
			// 닿는부분 녹이기
			for(i=0; i<r; i++)
				for(j=0; j<c; j++)
					if(cheese[i][j] && map[i][j]==1) {
						savecnt++;
						map[i][j]=0;
					}
			
			
			// 다 녹았는지 체크, 다 녹았으면 시간과 이전 치즈수 출력후 종료
			int cnt=0;
			for(i=0; i<r; i++)
				for(j=0; j<c; j++)
					if(map[i][j]==1) cnt++;
			
			if(cnt==0) {
				System.out.println(time);
				System.out.println(savecnt);
				break;
			}
		}
	}

	static void dfs(boolean[][] cheese, int i, int j) {
		cheese[i][j]=true;
		for(int d=0; d<4; d++) {
			int dx=i+delx[d];
			int dy=j+dely[d];
			if(dx<0||dx>=r||dy<0||dy>=c||cheese[dx][dy]) continue;
			if(map[dx][dy]==1) {
				cheese[dx][dy]=true;
				continue;
			}
			dfs(cheese,dx,dy);
		}
	}

}
