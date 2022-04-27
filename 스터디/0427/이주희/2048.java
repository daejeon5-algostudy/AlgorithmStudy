import java.io.*;
import java.util.*;

public class Main_bj_12100_2048Easy {
	
	static int N, ans;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_12100.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0;
		
		// 최대 5번이니 4방향으로 갈 수 있는 경우의 수 4^5
		for(int val=0; val < (1<<2*5); val++) {
			
			// 원본 배열 복사
			int[][] map2 = new int[N][N];
			for(int i=0; i<N; i++)
				map2[i] = Arrays.copyOf(map[i], N);
			
			int tmp=val;
			for(int i=0; i<5; i++) {	// 4방향
				int d = tmp%4;
				tmp/=4;
				push(d, map2);
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++)
					ans = Math.max(ans, map2[i][j]);
			}
		}
		
		System.out.println(ans);
		br.close();

	}
	
	static void push(int d, int[][] map) {
		while(d-->0) rotate(map);
		
		for(int i=0; i<N; i++) {
			int[] push = new int[N];						// 밀린 배열
			
			int idx=0;
			for(int j=0; j<N; j++) {
				if(map[i][j]==0) continue; 					// 밀 숫자가 없다
				
				if(push[idx]==0) push[idx] = map[i][j];		// 빈 공간
				else if(push[idx] == map[i][j]) {			// 같은 숫자가 밀림
					push[idx++]*=2;
				}else										// 다른 숫자가 밀림
					push[++idx] = map[i][j];	 
			}
			
			for(int j=0; j<N; j++) {
				map[i][j] = push[j];
			}
		}
		
	}
	
	// 왼쪽으로 90도 회전
	static void rotate(int[][] map) {
		int[][] tmp = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				tmp[i][j] = map[j][N-i-1];
			}
		}
		
		for(int i=0; i<N; i++)
			map[i] = Arrays.copyOf(tmp[i], N);
		
	}

}
