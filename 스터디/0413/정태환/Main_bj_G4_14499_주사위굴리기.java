package algoStudy.a0413;

import java.io.*;
import java.util.*;

public class Main_bj_G4_14499_주사위굴리기 {
	
	static class Dice {
		int top, up, bottom, down, left, right;

		public Dice(int top, int up, int bottom, int down, int left, int right) {
			this.top = top;
			this.up = up;
			this.bottom = bottom;
			this.down = down;
			this.left = left;
			this.right = right;
		}
	}
	
	static int[] delx= { 0, 0,-1, 1}; // 동 서 북 남
	static int[] dely= { 1,-1, 0, 0};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14499"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");

		int N=Integer.parseInt(st.nextToken()); // 지도의 세로
		int M=Integer.parseInt(st.nextToken()); // 지도의 가로
		int x=Integer.parseInt(st.nextToken()); // 주사위 좌표
		int y=Integer.parseInt(st.nextToken()); // 주사위 좌표
		int K=Integer.parseInt(st.nextToken()); // 명령의 개수
		
		int[][] map=new int[N][M];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++)
				map[i][j]=Integer.parseInt(st.nextToken());
		}
		
		Dice now=new Dice(0, 0, 0, 0, 0, 0);
		st=new StringTokenizer(br.readLine()," ");
		for(int i=0; i<K; i++) {
			int commend=Integer.parseInt(st.nextToken())-1;
			int dx=x+delx[commend], dy=y+dely[commend];
			if(dx<0||dx>=N||dy<0||dy>=M) continue;
			x=dx; y=dy;
			
			switch(commend) {
				case 0: now=new Dice(now.left, now.up, now.right, now.down, now.bottom, now.top); break;
				case 1: now=new Dice(now.right, now.up, now.left, now.down, now.top, now.bottom); break;
				case 2: now=new Dice(now.down, now.top, now.up, now.bottom, now.left, now.right); break;
				case 3: now=new Dice(now.up, now.bottom, now.down, now.top, now.left, now.right); break;
			}
			
			if(map[x][y]==0) {
				map[x][y]=now.bottom;
				System.out.println(now.top);
			} else {
				now.bottom=map[x][y];
				map[x][y]=0;
				System.out.println(now.top);
			}
		}
		
		br.close();
	}
	
}
