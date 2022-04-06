import java.io.*;
import java.util.*;

public class Main_bj_2206_벽부수고이동하기 {

	static int dr[] = {-1, 0, 1, 0};
	static int dc[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2206.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][][] map = new int[N][M][2];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j][0] = s.charAt(j) -'0';
				if(map[i][j][0]==1) {
					map[i][j][0] = -1;
					map[i][j][1] = -1;
				}
			}
		}
		
		map[0][0][0] = 1;
		Queue<int[]> que = new ArrayDeque<int[]>();
		que.offer(new int[] {0, 0, 0});		// (0, 0), 벽을 한 번도 안깼으므로 0
		
		int ans = -1;
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int r = cur[0];
			int c = cur[1];
			int k = cur[2]; 	// 벽을 부셨는지 안부셨는지 여부
			
			if(r==N-1 && c==M-1) {
				ans = map[r][c][k];
				break;
			}
			
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
				if(map[nr][nc][k]==0) {
					map[nr][nc][k] = map[r][c][k]+1;
					que.offer(new int[] {nr, nc, k});
				}else if(k==0 && map[nr][nc][k]==-1) {
					map[nr][nc][1] = map[r][c][k]+1;
					que.offer(new int[] {nr, nc, 1});
				}
			}
		}
		
		System.out.println(ans);
		br.close();

	}

}
