import java.io.*;
import java.util.*;

public class Main_bj_2573_빙산 {

	static int N, M;
	static int[][] ice, tmp;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2573.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ice = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				ice[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		tmp = new int[N][M];
		int ans = 0;
		while (true) {
			int cnt = getCnt();		// 빙하 덩어리 갯수를 셈
			if (cnt == 0) {			// 빙하가 없음
				ans = 0;
				break;
			}	
			if (cnt >= 2)			// 빙하 덩어리가 2개 이상
				break;

			ans++;

			// 빙하가 녹음
			for (int i = 0; i < N; i++) {
				tmp[i] = Arrays.copyOf(ice[i], M);
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (ice[i][j] != 0) {
						melting(i, j);
					}
				}
			}
			
			// tmp 를 ice에 대입
			for(int i=0; i<N; i++) {
				ice[i] = Arrays.copyOf(tmp[i], M);
			}
		}

		System.out.println(ans);
		br.close();
	}

	static void melting(int i, int j) {

		for (int d = 0; d < 4; d++) {     // 빙하의 4방을 탐색
			int ni = i + di[d];
			int nj = j + dj[d];

			if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
			if (ice[ni][nj]==0) tmp[i][j]--;		// 주변이 0이면 현재 빙하 -1
			if(tmp[i][j]==0) break;					// 0이 된다면 더이상 빼지 않고 break
		}

	}

	// 덩어리 갯수를 세서 반환하는 함수
	static int getCnt() {
		for (int i = 0; i < N; i++) {
			tmp[i] = Arrays.copyOf(ice[i], M);
		}

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmp[i][j] != 0) {	  // 빙하라면
					cnt++;				        // 1증가
					bfs(i, j);			      // 현재 빙하와 연결된 지점들을 tmp 2차원 배열에 모두 0으로 변환
				}
			}
		}

		return cnt;
	}
	
  // 매개변수로 입력되는 빙하와 연결된 지점들을 tmp 2차원 배열에 모두 0으로 변환
	static void bfs(int i, int j) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] { i, j });
		tmp[i][j] = 0;        // 0으로 방문처리

		while (!que.isEmpty()) {
			int[] cur = que.poll();

			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int ni = cur[0] + di[d];
				int nj = cur[1] + dj[d];

				if (ni < 0 || ni >= N || nj < 0 || nj >= M || tmp[ni][nj] == 0) 
					continue;
				tmp[ni][nj] = 0;
				que.offer(new int[] { ni, nj });
			}
		}
	}

}
