import java.io.*;
import java.util.*;

public class Main {
	static int[][] opers;
	static int N, M, K, ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		opers = new int[K][3];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<3; j++) {
				opers[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = Integer.MAX_VALUE;
		perm(0, map, new boolean[K]);		// 회전할 순서 순열
		System.out.println(ans);
	}
	
	static void perm(int idx, int[][] map, boolean[] isSelected) {
		if(idx == K) {
			getRes(map);
			return;
		}
		
		for(int i=0; i<K; i++) {
			if(isSelected[i]) continue;
			isSelected[i]=true;
			int[][] nmap = rotate(map, i);
			perm(idx+1, nmap, isSelected);
			isSelected[i] = false;
		}
	}
	
	static void getRes(int[][] map) {
		for(int i=1; i<=N; i++) {
			int sum=0;
			for(int j=1; j<=M; j++) {
				sum += map[i][j];
			}
			ans = Math.min(ans, sum);
		}
	}
	
	static int[] dx = {0, 1, 0, -1};	// →, ↓, ←, ↑
	static int[] dy = {1, 0, -1, 0};
	static int[][] rotate(int[][] map, int k) {
		int[][] nmap = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				nmap[i][j] = map[i][j];
			}
		}
		
		int sx = opers[k][0]-opers[k][2];
		int sy = opers[k][1]-opers[k][2];
		
		int ex = opers[k][0]+opers[k][2];
		int ey = opers[k][1]+opers[k][2];
		
		while(true) {
			if(ex < sx) break;
			int d=0;
			int x = sx, y = sy;
			while(true) {
				int nx = x+dx[d], ny=y+dy[d];
				if(nx < sx || nx > ex || ny < sy || ny > ey) {
					d+=1; 
					if(d==4) break;
					else continue;
					
				}
				nmap[nx][ny] = map[x][y];
					
				x = nx; y = ny;
			}
			sx+=1; sy+=1; ex-=1; ey-=1;
		}
		
		return nmap;
	}

}
