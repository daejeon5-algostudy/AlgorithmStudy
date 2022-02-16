
풀이
------------

* 3차원 배열을 사용해 각 지점에 존재하는 BC를 boolean형으로 저장
* A와 B의 값을 완전탐색으로 최댓값을 구함
* A와 B가 같은 BC를 사용하는 경우 총량은 같으므로 B의 전력량을 0으로 두고 계산


코드
------------

```java
package com.ssafy;

import java.util.*;
import java.io.*;

public class solution_5644_무선충전 {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("res/input_sw_5644.txt")));
		// BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int[] dj = new int[] { 0, 0, 1, 0, -1 }; // -, 상, 우, 하, 좌 (가로)
		int[] di = new int[] { 0, -1, 0, 1, 0 }; // 세로
		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#" + test_case + " ");
			StringTokenizer st = new StringTokenizer(bf.readLine());

			int M = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());

			// A가 이동하는 경로
			int[] A_move = new int[M];
			// B가 이동하는 경로
			int[] B_move = new int[M];
			int[] power = new int[A];
			
			// 지점에 어떤 BC이 있는지를 저장하는 배열
			boolean[][][] mat = new boolean[10][10][A + 1];
			st = new StringTokenizer(bf.readLine());
			
			for (int i = 0; i < M; i++) {
				A_move[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(bf.readLine());
			for (int i = 0; i < M; i++) {
				B_move[i] = Integer.parseInt(st.nextToken());
			}
			
			// BC 위치 지정
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(bf.readLine());
				int x = Integer.parseInt(st.nextToken()); // j
				int y = Integer.parseInt(st.nextToken()); // i
				int r = Integer.parseInt(st.nextToken());
				Queue<int[]> Q = new LinkedList<int[]>();
				Q.offer(new int[] { y-1, x-1 });
				for (int n = 0; n <= r; n++)// 1 1+4 1+4+8 -> 1,5,13
				{
					int size = Q.size();
					for (int m = 0; m < size; m++) {
						int[] tmp = Q.poll();
						mat[tmp[0]][tmp[1]][i] = true; // 마킹
						for (int j = 0; j < 5; j++) {
							// 범위안에 있는경우
							if(0<=tmp[0]+di[j]&&tmp[0]+di[j]<10&&0<=tmp[1]+dj[j]&&tmp[1]+dj[j]<10)
							{
								if (mat[tmp[0] + di[j]][tmp[1] + dj[j]][i])
									continue; // true면 안넣는다.
								Q.offer(new int[] { tmp[0] + di[j], tmp[1] + dj[j] }); // 큐에 집어넣기
							}
						}
					}
				}
				power[i] = Integer.parseInt(st.nextToken()); // 전력량
			}
			int[] isA = new int[]{0,0};
			int[] isB = new int[] {9,9};
			
			int res = 0;
			int max = -1;
			// 맨 처음위치
			for(int j=0;j<A;j++)
			{
				int ACharge = 0;
				int BCharge = 0;
				// A의 위치가 BC 범위 내일경우
				if(mat[isA[0]][isA[1]][j])
				{
					// A의 충전값을 갱신
					ACharge = power[j];
					// B의 위치에서 있는 BC를 찾는다.
					for(int k=0;k<A;k++)
					{
						// A와 B의 충전기가 같을경우 pass (어짜피 나눠쓰므로)
						if(k==j)continue;
						// 두개의 충전기가 다른경우
						if(mat[isB[0]][isB[1]][k])
						{
							// 가장 큰 충전량을 가진 BC값을 가져온다
							if(BCharge<power[k])
								BCharge = power[k];
						}
					}
				
					// A의 충전량과 B의 충전량을 합한 값이 제일 크다면 갱신한다.
					if(max<ACharge+BCharge)
						max = ACharge+BCharge;
				}
				// 해당지점이 A의 충전구역에 없는경우
				if(ACharge==0)
				{
					for(int k=0;k<A;k++)
					{
						// A는 충전구역에 없으므로 제일 큰 값만 구한다.
						if(mat[isB[0]][isB[1]][k])
						{
							if(BCharge<power[k])
								BCharge = power[k];
						}
					}
					// A의 충전량과 B의 충전량을 합한 값이 제일 크다면 갱신한다 2
					if(max<ACharge+BCharge)
						max = ACharge+BCharge;
				}
			}
			res+=max; // 0 인경우 
			
			
			for(int i=0;i<M;i++)
			{
				// A와 B 이동
				isA[0] += di[A_move[i]];
				isA[1] += dj[A_move[i]];
				isB[0] += di[B_move[i]];
				isB[1] += dj[B_move[i]];
				
				// 최댓값 초기화
				max = -1;
				for(int j=0;j<A;j++)
				{
					int ACharge = 0;
					int BCharge = 0;
					// BC 범위 내일경우
					if(mat[isA[0]][isA[1]][j])
					{
						ACharge = power[j];
						for(int k=0;k<A;k++)
						{
							if(k==j)continue;
							if(mat[isB[0]][isB[1]][k])
							{
								if(BCharge<power[k])
									BCharge = power[k];
							}
						}
						// 둘 다 지점이 있는경우 더한다.
						if(max<ACharge+BCharge)
							max = ACharge+BCharge;
					}
					if(ACharge==0)
					{
						for(int k=0;k<A;k++)
						{
							if(k==j)continue;
							if(mat[isB[0]][isB[1]][k])
							{
								if(BCharge<power[k])
									BCharge = power[k];
							}
						}
						// 둘 다 지점이 있는경우 더한다.
						if(max<ACharge+BCharge)
							max = ACharge+BCharge;
					}
				}
				res+=max;
			}
			System.out.println(res);
		}
	}
}
