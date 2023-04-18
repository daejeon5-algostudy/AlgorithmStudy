import java.io.*;
import java.util.*;

public class Main_bj_19236_청소년상어 {
	
	static final int N = 4;
	static int ans;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};  //↑, ↖, ←, ↙, ↓, ↘, →, ↗
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	
	static class Fish {
		int x;				// 위치
		int y;
		int dir;			// 방향
		
		Fish(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_bj_19236.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] map = new int[N][N];	// 물고기 번호, 상어 = -1, 먹힘 = 0
		Fish[] fishes = new Fish[17];
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				fishes[num] = new Fish(i, j, dir-1);
				map[i][j] = num;
			}
		}
		
		// 상어 (0, 0) 먹음
		int fishNum = map[0][0];
		Fish eaten = fishes[fishNum];
		Fish shark = new Fish(0, 0, eaten.dir);
		fishes[fishNum] = null;
		map[0][0] = -1;
		
		ans=0;
		process(shark, map, fishes, fishNum);
		System.out.println(ans);
	}
	
	static void process(Fish orishark, int[][] orimap, Fish[] orifishes, int total) {
		// 복사
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = orimap[i][j];
			}
		}
		
		Fish[] fishes = new Fish[17];
		for(int i=1; i<=16; i++) {
			if(orifishes[i] == null) continue;
			fishes[i] = new Fish(orifishes[i].x, orifishes[i].y, orifishes[i].dir);
		}
		
		Fish shark = new Fish(orishark.x, orishark.y, orishark.dir);
		
		// 물고기 이동
		for(int i=1; i<=16; i++) {
			Fish now = fishes[i];
			if(now == null) continue; 	// 상어에게 먹힌 물고기
			
			int origDir = now.dir;
			 
			do {
				int nx = now.x + dx[now.dir];
				int ny = now.y + dy[now.dir];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N || map[nx][ny]==-1)  {	// 바깥을 넘어가거나 상어가 있다면 회전
					now.dir = (now.dir + 1) % 8;
					continue;
				}
				
				int fishNum = map[nx][ny];
				if(fishNum != 0) {			// 물고기 있음
					Fish fish = fishes[fishNum];
					fish.x = now.x;
					fish.y = now.y;
				}
				
				map[nx][ny] = i; 
				map[now.x][now.y] = fishNum; 
				now.x = nx;
				now.y = ny;
				
				break;
			} while (origDir != now.dir);	// 다시 원래대로 돌아오기 전까지
			
		}
		
		// 상어 이동
		int nx = shark.x, ny = shark.y;
		for(int k=1; k<=3; k++) {
			nx += dx[shark.dir];
			ny += dy[shark.dir];
			
			if(nx<0 || nx>=N || ny<0 || ny>=N) break;
			if(map[nx][ny] == 0) continue;				// 물고기 없음
			
			int fishNum = map[nx][ny];
			Fish fish = fishes[fishNum];
			
			map[shark.x][shark.y] = 0;
			map[nx][ny] = -1;
			
			shark.x = nx;
			shark.y = ny;
			shark.dir = fish.dir;
			
			fishes[fishNum] = null;
			
			process(shark, map, fishes, total+fishNum);
			
			// 복구
			map[nx][ny] = fishNum;
			map[orishark.x][orishark.y] = -1;
			
			fishes[fishNum] = fish;
			
			shark.x = orishark.x;
			shark.y = orishark.y;
			shark.dir = orishark.dir;
		}
		
		ans = Math.max(ans, total);
	}

}
