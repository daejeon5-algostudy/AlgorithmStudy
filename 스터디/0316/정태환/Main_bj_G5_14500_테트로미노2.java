package algoStudy.a0316;

import java.io.*;
import java.util.*;

public class Main_bj_G5_14500_테트로미노2 {

	static int[] delx=new int[] {-1, 0, 1, 0}; // 상 우 하 좌
	static int[] dely=new int[] { 0, 1, 0,-1}; // 상 우 하 좌
	static int[][] map;
	static boolean[][] v;
	static int N, M, max=Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14500"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
		N=Integer.parseInt(st.nextToken()); // 세로
		M=Integer.parseInt(st.nextToken()); // 가로
		map=new int[N][M];
		v=new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) map[i][j]=Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++) {// 각 좌표별 dfs
				v[i][j]=true;
				dfs(i,j,0,map[i][j]);
				v[i][j]=false;
			}
		
		System.out.println(max);
	}
	
	static void dfs(int x, int y, int cnt, int sum) {
		if(cnt==3) {
			max=Math.max(max, sum);
			return;
		}
		
		for(int i=0; i<4; i++) {
			int dx=x+delx[i];
			int dy=y+dely[i];
			if(dx<0||dx>=N||dy<0||dy>=M||v[dx][dy]) continue;
			v[dx][dy]=true;
			dfs(dx, dy, cnt+1, sum+map[dx][dy]);
			v[dx][dy]=false;
		}
		
		// ㅗ ㅏ ㅜ ㅓ 검사
		if(cnt==0) {
			int sum2=0;
			// ㅗ
			if(x>0 && x<N && y+2<M) {
				sum2=map[x][y]+map[x][y+1]+map[x][y+2]+map[x-1][y+1];
				max=Math.max(sum2, max);
			}
			// ㅏ
			if(x+2<N && y+1<M) {
				sum2=map[x][y]+map[x+1][y]+map[x+2][y]+map[x+1][y+1];
				max=Math.max(sum2, max);
			}
			// ㅜ
			if(x+1<N && y+2<M) {
				sum2=map[x][y]+map[x][y+1]+map[x][y+2]+map[x+1][y+1];
				max=Math.max(sum2, max);
			}
			// ㅓ
			if(x>0 && x+1<N && y+1<M) {
				sum2=map[x][y]+map[x-1][y+1]+map[x][y+1]+map[x+1][y+1];
				max=Math.max(sum2, max);
			}
		}
	}
}
