package p0406;

import java.io.*;
import java.util.*;

public class Main_bj_2206_벽부수고이동하기_bfs2 {
	static class loc{
		int i;
		int j;
		int cnt;
		boolean isbreak;
		public loc(int i, int j, int cnt, boolean isbreak) {
			this.i = i;
			this.j = j;
			this.cnt=cnt;
			this.isbreak = isbreak;
		}
		
		
	}
	static int[] di = {-1,0,1,0};
	static int[] dj = {0,1,0,-1};
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j]=str.charAt(j)-'0';
			}
		}
		Queue<loc> q = new ArrayDeque<loc>();
		q.offer(new loc(0, 0, 1, false));
		boolean[][] breakv = new boolean[N][M];
		boolean[][] nobreakv = new boolean[N][M];
		nobreakv[0][0]=true;
		int result=0;
		while(!q.isEmpty()) {
			loc cur = q.poll();
			if(cur.i==N-1&&cur.j==M-1) {
				result=cur.cnt;
				break;
			}
			for(int d=0;d<4;d++) {
				int ni=cur.i+di[d];
				int nj=cur.j+dj[d];
				if(0<=ni&&ni<N&&0<=nj&&nj<M) {
					if(cur.isbreak) {
						if(map[ni][nj]==0&&!breakv[ni][nj]) {
							q.offer(new loc(ni, nj, cur.cnt+1, true));
							breakv[ni][nj]=true;
						}
					}else {
						if(map[ni][nj]==1&&!breakv[ni][nj]) {
							q.offer(new loc(ni, nj, cur.cnt+1, true));
							breakv[ni][nj]=true;
						}else if(map[ni][nj]==0&&!nobreakv[ni][nj]) {
							q.offer(new loc(ni, nj, cur.cnt+1, false));
							nobreakv[ni][nj]=true;
						}
					}
				}
				
			}
		}
		if(result==0)System.out.println(-1);
		else System.out.println(result);
		
		br.close();

	}
	
}