import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] map;
	static List<Loc> birus; 
	static int zero, min;
	
	static class Loc {
		int x;
		int y;
		
		Loc(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_bj_17142.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		birus = new ArrayList<>();
		
		zero = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					zero++;
				}
				if(map[i][j] == 2) {
					birus.add(new Loc(i, j));
				}
			}
		}
		
		min = Integer.MAX_VALUE;
		combi(0, 0, new int[M]);
		
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
		
		br.close();
	}
	
	static void combi(int s, int idx, int[] selected) {
		if(idx == M) {
			bfs(selected);
			return;
		}
		
		for(int i=s; i<birus.size(); i++) {
			selected[idx] = i;
			combi(i+1, idx+1, selected);
		}
	}
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static void bfs(int[] selected) {
		Queue<Loc> que = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		
		for(int i=0; i<M; i++) {
			Loc start = birus.get(selected[i]);
			que.add(start);
			visited[start.x][start.y] = true;
		}
		
		int ans=0, cnt=0;
		while(true) {
			int size = que.size();
			
			if(size==0) return;
			
			if(cnt == zero) {		// 빈칸 다채움
				min = Math.min(min, ans);
				return;
			}
			
			while(size-- > 0) {
				Loc now = que.poll();
				
				for(int d=0; d<4; d++) {
					int nx = now.x + dx[d];
					int ny = now.y + dy[d];
					
					if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny] || map[nx][ny] == 1) continue;
					visited[nx][ny] = true;
					if(map[nx][ny] == 0) {
						cnt++;
					}
					que.add(new Loc(nx, ny));
				}
				
			}
			
			ans++;
		}
		
	}

}
