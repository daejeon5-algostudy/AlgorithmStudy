import java.io.*;
import java.util.*;

public class Main_bj_2638_치즈 {
	
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2638.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		int left=0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) left++;
			}
		}
		
		int seconds = 0;
		stop :while(true) {
			if(left==0) break;
			
			seconds++;

			// (0,0) 를 시작으로 공기를 -1로 바꾼다.
			int[][] tmp = new int[N][M];
			for(int i=0; i<N; i++)
				tmp[i] = Arrays.copyOf(map[i], M);
			
			air(tmp, N, M);
			
			// 공기와 맞닿는 면적이 2이상인 경우 0으로 바꿈
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j]==1) {
						
						int airCnt=0;
						// 4방 탐색
						for(int d=0; d<4; d++) {
							int ni = i+di[d];
							int nj = j+dj[d];
							
							if(ni<0 || ni>=N || nj<0 || nj>=M || tmp[ni][nj]!=-1) continue;
							airCnt++;
						}
						
						if(airCnt>=2) {
							map[i][j] = 0;
							left--;
						}
						
						if(left==0) break stop;
					}
				}
			}
		}
		
		System.out.println(seconds);
		br.close();
	}
	
	static void air(int[][] tmp, int N, int M) {
		Queue<int[]> que = new ArrayDeque<int[]>();
		que.offer(new int[] {0, 0});
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			
			for(int d=0; d<4; d++) {
				int ni = cur[0] + di[d];
				int nj = cur[1] + dj[d];
				
				if(ni<0 || ni>=N || nj<0 || nj>=M || tmp[ni][nj]!=0) continue;
				tmp[ni][nj] = -1;
				que.offer(new int[] {ni, nj});
			}
		}
	}

}
