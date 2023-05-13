import java.io.*;
import java.util.*;

public class Main_bj_17825_주사위윷놀이 {
	
	static int ans;
	static int[][] board;
	static int[] dice;
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_bj_17825.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		dice = new int[10];
		for(int i=0; i<10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		board = new int[4][26];
		board[0] = new int[] {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, -1, -1, -1, -1, -1};
		board[1] = new int[] {0, 0, 0, 0, 0, 10, 13, 16, 19, 25, 30, 35, 40, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
		board[2] = new int[] {0, 0, 0, 0, 0,  0,  0,  0,  0,  0, 20, 22, 24, 25, 30, 35, 40, -1, -1, -1, -1, -1, -1, -1, -1, -1};
		board[3] = new int[] {0, 0, 0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 30, 28, 27, 26, 25, 30, 35, 40, -1, -1, -1};
		
		v = new boolean[4][26];
		ans = 0;
		play(0, 0, new int[4], new int[4]);
		System.out.println(ans);
	}
	
	static void play(int turn, int total, int[] x, int[] y) {
		if(turn==10) {
			ans = Math.max(ans, total);
			return;
		}
		
		for(int i=0; i<4; i++) {
			int tx=x[i], ty=y[i];		// 복구를 위한 저장
			if(board[x[i]][y[i]] == -1) {	// 이미 도착한 말이라면 다른 말
				continue;
			}
			v[x[i]][y[i]] = false; 	// 이동할거여서 방문 해제

			// 외각에 있고 지름길에 위치한다면 경로 변경
			if(x[i]==0) {
				if(y[i]==5) {
					x[i] = 1;
				} else if(y[i]==10) {
					x[i] = 2; 
				} else if(y[i]==15) {
					x[i] = 3;
				}
			}
			
			y[i] += dice[turn];		// 주사위 수 만큼 전진
			int score = board[x[i]][y[i]];		// 얻는 점수
			// 3개의 지름길이 공통적으로 지나가는 길 = 25, 30, 35, 40
			// 방문 표시를 위해 40은 0번, 나머지는 1번 지름길로 경로 변경
			if(x[i]!=0) {
				if(score==40) {
					x[i] = 0; y[i] = 20;
				} else if(score==25) {	
					x[i] = 1; y[i] = 9;
				} else if(score==30) {
					x[i] = 1; y[i] = 10;
				} else if(score==35) {
					x[i] = 1; y[i] = 11;
				}
			}
			
			if(!v[x[i]][y[i]]) {	// 다른 말이 없을 때만
				if(score==-1) {
					play(turn+1, total, x, y);	// 도착지라면 점수를 더하지 않고 패스
				} else {
					v[x[i]][y[i]] = true;
					play(turn+1, total+score, x, y);
					v[x[i]][y[i]] = false;
				}
			}
			
			x[i]=tx; y[i]=ty; v[x[i]][y[i]] = true;	// 다음 경우를 위한 복구 
		}
		
	}

}
