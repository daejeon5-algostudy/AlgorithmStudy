package algoStudy.a0228;

import java.io.*;
import java.util.*;

public class Main_bj_G2_19236_청소년상어 {

	static int[] delx=new int[] {-1,-1, 0, 1, 1, 1, 0,-1}; // 상 좌상 좌 좌하 하 우하 우 우상
	static int[] dely=new int[] { 0,-1,-1,-1, 0, 1, 1, 1}; // 상 좌상 좌 좌하 하 우하 우 우상
	static int ans;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_19236"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int[][][] map=new int[4][4][2];
		for(int i=0; i<4; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<4; j++) {
				int num=Integer.parseInt(st.nextToken()), d=Integer.parseInt(st.nextToken())-1;
				map[i][j][0]=num; map[i][j][1]=d;
			}
		}
		ans=map[0][0][0]; map[0][0][0]=17;
		dfs(ans, map);
		System.out.println(ans);
	}
	
	static void dfs(int sum, int[][][] map) {
		// 맵복사
		int[][][] cmap=new int[4][4][2];
		for(int i=0; i<4; i++) for(int j=0; j<4; j++) 
			for(int k=0; k<2; k++) cmap[i][j][k]=map[i][j][k]; 
		
		// 생선이동
		label:for(int i=1; i<=16; i++) { 
			for(int x=0; x<4; x++) { for(int y=0; y<4; y++) { // i번째 생선위치찾기
					if(cmap[x][y][0]==i) {
						for(int d=0; d<8; d++) {
							int dx=x+delx[cmap[x][y][1]], dy=y+dely[cmap[x][y][1]];
							if(dx<0||dx>=4||dy<0||dy>=4||cmap[dx][dy][0]==17) { // 맵밖이거나 상어일경우 방향변경
								cmap[x][y][1]=(cmap[x][y][1]+1)%8;
								continue;
							}
							if(cmap[dx][dy][0]==0) { // 비어있으면 생선이동
								cmap[dx][dy][0]=cmap[x][y][0]; cmap[dx][dy][1]=cmap[x][y][1];
								cmap[x][y][0]=0; cmap[x][y][1]=0;
							} else { // x,y와 dx,dy의 생선 위치 변경
								int tn=cmap[dx][dy][0], td=cmap[dx][dy][1];
								cmap[dx][dy][0]=i; cmap[dx][dy][1]=cmap[x][y][1];
								cmap[x][y][0]=tn; cmap[x][y][1]=td;
							}
							continue label;
						}
					}
			}}
		}
		// 상어찾기
		label:for(int x=0; x<4; x++) { for(int y=0; y<4; y++) {
				if(cmap[x][y][0]==17) {
					int dx=x, dy=y;
					for(int d=0; d<3; d++) { // 3번초과하여 이동하는경우 없음
						dx+=delx[cmap[x][y][1]]; dy+=dely[cmap[x][y][1]];
						if(dx<0||dx>=4||dy<0||dy>=4) { // 맵밖이면 갱신
							ans=Math.max(ans, sum);
							return;
						}
						if(cmap[dx][dy][0]==0) continue; // 물고기없으면 다음 경우로 넘김
						int tn=cmap[dx][dy][0], td=cmap[x][y][1]; // 먹힐 생선번호와 상어의 이동전 방향저장
						cmap[dx][dy][0]=17; cmap[x][y][0]=0; cmap[x][y][1]=0; // 상어이동
						dfs(sum+tn, cmap); // 상어이동후 탐색
						cmap[dx][dy][0]=tn; cmap[x][y][0]=17; cmap[x][y][1]=td; // 다음 경우를 위한 복구
					}
					break label;
				}
		}}
	}
}
