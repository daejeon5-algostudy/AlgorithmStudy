package p0401;
import java.io.*;
import java.util.*;

public class Main_bj_19238_스타트택시 {
	static class loc implements Comparable<loc>{
		int i;
		int j;
		int dis;
		public loc(int i, int j, int dis) {
			super();
			this.i = i;
			this.j = j;
			this.dis = dis;
		}
		@Override
		public int compareTo(loc o) {
			int r = this.dis-o.dis;
			if(r==0)r=this.i-o.i;
			if(r==0)r=this.j-o.j;
			return r;
		}
	}
	static int di[] = {-1,0,1,0};
	static int dj[] = {0,1,0,-1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] dest = new int[M+2][2];
		int fuel = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
		
				map[i][j]=Integer.parseInt(st.nextToken());
				
			}
		}
		st = new StringTokenizer(br.readLine()," ");
		int taxi = Integer.parseInt(st.nextToken())-1;
		int taxj = Integer.parseInt(st.nextToken())-1;
		for(int i=0; i<M; i++) {
			st =new StringTokenizer(br.readLine()," ");
			int si= Integer.parseInt(st.nextToken())-1;
			int sj= Integer.parseInt(st.nextToken())-1;
			int di= Integer.parseInt(st.nextToken())-1;
			int dj= Integer.parseInt(st.nextToken())-1;
			map[si][sj]=i+2;
			dest[i+2][0]=di;
			dest[i+2][1]=dj;
			
		}
		boolean check = true;
		for(int i=0; i<M; i++) {
			loc res = findstart(taxi, taxj, map, new boolean[N][N], N);
			if(res==null) {
				check =false;
				break;
			}
			taxi = res.i;
			taxj=res.j;
			int targeti=dest[map[res.i][res.j]][0];
			int targetj=dest[map[res.i][res.j]][1];
			map[taxi][taxj]=0;
			fuel-=res.dis;
			if(fuel<0) {
				check= false;
				break;
			}
		
			
			res = findend(taxi, taxj, map, new boolean[N][N], N, targeti, targetj);
			if(res==null) {
				check= false;
				break;
			}
			fuel-=res.dis;
			if(fuel<0) {
				check= false;
				break;
			}
			fuel+=res.dis*2;

			taxi = res.i;
			taxj=res.j;

		}
		if(check)System.out.println(fuel);
		else System.out.println(-1);
		br.close();

	}
	static loc findstart(int i, int j, int[][] map, boolean[][] v, int N) {
		int min = Integer.MAX_VALUE;
		PriorityQueue<loc> pq = new PriorityQueue<>();
		Queue<loc> que = new ArrayDeque<loc>();
		que.add(new loc(i, j,  0));
		v[i][j]=true;
		while(!que.isEmpty()) {
			loc cur = que.poll();

			if(map[cur.i][cur.j]>1) {//도착지 발견!
				if(cur.dis<=min)pq.add(cur);
			}else {//그냥 빈 칸 
				for(int d=0; d<4; d++) {
					int ni= cur.i+di[d];
					int nj= cur.j+dj[d];
					if(0<=ni&&ni<N&& 0<=nj&& nj<N&&map[ni][nj]!=1) {
						if(!v[ni][nj]) {
							que.add(new loc(ni, nj, cur.dis+1));
							v[ni][nj]=true;
						}
					}
				}
				
			}
		}		 
		return pq.poll();
	}
	
	static loc findend(int i, int j, int[][] map, boolean[][] v, int N,  int ti, int tj) {

		Queue<loc> que = new ArrayDeque<loc>();
		que.add(new loc(i, j, 0));
		v[i][j]=true;
		while(!que.isEmpty()) {
			loc cur = que.poll();
			if(cur.i==ti&& cur.j==tj) {
				return cur;
			}else {
				for(int d=0; d<4; d++) {
					int ni= cur.i+di[d];
					int nj= cur.j+dj[d];
					if(0<=ni&&ni<N&& 0<=nj&& nj<N&&map[ni][nj]!=1) {
						if(!v[ni][nj]) {
							que.add(new loc(ni, nj, cur.dis+1));
							v[ni][nj]=true;
						}
					}
				}
			}
		}
		return null;
	}

}
