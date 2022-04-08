package p0408;
import java.io.*;
import java.util.*;

public class Main_bj_14503_로봇청소기 {
	static class robot{
		int i;
		int j;
		int pos;
		int a2cnt;
		public robot(int i, int j, int pos, int a2cnt) {
			this.i = i;
			this.j = j;
			this.pos = pos;
			this.a2cnt=a2cnt;
		}
	}
	static int[] di= {-1,0,1,0};//상우하좌
	static int[] dj= {0,1,0,-1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st=new StringTokenizer(br.readLine()," ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		robot ro = new robot(r, c, d, 0);
		boolean[][] v = new boolean[N][M];
		int cnt=1;
		v[ro.i][ro.j]=true;
		//System.out.println(ro.pos);
		while(true) {
			//System.out.println(ro.pos+ " "+ro.a2cnt);
			if(ro.a2cnt==4) {
				int a= (ro.pos+2)%4;
				int ni = ro.i+di[a];
				int nj = ro.j + dj[a];
				if(0<=ni&&ni<N&&0<=nj&&nj<M && map[ni][nj]==0){
					ro.i=ni;
					ro.j=nj;
					ro.a2cnt=0;
				}else {
					break;
				}
			}

			if(!v[ro.i][ro.j])cnt++;
			v[ro.i][ro.j]=true;
			//0: 상   1: 우   2: 하     3: 좌
			if(ro.pos==0) { //상 -->좌
				int ni = ro.i+di[3];
				int nj= ro.j+dj[3];
				if(0<=ni&&ni<N&&0<=nj&&nj<M) {
					if(map[ni][nj]==0&&!v[ni][nj]) {
						//System.out.println("d");
						ro.i=ni;
						ro.j=nj;
						ro.pos=3;
						ro.a2cnt=0;
					}else {
						//System.out.println("s");
						ro.pos=3;
						ro.a2cnt++;
					}
				}else {
					//System.out.println();
					ro.pos=3;
					ro.a2cnt++;
				}
			}else if(ro.pos==1) { // 우 --> 상
				int ni = ro.i+di[0];
				int nj= ro.j+dj[0];
				if(0<=ni&&ni<N&&0<=nj&&nj<M) {
					if(map[ni][nj]==0&&!v[ni][nj]) {
						ro.i=ni;
						ro.j=nj;
						ro.pos=0;
						ro.a2cnt=0;
					}else {
						ro.pos=0;
						ro.a2cnt++;
					}
				}else {
					ro.pos=0;
					ro.a2cnt++;
				}
			}else if(ro.pos==2) { // 하 -> 우
				int ni = ro.i+di[1];
				int nj= ro.j+dj[1];
				if(0<=ni&&ni<N&&0<=nj&&nj<M) {
					if(map[ni][nj]==0&&!v[ni][nj]) {
						ro.i=ni;
						ro.j=nj;
						ro.pos=1;
						ro.a2cnt=0;
					}else {
						ro.pos=1;
						ro.a2cnt++;
					}
				}else {
					ro.pos=1;
					ro.a2cnt++;
				}
			}else if(ro.pos==3) { //좌 -->하
				int ni = ro.i+di[2];
				int nj= ro.j+dj[2];
				if(0<=ni&&ni<N&&0<=nj&&nj<M) {
					if(map[ni][nj]==0&&!v[ni][nj]) {
						ro.i=ni;
						ro.j=nj;
						ro.pos=2;
						ro.a2cnt=0;
					}else {
						ro.pos=2;
						ro.a2cnt++;
					}
				}else {
					ro.pos=2;
					ro.a2cnt++;
				}
			}
		}
		System.out.println(cnt);
		
		br.close();

	}

}
