package p0406;
import java.io.*;
import java.util.*;

public class Main_bj_17135_캐슬디펜스 {
	static class enemy implements Comparable<enemy>{
		int i;
		int j;
		int dis;
		
		public enemy(int i, int j, int dis) {
			this.i = i;
			this.j = j;
			this.dis = dis;
		}

		@Override
		public int compareTo(enemy o) {
			int r = this.dis-o.dis;
			if(r==0)r=this.j-o.j;
			return r;
		}
		
	}
	static int max;
	public static void main(String[] args) throws Exception{
		// 거리 : |r1-r2| + |c1-c2|
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N= Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+1][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		for(int j=0; j<M; j++) {
			map[N][j]=2;
		}
//		for(int[] a : map)System.out.println(Arrays.toString(a));
		
		max = Integer.MIN_VALUE;
		chooselocation(map, N, M, 0, new int[3], 0, d);
		System.out.println(max);
		
		br.close();
		
		

	}
	
	//조합
	static void chooselocation(int[][] map, int N , int M, int start, int[] out, int cnt, int d) {
		if(cnt==3) {
			int[][] copy = new int[N+1][M];
			for(int i=0; i<=N;i++) {
				for(int j=0; j<M; j++) {
					copy[i][j]=map[i][j];
				}
			}
			int killcount = play(out, copy, N, M, d);
			if(max<killcount)max = killcount;
			return;
		}
		
		
		for(int i=start; i<M;i++) {
			//선택
			out[cnt]=i;
			chooselocation(map, N, M, i+1, out, cnt+1, d);
			//chooselocation(map, N, M, start, out, cnt);
			
		}
	}
	static int play(int[] out, int[][] map, int N, int M, int d) {
		int killcount=0; 
		while(true) {
//			System.out.println(Arrays.toString(out));
//			for(int[] a : map)System.out.println(Arrays.toString(a));
//			System.out.println();
			
			int ecount=0;
			PriorityQueue<enemy> pq1 = new PriorityQueue<>();
			PriorityQueue<enemy> pq2 = new PriorityQueue<>();
			PriorityQueue<enemy> pq3 = new PriorityQueue<>();
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j]==1) {
						ecount++;
						int ai=N;
						int aj=out[0];
						pq1.add(new enemy(i, j, Math.abs(ai-i)+Math.abs(aj-j)));
						//2
						aj=out[1];
						pq2.add(new enemy(i, j, Math.abs(ai-i)+Math.abs(aj-j)));
						//3
						aj=out[2];
						pq3.add(new enemy(i, j, Math.abs(ai-i)+Math.abs(aj-j)));
					}
					
				}
			}
			if(ecount==0)break;
			//죽이기
			//1죽이기
			enemy e =  pq1.poll();
			if(e.dis<=d) {
				if(map[e.i][e.j]==1) {
					killcount++;
					map[e.i][e.j]=0;
				}
			}
			
			e = pq2.poll();
			if(e.dis<=d) {
				if(map[e.i][e.j]==1) {
					killcount++;
					map[e.i][e.j]=0;
				}
			}
			
			e = pq3.poll();
			if(e.dis<=d) {
				if(map[e.i][e.j]==1) {
					killcount++;
					map[e.i][e.j]=0;
				}
			}
			
			//한칸씩 아래로 
			for(int i = N-1; i>=0; i--) {
				for(int j=0; j<M; j++) {
					if(i==N-1) {
						if(map[i][j]==1) {
							//System.out.println("끝");
							map[i][j]=0;
						}
					}else {
						if(map[i][j]==1) {
							map[i+1][j]=1;
							map[i][j]=0;
						}
					}
				}
			}
		}
		
		return killcount;
	}

}
