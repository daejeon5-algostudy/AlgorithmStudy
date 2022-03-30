package practice0322;
import java.io.*;
import java.util.*;


public class Main_bj_2636_치즈 {
	static int[] di= {-1,0,1,0};
	static int[] dj= {0,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][][] map = new int[N][M][2];//현재 미래 표시
		
		int total=0;
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) {
				int n = Integer.parseInt(st.nextToken());
				total+=n;
				map[i][j][0]=n;
				map[i][j][1]=n;
			}
		}
		int time=1;
		while(true) {

			boolean[][] visited = new boolean[N][M];
		
			int count = 0;
			for(int t=0;t<M;t++) {
				solve(0,t,map, visited, N, M);
				solve(N-1,t,map, visited, N, M);
			}
			for(int t=0; t<N; t++) {
				solve(t,0,map, visited, N, M);
				solve(t,M-1,map, visited, N, M);
			}
			for(int i=0; i<N; i++) {
				
				for(int j=0; j<M; j++) {
					
					map[i][j][0]=map[i][j][1];
					if(map[i][j][0]==1)count++;
				}
			}

			if(count==0)break;
			time++;
			total = count;
		}
		
		System.out.println(time);
		System.out.println(total);
		br.close();

	}
	static void solve(int i, int j, int[][][] map, boolean[][] visited, int N, int M) {
		if(visited[i][j])return;
		visited[i][j]=true;
		for(int d=0; d<4;d++) {
			int ni=i+di[d];
			int nj = j+dj[d];
			if(0<=ni&&ni<N &&0<=nj&&nj<M) {
				if(map[ni][nj][0]==1) {
					map[ni][nj][1]=0;
					//System.out.println("변환");
				}
			}
		}

		for(int d=0; d<4;d++) {
			int ni=i+di[d];
			int nj = j+dj[d];
			if(0<=ni&&ni<N &&0<=nj&&nj<M) {
				if(!visited[ni][nj]&&map[ni][nj][0]==0) {
					solve(ni, nj, map, visited, N, M);
				}
			}
		}
		
	}

}
