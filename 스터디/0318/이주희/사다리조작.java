import java.io.*;
import java.util.*;

public class Main_bj_15684_사다리조작 {

	static int N, M, H, ans;
	static boolean[][] map;
	static int[] vertiSelect;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_bj_15684.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());		// 새로선의 갯수
		M = Integer.parseInt(st.nextToken());		// 가로선의 갯수
		H = Integer.parseInt(st.nextToken());		// 놓을 수 있는 다리의 갯수
		
		map = new boolean[H+1][N+1];				// 사다리 그림
		
		while(M-->0) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = true;
		}
		
		ans = -1;
		if(play()) {	// 아무것도 추가하지 않았을 때 true가 리턴된다면 ans=0
			ans = 0;
		} else {
			// 중복 조합 (사다리 선택)
			for(int i=1; i<=3; i++) {
				vertiSelect = new int[i];
				if(verti(1, 0, i)) {
					ans = i;
					break;
				}			
			}

		}
				
		System.out.println(ans);
		
	}
	
	// 1~N-1까지 사다리 target 갯수만큼 중복 선택
	static boolean verti(int start, int cnt, int target) {
		if(cnt == target) {
			if(hori(0, target)) return true;		// H개 중에 다리 선택
			return false;
		}
		
		for(int i=start; i<N; i++) {
			vertiSelect[cnt] = i;
			if(verti(i, cnt+1, target)) return true;
		}
		return false;
	}
	
	static boolean hori(int idx, int target) {
		if(idx == target) {
			if(play()) return true;
			return false;
		}
		
		for(int i=1; i<=H; i++) {
			if(map[i][vertiSelect[idx]] == true) continue;		// 이전에 선택된 번호가 같다면
			map[i][vertiSelect[idx]] = true;
			if(hori(idx+1, target)) return true;
			map[i][vertiSelect[idx]] = false;
		}
		
		return false;
	}
	
	// 사다리 게임
	static boolean play() {
		for(int j=1; j<=N; j++) {
			int now = j;
			for(int i=1; i<=H; i++) {
				if(map[i][now] == true) {
					now+=1;
				} else if(now-1 >=1 && map[i][now-1] == true) {
					now-=1;
				}
			}
			if(now != j) {
				return false;
			}
		}
		
		return true;
	}

}
