import java.io.*;
import java.util.*;

public class Main_bj_21608_상어초등학교 {
	
	static class Info {
		private boolean[] neighbor;
		private int blank;
		
		public Info(boolean[] neighbor, int blank) {
			this.neighbor = neighbor;
			this.blank = blank;
		}
	}
	
	static int N;
	static Info[][] map;
	static int[][] seat;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_bj_21608.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new Info[N][N];
		seat = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int blank = 4;
				if((i==0 && j==0) || (i==0 && j==N-1) || (i==N-1 && j==0) || (i==N-1 && j==N-1)) {
					blank = 2;
				} else if (i==0 || i==N-1 || j==0 || j==N-1) {
					blank = 3;
				}
				
				map[i][j] = new Info(new boolean[N*N+1], blank);
			}
		}
		
		int[][] favoriate = new int[N*N+1][4];
		
		StringTokenizer st;
		for(int i=0, size=N*N; i<size; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			for(int j=0; j<4; j++) {
				favoriate[num][j] = Integer.parseInt(st.nextToken());
			}
			
			findSeat(num, favoriate[num]);
		}
		
		System.out.println(getScore(favoriate));
		br.close();
	}
	
	static int getScore(int[][] favoriate) {
		int sum = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int cnt = 0;
				int num = seat[i][j];
				
				for(int k=0; k<4; k++) {
					int favo = favoriate[num][k];
					if(map[i][j].neighbor[favo]) {
						cnt++;
					}
				}
				
				if(cnt==0) continue;
				sum += Math.pow(10, cnt-1);
			}
		}
		
		return sum;
	}
	
	static class SeatInfo implements Comparable<SeatInfo>{
		int score;
		int blank;
		int i;
		int j;
		
		SeatInfo(int score, int blank, int i, int j) {
			this.score = score;
			this.blank = blank;
			this.i = i;
			this.j = j;
		}

		@Override
		public int compareTo(SeatInfo o) {
			
			if(this.score == o.score) {
				if(this.blank == o.blank) {
					if(this.i == o.i) {
						return this.j - o.j;
					}
					return this.i - o.i;
				}
				return o.blank - this.blank;		// 빈칸이 많은 순
			}
			
			return o.score - this.score;			// 점수가 높은 순
		}
	}
	
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static void findSeat(int num, int[] favoriate) {
		PriorityQueue<SeatInfo> que = new PriorityQueue();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(seat[i][j] == 0) {		// 아직 아무도 앉지 않은 좌석
					int score=0;
					for(int k=0; k<4; k++) {
						if(map[i][j].neighbor[favoriate[k]]) score++;
					}
					que.add(new SeatInfo(score, map[i][j].blank, i, j));
				}
			}
		}
		
		SeatInfo seatInfo = que.poll();		// num 이 앉을 자리
		seat[seatInfo.i][seatInfo.j] = num;
		
		for(int d=0; d<4; d++) {
			int ni = seatInfo.i + di[d];
			int nj = seatInfo.j + dj[d];
			
			if(ni<0 || ni>=N || nj<0 || nj>=N) continue;
			map[ni][nj].neighbor[num] = true;
			map[ni][nj].blank--;
		}
		
	}

}
