package algoStudy.a0323;

import java.io.*;
import java.util.*;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Main_bj_G4_2573_빙산 {
	
	static int[][] map, save;
	static int[] delx=new int[] {-1, 0, 1, 0}; // 상우하좌
	static int[] dely=new int[] { 0, 1, 0,-1}; // 상우하좌
	static int N,M;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2573"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
		N=Integer.parseInt(st.nextToken()); // 행
		M=Integer.parseInt(st.nextToken()); // 열
		map=new int[N][M];
		
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++)
				map[i][j]=Integer.parseInt(st.nextToken());
		}
		
		int time=0;
		while(true) {
			// 덩어리 확인하면서 맵복사
			boolean[][] v=new boolean[N][M];
			int cnt=0;
			save=new int[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					save[i][j]=map[i][j];
					if(map[i][j]!=0&&!v[i][j]) {
						dfs(i,j,v);
						cnt++;
					}
				}
			}
			if(cnt==0) {
				System.out.println(0);
				break;
			}
			if(cnt>=2) {
				System.out.println(time);
				break;
			}
			time++;
			// 빙하녹기
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j]==0) continue;
					for(int d=0; d<4; d++) {
						int dx=i+delx[d];
						int dy=j+dely[d];
						if(dx<0||dx>=N||dy<0||dy>=M||map[dx][dy]!=0) continue;
						if(save[i][j]==0) break;
						save[i][j]--;
					}
				}
			}
			// 맵 옮기기
			map=save;
			
//			for(int[] ia:save) {
//				System.out.println(Arrays.toString(ia));
//			}
//			System.out.println();
		}
		
	}
	
	static void dfs(int x, int y, boolean[][] v) {
		v[x][y]=true;
		for(int d=0; d<4; d++) {
			int dx=x+delx[d];
			int dy=y+dely[d];
			if(v[dx][dy]||dx<0||dx>=N||dy<0||dy>=M||map[dx][dy]==0) continue;
			dfs(dx,dy,v);
		}
	}

}
