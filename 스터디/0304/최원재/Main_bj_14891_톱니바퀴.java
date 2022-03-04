package practice0303;

import java.io.*;
import java.util.*;

public class Main_bj_14891_톱니바퀴 {
	/*
	 * 극이 다르면 반대 방향으로 회전 1번 ~ 4버 톱니바퀴 12시 방향부터 차례대로 주어짐
	 * 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] Gear = new int[4][8];

		for (int i = 0; i < 4; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				Gear[i][j] = str.charAt(j) - '0';
			}
		}
		/*
		 * 2번 과 6번 6번과 2번을 비교
		 */
		int N = Integer.parseInt(br.readLine()); // 돌리는 횟수
		for (int t = 0; t < N; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());// 1시계 -1 반시계
			int d3 = Gear[num - 1][2]; // 3시 방향
			int d9 = Gear[num - 1][6]; // 9시 방향

		
			if (dir == 1) {
				int tmp = Gear[num - 1][7];
				for (int i = 7; i >= 1; i--) {
					Gear[num - 1][i] = Gear[num - 1][i - 1];
				}
				Gear[num - 1][0] = tmp; // 시계방향으로 회전
			}else {
				int tmp = Gear[num-1][0];
				for (int j = 0; j <= 6; j++) {
					Gear[num-1][j] = Gear[num-1][j + 1];
				}
				Gear[num-1][7] = tmp; // 반시계방향으로 회전
			}
			int front_dir = dir;
			int back_dir = dir;
			for (int i = num; i < 4; i++) {
				if (d3 != Gear[i][6]) { // 마주 보는 것이 다르다면
					d3 = Gear[i][2];
					if (front_dir == 1) { // 회전이 시계면 우측은 반시계
						front_dir *= -1;
						int tmp2 = Gear[i][0];
						for (int j = 0; j <= 6; j++) {
							Gear[i][j] = Gear[i][j + 1];
						}
						Gear[i][7] = tmp2; // 반시계방향으로 회전
					} else {// 우측이 반시계면 좌측은 시계
						front_dir *= -1;// 방향 갱신
						int tmp2 = Gear[i][7];
						for (int j = 7; j > 0; j--) {
							Gear[i][j] = Gear[i][j - 1];
						}
						Gear[i][0] = tmp2; // 반시계방향으로 회전
					}

				} else
					break;
			}
			for (int i = num - 2; i >= 0; i--) {
				//System.out.println(i);
				if (d9 != Gear[i][2]) {
					d9 = Gear[i][6];
					//System.out.println(i);
					if (back_dir == 1) { // 회전이 시계면 우측은 반시계
						back_dir *= -1;// 방향 갱신
						int tmp2 = Gear[i][0];
						for (int j = 0; j <= 6; j++) {
							Gear[i][j] = Gear[i][j + 1];
						}
						Gear[i][7] = tmp2; // 반시계방향으로 회전
					} else {// 우측이 반시계면 좌측은 시계
						back_dir *= -1;// 방향 갱신
						int tmp2 = Gear[i][7];
						for (int j = 7; j > 0; j--) {
							Gear[i][j] = Gear[i][j - 1];
						}
						Gear[i][0] = tmp2; // 반시계방향으로 회전
					}
				} else {
					break;
				}
			}
//			for(int[] a: Gear) System.out.println(Arrays.toString(a));
//			System.out.println();
		}
		int sum = 0;
		for(int i=0 ; i<4; i++) {
			if(i==0) {
				sum+=1*Gear[i][0];
			}else if(i==1) {
				sum+=2*Gear[i][0];
			}else if(i==2) {
				sum+=4*Gear[i][0];
			}else {
				sum+=8*Gear[i][0];
			}
		}
		System.out.println(sum);
	}

}
