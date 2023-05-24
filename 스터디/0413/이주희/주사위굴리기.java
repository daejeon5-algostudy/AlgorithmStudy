import java.io.*;
import java.util.*;

public class Main_bj_14499_주사위굴리기 {
	
	static int N, M, x, y;
	static int[][] map;
	static int[] dice;
	//    [2]
	// [4][1][3]
	//    [5]
	//    [6]

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_bj_14499.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		x = Integer.parseInt(st.nextToken());		// 주사위 초기 위치
		y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dice = new int[7];
		st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		while(K-- > 0) {
			int d = Integer.parseInt(st.nextToken());		// 굴리는 방향
			int res = roll(d);
			if(res != -1) {
				sb.append(res).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	static int[][] idx = {
			{}, 
			{1, 4, 2, 3}, 		// 동 (1 -> 4, 4 -> 2, 2 -> 3, 3 -> 1) 
			{1, 3, 2, 4},		// 서
			{1, 5, 2, 6},		// 북
			{1, 6, 2, 5}		// 남
	};
	
	static int[] dx = {0, 0, 0, -1, 1};		// 0, 동, 서, 북, 남
	static int[] dy = {0, 1, -1, 0, 0};
	static int roll(int d) {
		int nx = x + dx[d];
		int ny = y + dy[d];
		if(nx<0 || nx>=N || ny<0 || ny>=M) return -1;
		
		int[] tmp = new int[7];				// 바뀐 주사위 위치
		for(int i=1; i<=6; i++) {
			tmp[i] = dice[i];
		}
		for(int i=0; i<4; i++) {
			tmp[idx[d][i]] = dice[idx[d][(i+1)%4]];
		}
		
		dice = tmp;
		
		if(map[nx][ny] == 0) {
			map[nx][ny] = dice[2];
		} else {
			dice[2] = map[nx][ny];
			map[nx][ny] = 0;
		}
	
		x = nx; y = ny;
		return dice[1];		// 윗면
	}

}
