import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, D, totalCnt, ans;
	static int[][] map;
	static int[] di = {0, -1, 0};	// 좌, 상, 우
	static int[] dj = {-1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());		// D 이하의 적만 쏠 수 있다.
		
		totalCnt=0;
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) totalCnt++;
			}
		}
		
		ans = 0;
		if(totalCnt!=0) {
			combi(0, 0, new int[3]);
		}
		System.out.println(ans);
		br.close();
	}
	
	static void combi(int idx, int cnt, int[] selected) {
		if(cnt==3) {
			attack(selected);
			return;
		}
		
		for(int i=idx; i<M; i++) {
			selected[cnt] = i;
			combi(i+1, cnt+1, selected);
		}
	}
	
	static void attack(int[] selected) {
		int[][] newMap = new int[N][M];
		for(int i=0; i<N; i++)
			newMap[i] = Arrays.copyOf(map[i], M);
		
		int cnt = 0; 			// 총 처치한 적의 수
		int left = totalCnt;	// 남아있는 적의 수
		int r = N; 				// 궁수의 행
		
		stop :while(true) {
			
			List<int[]> removed = new ArrayList<int[]>();	// 제거된 적들
			
			for(int i=0; i<3; i++) {	// 세 명의 궁수
				// 처치할 적을 탐색
				Queue<int[]> que = new ArrayDeque<int[]>();
				que.offer(new int[] {r-1, selected[i], 1});		// 궁수 바로 앞 위치, 거리 1
				boolean[][] visited = new boolean[N][M];
				visited[r-1][selected[i]] = true;
				
				while(!que.isEmpty()) {
					int[] cur = que.poll();
					
					if(cur[2] > D) break;
					
					if(newMap[cur[0]][cur[1]] == 1) {
						removed.add(new int[] {cur[0], cur[1]});
						break;
					}
					
					for(int d=0; d<3; d++) {	// 3방 탐색
						int ni = cur[0]+di[d];
						int nj = cur[1]+dj[d];
						
						if(ni<0 || ni>=N || nj<0 || nj>=M || visited[ni][nj]) continue;
						que.offer(new int[] {ni, nj, cur[2]+1});
						visited[ni][nj] = true;
					}
				}
				
				if(left==0) break stop;
			}
			// 세 명의 궁수가 모두 적을 처치함
			
			for(int i=0; i<removed.size(); i++) {
				int[] now = removed.get(i);
				if(newMap[now[0]][now[1]]==1) {
					cnt++; left--;
				}
				newMap[now[0]][now[1]]=0;
			}
			
			// 궁수들이 한칸씩 올라감
			for(int c=0; c<M; c++) {
				if(newMap[r-1][c]==1) {
					left--;					// 위로 올라갈 궁수 자리에 있는 적들은 처치됨
				}
			}
			
			if(r==1) break; 	// 위로 올라가도 적이 없다
			r--;
		}
		
		ans = Math.max(ans, cnt);
	}

}
