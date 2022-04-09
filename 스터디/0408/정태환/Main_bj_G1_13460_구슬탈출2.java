package algoStudy.a0408;

import java.io.*;
import java.util.*;

public class Main_bj_G1_13460_구슬탈출2 {
	static class Status {
		int rx, ry, bx, by, time;

		public Status(int rx, int ry, int bx, int by, int time) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.time = time;
		}
	}
	
	static int[] delx= {-1, 0, 1, 0}; // 상우하좌
	static int[] dely= { 0, 1, 0,-1}; // 상우하좌
	static int N, M, targetx, targety, ans;
	static char[][] map;
	static boolean[][][][] v;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_13460"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
		N=Integer.parseInt(st.nextToken()); // 세로
		M=Integer.parseInt(st.nextToken()); // 가로
		map=new char[N][M];
		for(int i=0; i<N; i++)
			map[i]=br.readLine().toCharArray();
		
		int rx=0,ry=0,bx=0,by=0;
		for(int i=0; i<N; i++)
			for(int j=0; j<M; j++)
				if(map[i][j]=='R') {
					rx=i; ry=j;
				} else if(map[i][j]=='B') {
					bx=i; by=j;
				} else if(map[i][j]=='O') {
					targetx=i; targety=j;
				}
		
		v=new boolean[N][M][N][M];
		Status s=new Status(rx, ry, bx, by, 0);
		System.out.println(bfs(s));
		br.close();
	}

	static int bfs(Status s) {
		ArrayDeque<Status> q=new ArrayDeque<>();
		q.offer(s);
		v[s.rx][s.ry][s.bx][s.by]=true;
		
		while(!q.isEmpty()) {
			Status now=q.poll();
			int rx=now.rx, ry=now.ry, bx=now.bx, by=now.by, time=now.time;
			
			if(time==10) return -1;
			for(int d=0; d<4; d++) {
				int drx=rx, dry=ry;
				int dbx=bx, dby=by;
				
				boolean rchk=false, bchk=false;
				
				while(true) {
					if(map[drx+delx[d]][dry+dely[d]]=='#') break;
					drx+=delx[d]; dry+=dely[d];
					if(drx==targetx && dry==targety) {
						rchk=true;
						break;
					}
				}
				while(true) {
					if(map[dbx+delx[d]][dby+dely[d]]=='#') break;
					dbx+=delx[d]; dby+=dely[d];
					if(dbx==targetx && dby==targety) {
						bchk=true;
						break;
					}
				}
				if(rchk&&!bchk) return time+1;
				if(bchk) continue;
				
				if(drx==dbx && dry==dby) {
					if(d==0) // 상
						if(rx>bx) drx-=delx[d];
						else dbx-=delx[d];
					else if(d==1) // 우
						if(ry<by) dry-=dely[d];
						else dby-=dely[d];
					else if(d==2) // 하
						if(rx<bx) drx-=delx[d];
						else dbx-=delx[d];
					else if(d==3) // 좌
						if(ry>by) dry-=dely[d];
						else dby-=dely[d];
				}
				
				if(!v[drx][dry][dbx][dby]) {
					v[drx][dry][dbx][dby]=true;
					q.offer(new Status(drx, dry, dbx, dby, time+1));
				}
			}
		}
		return -1;
	}
}
