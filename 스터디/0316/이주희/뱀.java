import java.io.*;
import java.util.*;

public class Main_bj_3190_뱀 {
	
	static class Turn {
		int time;
		char dir;
		
		public Turn(int time, char dir) {
			this.time = time;
			this.dir = dir;
		}
	}
	
	static int N;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_bj_3190.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		int A = Integer.parseInt(br.readLine());
		StringTokenizer st;
		while(A-->0) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			
			map[x][y] = 2; 	// 사과 표시
		}
		int M = Integer.parseInt(br.readLine());
		List<Turn> turnList = new ArrayList<>();
		while(M-->0) {
			st = new StringTokenizer(br.readLine(), " ");
			turnList.add(new Turn(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
		}
		
		int time=0;
		Queue<int[]> tail = new ArrayDeque<>();
		int[] head = new int[] {0, 0};
		int d = 0;
		tail.add(new int[] {0, 0});
		map[head[0]][head[1]] = 1;		// 뱀 표시
		int idx = 0;
		while(true) {
			// 뱀 움직이기
			time++;
			if(!move(head, tail, d)) {
				break;
			}
			
			// 회전하기
			if(idx >= turnList.size()) continue;
			else if(turnList.get(idx).time == time) {
				if(turnList.get(idx).dir == 'D') {
					d = (d+1)%4;
				} else {
					d = (d-1+4)%4;
				}
				idx++;
			}
		}
		System.out.println(time);
	}
	
	static int[] dx = {0, 1, 0, -1}; // → ↓ ← ↑
	static int[] dy = {1, 0, -1, 0};
	static boolean move(int[] head, Queue<int[]> tail, int d) {
		int nx = head[0] + dx[d];
		int ny = head[1] + dy[d];
		
		if(nx<0 || nx>=N || ny<0 || ny>=N || map[nx][ny] == 1) return false;	// 게임 종료
		
		if(map[nx][ny] != 2) {		// 사과 없음
			int[] t = tail.poll();
			map[t[0]][t[1]] = 0; 	// 꼬리 줄이기
		}
		
		head[0] = nx; head[1] = ny;
		tail.add(new int[] {nx, ny});
		map[nx][ny] = 1;
		
		return true;
	}

}
