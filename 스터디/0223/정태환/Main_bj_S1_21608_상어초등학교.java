package algoStudy.a0221;

import java.io.*;
import java.util.*;

public class Main_bj_S1_21608_상어초등학교 {

	static int[] deltax = new int[] { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] deltay = new int[] { 0, 1, 0, -1 };
	static int N;
	static int[][] arr, mem;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_21608"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // N * N 행렬
		arr = new int[N + 1][N + 1];
		mem = new int[N * N + 1][4];
		for (int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken()); // 학생번호
			mem[n][0] = Integer.parseInt(st.nextToken());
			mem[n][1] = Integer.parseInt(st.nextToken());
			mem[n][2] = Integer.parseInt(st.nextToken());
			mem[n][3] = Integer.parseInt(st.nextToken()); // 좋아하는 학생번호
			setting(n); // 자리 세팅하기
		}
//		for(int[] ia: arr)
//			System.out.println(Arrays.toString(ia));
		
		int ans = 0;
		// 만족도 계산하기
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int now = arr[i][j];
				int favor = 0;
				for (int d = 0; d < 4; d++) {
					int dx = i + deltax[d];
					int dy = j + deltay[d];
					if (dx < 1 || dx > N || dy < 1 || dy > N)
						continue;
					if (arr[dx][dy] == mem[now][0] || arr[dx][dy] == mem[now][1] 
					 || arr[dx][dy] == mem[now][2] || arr[dx][dy] == mem[now][3])
						favor++;
				}
				if(favor == 1) ans++;
				else if(favor >= 2) {
					ans += Math.pow(10, favor-1);
				}
			}
		}
		System.out.println(ans);
	}

	static void setting(int n) {
		// 좋아하는학생숫자 0번인덱스, 비어있는칸 1번인덱스, 행좌표 2번인덱스, 열좌표 3번인덱스
		PriorityQueue<Integer[]> q = new PriorityQueue<>((o1, o2) -> {
			if(o1[0] != o2[0]) return o2[0] - o1[0];
			else if(o1[1] != o2[1]) return o2[1] - o1[1];
			else if(o1[2] != o2[2]) return o1[2] - o2[2];
			else return o1[3] - o2[3];
		});
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(arr[i][j] != 0) continue;
				int favor = 0; // 좋아하는학생수
				int blank = 0; // 비어있는칸
				for (int k = 0; k < 4; k++) {
					int dx = i + deltax[k];
					int dy = j + deltay[k];
					if (dx < 1 || dx > N || dy < 1 || dy > N)
						continue;
					if (arr[dx][dy] == 0)
						blank++;
					else if (   arr[dx][dy] == mem[n][0] || arr[dx][dy] == mem[n][1] 
							 || arr[dx][dy] == mem[n][2] || arr[dx][dy] == mem[n][3])
						favor++;
				}
				q.offer(new Integer[] {favor, blank, i, j});
			}
		}
//		for(int[] ia: arr)
//			System.out.println(Arrays.toString(ia));
//		for(Integer[] now : q) {
//			System.out.println(n + " " + Arrays.toString(now));
//		}
//		System.out.println();
		Integer[] now = q.poll();
		arr[now[2]][now[3]] = n;

	}

}
