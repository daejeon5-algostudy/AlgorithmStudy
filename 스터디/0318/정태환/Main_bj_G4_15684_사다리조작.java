package algoStudy.a0318;

import java.io.*;
import java.util.*;

public class Main_bj_G4_15684_사다리조작 {

	static int N,M,H,ans;
	static int[][] map;
	static boolean end;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_15684"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
		N=Integer.parseInt(st.nextToken()); // 세로선
		M=Integer.parseInt(st.nextToken()); // 가로선
		H=Integer.parseInt(st.nextToken()); // 가로선을 놓을 수 있는 위치의 개수
		
		map=new int[H+1][N+1];
		
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			map[x][y]=1;
			map[x][y+1]=2;
		}
		
		for(int i=0; i<=3; i++) {
			ans=i;
			dfs(1,0);
			if(end) break;
		}
		
		if(end) System.out.println(ans);
		else System.out.println(-1);
	}
	
	static void dfs(int start, int cnt) {
		if(end) return;
		if(ans==cnt) {
			if(game()) end=true;
			return;
		}
		
		for(int i=start; i<H+1; i++) { // 가로축 사다리 놓을 수 있는 경우
			for(int j=1; j<N; j++) { // 세로축
				if(map[i][j]==0 && map[i][j+1]==0) {
					map[i][j]=1;
					map[i][j+1]=2;
					dfs(i, cnt+1);
					map[i][j]=0;
					map[i][j+1]=0;
				}
			}
		}
	}

	static boolean game() {
		for(int i=1; i<=N; i++) {
			int x=1, y=i;
			for(int j=0; j<H; j++) {
				if(map[x][y]==1) y++;
				else if(map[x][y]==2) y--;
				x++;
			}
			if(y!=i) return false;
		}
		return true;
	}
	
}
