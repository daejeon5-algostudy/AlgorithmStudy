import java.io.*;
import java.util.*;

public class Main {
	
	static int N, total, ans;
	static int[][] map;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_bj_17779.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		StringTokenizer st;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = Integer.MAX_VALUE;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				for(int d1=1; d1<=N; d1++) {
					for(int d2=1; d2<=N; d2++) {
						if(i+d1+d2 > N || j+d2 > N || j-d1 < 1) continue;
						process(i, j, d1, d2);
					}
				}
			}
		}
		System.out.println(ans);
	}
	
	static void process(int x, int y, int d1, int d2) {
		int[][] tmp = new int[N+1][N+1];
		// 5번 구역 경계
		for(int i=0; i<=d1; i++) {
			tmp[x+i][y-i] = 5;
		}
		
		for(int i=0; i<=d2; i++) {
			tmp[x+i][y+i] = 5;
		}
		
		for(int i=0; i<=d2; i++) {
			tmp[x+d1+i][y-d1+i] = 5;
		}
		
		for(int i=0; i<=d1; i++) {
			tmp[x+d2+i][y+d2-i] = 5;
		}
		
		// 경계선 안쪽
		boolean start = false;
		for(int i=x+1; i<x+d1+d2; i++) {
			for(int j=1; j<=N; j++) {
				if(start) {
					if(tmp[i][j] == 5) {
						start = false;
						break;
					}
					tmp[i][j] = 5;
					continue;
				}
				
				if(tmp[i][j] == 5) {
					start = true;
				}
			}
		}
		
		// 1번 구역
		for(int a=1; a<x+d1; a++) {
			for(int b=1; b<=y; b++) {
				if(tmp[a][b] != 0) continue;
				tmp[a][b] = 1;
			}
		}
		
		// 2번 구역
		for(int a=1; a<=x+d2; a++) {
			for(int b=y+1; b<=N; b++) {
				if(tmp[a][b] != 0) continue;
				tmp[a][b] = 2;
			}
		}
		
		// 3번 구역
		for(int a=x+d1; a<=N; a++) {
			for(int b=1; b<y-d1+d2; b++) {
				if(tmp[a][b] != 0) continue;
				tmp[a][b] = 3;
			}
		}
		
		// 4번 구역
		for(int a=x+d2+1; a<=N; a++) {
			for(int b=y-d1+d2; b<=N; b++) {
				if(tmp[a][b] != 0) continue;
				tmp[a][b] = 4;
			}
		}
		
		int[] sum = new int[6];
		for(int a=1; a<=N; a++) {
			for(int b=1; b<=N; b++) {
				sum[tmp[a][b]] += map[a][b];
			}
		}
		
		int maxValue=0, minValue=Integer.MAX_VALUE;
		for(int i=1; i<=5; i++) {
			maxValue = Math.max(maxValue, sum[i]);
			minValue = Math.min(minValue, sum[i]);
		}
		
		ans = Math.min(ans, maxValue-minValue);
	}
	
}
