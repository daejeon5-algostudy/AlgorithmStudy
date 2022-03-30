import java.io.*;
import java.util.*;

public class Main_bj_16234_인구이동 {
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int N, L, R;
	static int[][] map, open, nmap;
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/input_bj_16234.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans=0;
		while(true) {
			open = new int[N][N];
			nmap = new int[N][N];
			for(int i=0; i<N; i++) {
				nmap[i] = Arrays.copyOf(map[i], N);			// 새 인구수를 담음
			}
			
			
			int cnt=0, index=1;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(open[i][j]==0) {
						cnt += bfs(i, j, index++);		// 열린다면 +1
					}
				}
			}
			
			if(cnt==0) break;
			
			for(int i=0; i<N; i++) {
				map[i] = Arrays.copyOf(nmap[i], N);
			}
			
			ans++;
		}
		System.out.println(ans);
		br.close();
	}
	
	static int bfs(int i, int j, int index) {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {i, j});
		open[i][j] = index;
		
		int cnt=1, total=map[i][j];
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			for(int k=0; k<4; k++) {
				int ni = cur[0]+di[k];
				int nj = cur[1]+dj[k];
				
				if(ni<0 || ni>=N || nj<0 || nj>=N || open[ni][nj]!=0) continue;	// 이미 열렸다면
				int d = Math.abs(map[ni][nj] - map[cur[0]][cur[1]]);
				
				if(d>=L && d<=R) {
					open[ni][nj] = index;
					que.offer(new int[] {ni, nj});
					cnt++;
					total += map[ni][nj];
				}
			}
		}
		
		if(cnt>1) {
			int value = total/cnt;
			for(int x=0; x<N; x++) {
				for(int y=0; y<N; y++) {
					if(open[x][y]==index) {
						nmap[x][y] = value;
					}
				}
			}
			
			return 1;
		}
		return 0;
	}

}
