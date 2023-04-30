import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	// 북, 동, 남, 서
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_bj_14503.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] graph = new int[N][M];
		
		st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = 0;
		while(true) {
			if(graph[r][c] == 0) {	// 현재 위치 청소
				ans++;
				graph[r][c] = 2;	// 청소 처리
			}
			
			boolean isBlocked = true;	// 4방이 막혔거나 청소했는지 판별
			for(int i=0; i<4; i++) {
				d = (d-1+4)%4;			// 왼쪽 방향으로 회전
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(graph[nr][nc] == 0) {
					isBlocked = false;
					r = nr;
					c = nc;
					break;
				}
			}
			
			if(isBlocked) {
				int nd = (d+2) % 4;		// 후진
				int nr = r + dr[nd];
				int nc = c + dc[nd];
				
				if(graph[nr][nc] == 1) break;
				r = nr; c = nc;
			}
		}
		
		System.out.println(ans);
		br.close();
	}
}
