import java.io.*;
import java.util.*;

public class Main_bj_13460_구슬탈출2 {
	
	static class Balls{
		int br, bc, rr, rc, cnt;		// cnt: 몇번째 턴
		
		public Balls() {}

		public Balls(int br, int bc, int rr, int rc, int cnt) {
			super();
			this.br = br;
			this.bc = bc;
			this.rr = rr;
			this.rc = rc;
			this.cnt = cnt;
		}
	}
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int N, M, ans;
	static char[][] map;
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/input_bj_13460.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());		// 세로 크기
		M = Integer.parseInt(st.nextToken());		// 가로 크기
		
		map = new char[N][M];
		Balls balls = new Balls();		// br, bc, rr, rc, cnt
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j]=='B') {
					balls.br = i; balls.bc = j;
				}
				if(map[i][j]=='R') {
					balls.rr = i; balls.rc = j;
				}
			}
		}
		// 입력 완료
		
		ans = -1;
		bfs(balls);
		System.out.println(ans);
		br.close();
	}
	
	static void bfs(Balls balls) {
		Queue<Balls> que = new ArrayDeque<>();
		que.offer(balls);
		boolean[][][][] visited = new boolean[10][10][10][10];		// 공의 위치에 따른 방문 처리 (한번 방문한 위치는 가지 않기 위해)
		
		stop: while(!que.isEmpty()) {
			Balls curballs = que.poll();
			visited[curballs.br][curballs.bc][curballs.rr][curballs.rc] = true;
			
			if(curballs.cnt>=10) break;		// 10번 굴렸다면 더이상 굴릴 필요가 없음
			
			for(int d=0; d<4; d++) {		// 현재 볼들에 대해서 4방 탐색
				
				// 공을 굴림
				int[] nextB = rollBall(curballs.br, curballs.bc, d);
				int[] nextR = rollBall(curballs.rr, curballs.rc, d);
				
				if(map[nextB[0]][nextB[1]] == 'O') continue;
				if(map[nextR[0]][nextR[1]] == 'O') {
					ans = curballs.cnt+1; break stop;
				}
				
				// 두 공의 위치가 같다면
				if(nextB[0] == nextR[0] && nextB[1] == nextR[1]) {
					if(d==0) {
						if(curballs.br < curballs.rr) {
							nextR[0]++;
						}else {
							nextB[0]++;
						}
					}else if(d==1) {
						if(curballs.bc > curballs.rc) {
							nextR[1]--;
						}else {
							nextB[1]--;
						}
					}else if(d==2) {
						if(curballs.br > curballs.rr) {
							nextR[0]--;
						}else {
							nextB[0]--;
						}
					}else {
						if(curballs.bc < curballs.rc) {
							nextR[1]++;
						}else {
							nextB[1]++;
						}
					}
				}
				// 공 위치 조정 완료
				
				if(!visited[nextB[0]][nextB[1]][nextR[0]][nextR[1]]) {	// 처음 가보는 위치라면
					que.offer(new Balls(nextB[0], nextB[1], nextR[0], nextR[1], curballs.cnt+1));
				}
				
			}
			
		}
		
	}
	
	static int[] rollBall(int r, int c, int d) {
		
		while(map[r][c]!='#') {	  // 사방이 벽이여서 범위 검사 하지 않아도 됨
			if(map[r][c]=='O') return new int[] {r, c};		// 현재 위치가 탈출구라면 현재 위치를 리턴
			
			r += dr[d];
			c += dc[d];
		}
		
		// 벽을 만나서 while 문을 빠져 나온 경우, 벽 이전의 위치를 리턴
		return new int[] {r-=dr[d], c-=dc[d]};
	}
}
