import java.io.*;
import java.util.*;

public class Main_bj_5427_불 {
	
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_5427.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(),  " ");
			
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			char[][] map = new char[N][M];
			boolean[][] visited = new boolean[N][M];
			
			Queue<int[]> fireQue = new ArrayDeque<int[]>();
			Queue<int[]> pQue = new ArrayDeque<int[]>();
			
			for(int i=0; i<N; i++) {
				String s = br.readLine();
				for(int j=0; j<M; j++) {
					map[i][j] = s.charAt(j);
					if(map[i][j]=='@') {
						pQue.offer(new int[] {i, j});
						map[i][j] = '.';
						visited[i][j] = true;
					}else if(map[i][j]=='*') {
						fireQue.offer(new int[] {i, j});
					}
				}
			}
			
			int seconds=0;
			boolean success = false;
			stop: while(!pQue.isEmpty()) {
				seconds++;

				// 1. 불들이 옮겨붙는다.
				int size = fireQue.size();
				while(size-- > 0) {
					int[] fire = fireQue.poll();
					
					for(int d=0; d<4; d++) {
						int ni = fire[0] + di[d];
						int nj = fire[1] + dj[d];
						
						if(ni<0 || ni>=N || nj<0 || nj>=M || map[ni][nj]!='.') continue;
						map[ni][nj] = '*';
						fireQue.offer(new int[] {ni, nj});
					}
				}
				
				
				size = pQue.size();
				while(size-- > 0) {
					// 2. 상근이가 이동한다.
					int[] p = pQue.poll();
					
					for(int d=0; d<4; d++) {
						int ni = p[0] + di[d];
						int nj = p[1] + dj[d];
						
						if(ni<0 || ni>=N || nj<0 || nj>=M) {		// 탈출
							success = true;
							break stop;
						}
						if(visited[ni][nj] || map[ni][nj] !='.') continue;
						visited[ni][nj] = true;
						pQue.offer(new int[] {ni, nj});
					}
				}
			}
			
			if(success) sb.append(seconds).append("\n");
			else sb.append("IMPOSSIBLE").append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
	}

}
