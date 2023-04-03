import java.io.*;
import java.util.*;

//1. 토네이도 이동
//2. 모래 흩뿌리기
//3. 밖으로 나간 모래 계산
//4. (0, 0) 도착 시 종료

public class Main_bj_20057_마법사상어와토네이도 {
	
	static int[][] send; 
	static int N, ans;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_bj_20057.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		send = new int[N][N];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				send[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int x = N/2;		// 시작 x 좌료
		int y = N/2;		// 시작 y 좌표
		int d = 0;			// 토네이도 방향
		int moveCnt=0;		// 움직인 횟수
		int time=1;			// 방향 바꿀 횟수
		int idx=0;
		
		int[] dx = {0, 1, 0, -1};		// 좌, 하, 우, 상
		int[] dy = {-1, 0, 1, 0};
		
		int[] ddx = {-1, 1, 1, -1};		// 대각선 (좌상, 좌하, 우하, 우상)
		int[] ddy = {-1, -1, 1, 1};
		
		ans = 0;
		int result=0;
		while(true) {
			if(x == 0 && y == 0) break;
			
			int nx = x + dx[d]; 
			int ny = y + dy[d];
			
			result = 0;			// 퍼진량
			// 7%
			result += (int)(send[nx][ny]*0.07)*2;
			divideSend(nx, ny, (d+1)%4, (int)(send[nx][ny]*0.07), 1, dx, dy);
			divideSend(nx, ny, (d+3)%4, (int)(send[nx][ny]*0.07), 1, dx, dy);
			
			// 2%
			result += (int)(send[nx][ny]*0.02)*2;
			divideSend(nx, ny, (d+1)%4, (int)(send[nx][ny]*0.02), 2, dx, dy);
			divideSend(nx, ny, (d+3)%4, (int)(send[nx][ny]*0.02), 2, dx, dy);
			
			// 5%
			result += (int)(send[nx][ny]*0.05);
			divideSend(nx, ny, d, (int)(send[nx][ny]*0.05), 2, dx, dy);
			
			// 10%
			result += (int)(send[nx][ny]*0.1)*2;
			divideSend(nx, ny, d, (int)(send[nx][ny]*0.1), 1, ddx, ddy);
			divideSend(nx, ny, (d+1)%4, (int)(send[nx][ny]*0.1), 1, ddx, ddy);
			
			// 1%
			result += (int)(send[nx][ny]*0.01)*2;
			divideSend(nx, ny, (d+2)%4, (int)(send[nx][ny]*0.01), 1, ddx, ddy);
			divideSend(nx, ny, (d+3)%4, (int)(send[nx][ny]*0.01), 1, ddx, ddy);
			
			// a
			divideSend(nx, ny, d, send[nx][ny]-result, 1, dx, dy);
			
			send[nx][ny] = 0;
			
			moveCnt++;
			if(moveCnt==time) {
				if(idx<1) {
					idx++;
				} else {
					idx=0;
					time++;
				}
				moveCnt=0;
				d=(d+1)%4;
			}
			
			x = nx; y = ny;
		}
		
		System.out.println(ans);
		br.close();
	}
	
	static void divideSend(int x, int y, int d, int weight, int length, int[] dx, int[] dy) {
		int nx = x, ny = y;
		
		for(int i=0; i<length; i++) {
			nx += dx[d];
			ny += dy[d];
		}
		
		if(nx<0 || nx>=N || ny<0 || ny>=N) {
			ans += weight;
		} else {
			send[nx][ny] += weight;
		}
	}
}
