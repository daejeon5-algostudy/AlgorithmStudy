import java.io.*;
import java.util.*;

public class Main_bj_2636_치즈 {

	static int[] di= {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2636.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		int left = 0;// 남은 치즈 조각
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) left++;
			}
		}
		
		int ans=0;	
		while(true) {
			// 0,0 부터 시작해서 연결된 모든 지점 (공기에 맞닿은 부분) -1로 변환
			map[0][0] = -1;
			
			int[][] tmp = new int[N][M];		//tmp에 복사해서 하는 이유는 다음 시간이 지났을 때
			for(int i=0; i<N; i++) {			//공기에 닿아있는 면적을 다시 구해야하기 때문(새로 녹은부분포함)
				tmp[i] = Arrays.copyOf(map[i], M);
			}
			
			Queue<int[]> que = new LinkedList<int[]>();
			que.add(new int[] {0, 0});
			
			while(!que.isEmpty()) {
				int[] cur = que.poll();
				
				for(int d=0; d<4; d++) {
					int ni = cur[0]+di[d];
					int nj = cur[1]+dj[d];
					
					if(ni<0 || ni>=N || nj<0 || nj>=M || tmp[ni][nj] != 0) continue;   // tmp[ni][nj]가 0이면
					tmp[ni][nj] = -1;
					que.add(new int[] {ni, nj});
				}
			}
			
			int changed=0;
			// 1인 지점 중 -1과 닿아있는 지점 0으로 변환
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(tmp[i][j] == 1) {
						
						for(int d=0; d<4; d++) {
							int ni = i + di[d];
							int nj = j + dj[d];
							
							if(ni<0 || ni>=N || nj<0 || nj>=M) continue;
							if(tmp[ni][nj] == -1) {
								map[i][j] = 0;
								changed++;
								break;
							}
						}
					}
				}
			}
				
			ans++;	// 1시간 지남
			if(left	== changed) {	// 남아있던 부분과 바뀐 부분이 같다면
				break;
			}
			left-=changed;
		}
		
		System.out.println(ans);
		System.out.println(left);
		br.close();
	}

}
