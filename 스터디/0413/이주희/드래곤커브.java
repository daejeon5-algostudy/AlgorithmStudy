import java.io.*;
import java.util.*;

public class Main_bj_15685_드래곤커브 {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_bj_15685.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[][] map = new boolean[101][101];
		
		int[] dx = {0, -1, 0, 1}; // →, ↑, ←, ↓
		int[] dy = {1, 0, -1, 0};
		StringTokenizer st;
		int y, x, d, time;
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			
			map[x][y] = true;
			List<Integer> dirs = new ArrayList<>();
			x += dx[d];
			y += dy[d];
			dirs.add(d);
			map[x][y] = true;
			
			for(int i=1; i<=time; i++) {
				int size = dirs.size();
				for(int j=size-1; j>=0; j--) {
					d = (dirs.get(j)+1)%4;
					x += dx[d];
					y += dy[d];
					
					map[x][y] = true;
					dirs.add(d);
				}
			}
		}
		
		// 갯수 세기
		int cnt = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j] != true) continue;
				if(map[i][j+1]==true && map[i+1][j]==true && map[i+1][j+1]==true) cnt++;
			}
		}
		
		System.out.println(cnt);
		
	}

}
