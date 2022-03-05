package algoStudy.a0304;

import java.io.*;
import java.util.*;

public class Main_bj_G5_21610_마법사상어와비바라기 {

	static int[][] map;
	static int[] delx=new int[] { 0,-1,-1,-1, 0, 1, 1, 1}; // 좌 좌상 상 우상 우 우하 하 좌하
	static int[] dely=new int[] {-1,-1, 0, 1, 1, 1, 0,-1}; // 좌 좌상 상 우상 우 우하 하 좌하
	static int N;
	static boolean[][] cloud;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_21610"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
		N=Integer.parseInt(st.nextToken()); // N*N 맵
		map=new int[N][N];
		int M=Integer.parseInt(st.nextToken()); // 구름이 M번 이동
		
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++)
				map[i][j]=Integer.parseInt(st.nextToken());
		}
		
		cloud=new boolean[N][N];
		cloud[N-1][0]=true; cloud[N-1][1]=true;
		cloud[N-2][0]=true; cloud[N-2][1]=true;
		
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int d=Integer.parseInt(st.nextToken())-1; // 이동방향
			int n=Integer.parseInt(st.nextToken()); // 이동칸
			rain(d,n);
		}
		
		int ans=0;
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				ans+=map[i][j];
		System.out.println(ans);
	}
	
	static void rain(int d, int n) {
		// d방향으로 n칸이동 (0번인덱스와 N-1번인덱스 연결되어있음)
		boolean[][] cloud2=new boolean[N][N];
		for(int x=0; x<N; x++) for(int y=0; y<N; y++)
				if(cloud[x][y]) {
					int dx=x+delx[d]*n, dy=y+dely[d]*n;
					if(dx<0)dx=(dx%N)+N; if(dy<0)dy=(dy%N)+N;
					if(dx>=N)dx%=N; if(dy>=N)dy%=N;
					cloud[x][y]=false; cloud2[dx][dy]=true;
				}
		for(int x=0; x<N; x++) for(int y=0; y<N; y++)
				cloud[x][y]=cloud2[x][y];
		
		// 구름있는칸에 물의양 1씩증가
		for(int x=0; x<N; x++) for(int y=0; y<N; y++)
				if(cloud[x][y]) 
					map[x][y]++;
		
		// 물이 증가한 칸에 물복사버그, 대각선방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 증가 (이동과 다르게 경계는 포함x)
		for(int x=0; x<N; x++) for(int y=0; y<N; y++)
				if(cloud[x][y]) {
					int cnt=0;
					for(int i=1; i<8; i=i+2) {
						int dx=x+delx[i], dy=y+dely[i];
						if(dx<0||dx>=N||dy<0||dy>=N) continue;
						if(map[dx][dy]!=0) cnt++;
					}
					map[x][y]+=cnt;
				}
		
		// 바구니에 물이 2이상 있는 모든 칸에 구름이 생김 (방금 구름이 사라진 칸은 제외)
		cloud2=new boolean[N][N];
		for(int x=0; x<N; x++) for(int y=0; y<N; y++)
			if(map[x][y]>=2 && !cloud[x][y]) {
				cloud2[x][y]=true;
				map[x][y]-=2;
			}
		for(int x=0; x<N; x++) for(int y=0; y<N; y++)
				cloud[x][y]=cloud2[x][y];
	}

}
