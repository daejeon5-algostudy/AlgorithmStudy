package p0408;
import java.io.*;
import java.util.*;

/*보드의 세로 크기는 N, 가로 크기는 M이고, 편의상 1×1크기의 칸으로 나누어져 있다. 
 * 가장 바깥 행과 열은 모두 막혀져 있고, 보드에는 구멍이 하나 있다
 * 
 */
public class Main_bj_13460_구슬탈출5 {
	static class ball{
		int redi;
		int redj;
		int bluei;
		int bluej;
		int cnt;
		public ball(int redi, int redj, int bluei, int bluej, int cnt) {
			super();
			this.redi = redi;
			this.redj = redj;
			this.bluei = bluei;
			this.bluej = bluej;
			this.cnt = cnt;
		}
		
	}
	static int[] di= {-1,0,1,0};
	static int[] dj= {0,1,0,-1};
		
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int ri=0,rj=0,bi=0,bj=0;
		char[][] map = new char[N][M];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j]=str.charAt(j);
				if(map[i][j]=='R') {
					ri=i;
					rj=j;
				}else if(map[i][j]=='B') {
					bi=i;
					bj=j;
				}
			}
		}
		boolean[][][][] v = new boolean[N][M][N][M];
		//for(char[] a: map)System.out.println(Arrays.toString(a));
		int result = solve(N, M, ri, rj, bi, bj,  map, v);
		 System.out.println(result);
		br.close();
	}
	static int solve(int N, int M, int ri, int rj, int bi, int bj, char[][] map, 
			boolean[][][][] v) {
		Queue<ball> que = new ArrayDeque<ball>();
		que.offer(new ball(ri, rj, bi, bj, 1));
		v[ri][rj][bi][bj]=true;
		while(!que.isEmpty()) {
			ball cur = que.poll();
			int curri = cur.redi;
			int currj = cur.redj;
			int curbi = cur.bluei;
			int curbj = cur.bluej;
			int count = cur.cnt;
			if(count>10)break;
			for(int d=0; d<4; d++) {
				//System.out.println(d);
				int nri = curri;
				int nrj = currj;
				int nbi = curbi;
				int nbj = curbj;
				boolean redhole=false;
				boolean bluehole=false;
				
				
				//빨간 공 #전까지 이동 
				while(true) {
					nri+=di[d];
					nrj+=dj[d];
					if(map[nri][nrj]=='#') {
						nri-=di[d];
						nrj-=dj[d];
						break;
					}
					if(map[nri][nrj]=='O') {
						redhole=true;
						break;
					}
					
				}
				// 파란 공 #전까지 이동 
				while(true) {
					nbi+=di[d];
					nbj+=dj[d];
					if(map[nbi][nbj]=='#') {
						nbi-=di[d];
						nbj-=dj[d];
						break;
					}
					if(map[nbi][nbj]=='O') {
						bluehole=true;
						break;
					}
				}
				if(bluehole)continue; 
				if(redhole) {
					return cur.cnt;
				
				}
				//둘의 위치가 같다.
				if(nri==nbi&&nrj==nbj) {
					if(d==0) {//위로 굴린 것 i가 큰것이 아래로 내려 갈 것 i+1
						if(curri>curbi) nri+=1;//레드가 원래 아래에 있었다.							
						else nbi+=1;
					}else if(d==1) {//우로 굴린것
						if(currj>curbj)nbj-=1; 
						else nrj-=1;
	
					}else if(d==2) {//아래로 굴린 것 
						if(curri>curbi)nbi-=1; //레드가 원래 아래에 있었다. 블루가 i-1							
						else nri-=1;
						
					}else { //좌로 굴린것
						if(currj>curbj) nrj+=1;
						else nbj+=1;
						
					}
				}
				if(!v[nri][nrj][nbi][nbj]) {
					v[nri][nrj][nbi][nbj]=true;
					que.offer(new ball(nri, nrj, nbi, nbj, count+1));
				}
			}
		}
		return -1;
	}
}


