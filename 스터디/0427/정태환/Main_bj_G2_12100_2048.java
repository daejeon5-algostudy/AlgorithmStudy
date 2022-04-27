package algoStudy.a0427;

import java.io.*;
import java.util.*;

public class Main_bj_G2_12100_2048 {
	
	static int[] delx= {-1, 0, 1, 0};
	static int[] dely= { 0, 1, 0,-1};
	static int[][] map;
	static int ans, N;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_12100"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N=Integer.parseInt(br.readLine());
		
		map=new int[N][N];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) map[i][j]=Integer.parseInt(st.nextToken());
		}
		
		perm(0, new int[5]);
		
		System.out.println(ans);
		br.close();
	}

	static void perm(int cnt, int[] direct) {
		if(cnt==5) {
			int n=game2048(direct);
			ans=Math.max(ans, n);
			return;
		}
		
		for(int i=0; i<4; i++) {
			direct[cnt]=i;
			perm(cnt+1, direct);
		}
	}

	static int game2048(int[] direct) {
		int[][] cmap=new int[N][N];
		for(int i=0; i<N; i++) cmap[i]=map[i].clone();
		
		for(int c=0; c<5; c++) {
			int d=direct[c];
			if(d==0) { // 상
				for(int i=0; i<N; i++) move(cmap, 0, 0, i); // 한줄씩 이동
				for(int i=0; i<N; i++) calcMove(cmap, 0, 0, i); // 한줄씩 이동하며 계산
				for(int i=0; i<N; i++) move(cmap, 0, 0, i); // 한줄씩 이동
			}
			else if(d==1) { // 우
				for(int i=0; i<N; i++) move(cmap, 1, i, N-1); // 한줄씩 이동
				for(int i=0; i<N; i++) calcMove(cmap, 1, i, N-1); // 한줄씩 이동하며 계산
				for(int i=0; i<N; i++) move(cmap, 1, i, N-1); // 한줄씩 이동
			}
			else if(d==2) { // 하
				for(int i=0; i<N; i++) move(cmap, 2, N-1, i); // 한줄씩 이동
				for(int i=0; i<N; i++) calcMove(cmap, 2, N-1, i); // 한줄씩 이동하며 계산
				for(int i=0; i<N; i++) move(cmap, 2, N-1, i); // 한줄씩 이동
			}
			else if(d==3) { // 좌
				for(int i=0; i<N; i++) move(cmap, 3, i, 0); // 한줄씩 이동
				for(int i=0; i<N; i++) calcMove(cmap, 3, i, 0); // 한줄씩 이동하며 계산
				for(int i=0; i<N; i++) move(cmap, 3, i, 0); // 한줄씩 이동
			}
		}
		
		int result=0;
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				result=Math.max(result, cmap[i][j]);
		return result;
	}
	
	static void calcMove(int[][] cmap, int d, int sx, int sy) {
		while(true) {
			if(sx<0||sx>=N||sy<0||sy>=N) break;
			if(sx-delx[d]<0||sx-delx[d]>=N||sy-dely[d]<0||sy-dely[d]>=N) break;
			if(cmap[sx][sy]==cmap[sx-delx[d]][sy-dely[d]]) {
				cmap[sx][sy]*=2;
				cmap[sx-delx[d]][sy-dely[d]]=0;
				sx-=delx[d]*2;
				sy-=dely[d]*2;
			} else {
				sx-=delx[d];
				sy-=dely[d];
			}
		}
	}
	
	static void move(int[][] cmap, int d, int sx, int sy) {
		while(true) {
			if(sx<0||sx>=N||sy<0||sy>=N) return;
			if(cmap[sx][sy]!=0) {
				sx-=delx[d]; sy-=dely[d];
				continue;
			} else {
				int dx=sx, dy=sy;
				for(int i=0; i<N; i++) {
					dx-=delx[d]; dy-=dely[d];
					if(dx<0||dx>=N||dy<0||dy>=N) return;
					if(cmap[dx][dy]!=0) {
						cmap[sx][sy]=cmap[dx][dy];
						cmap[dx][dy]=0;
						sx-=delx[d]; sy-=dely[d];
						break;
					}
				}
			}
		}
	}
	
}
