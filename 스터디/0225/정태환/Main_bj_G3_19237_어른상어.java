package algoStudy.a0225;

import java.io.*;
import java.util.*;

public class Main_bj_G3_19237_어른상어 {

	static class shark {
		int x, y, d, num;
		int nextx, nexty, nextd;
		int[][] dset;

		public shark(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
			dset = new int[5][5];
		}
		
		public void setd(int[] set, int idx) {
			dset[idx] = set;
		}

	}
	
	static int[] delx = new int[] {0,-1, 1, 0, 0}; // X상하좌우
	static int[] dely = new int[] {0, 0, 0,-1, 1}; // X상하좌우
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_19237"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine(), " ");
		
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		LinkedList<shark> sL=new LinkedList<>();
		
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int num=Integer.parseInt(st.nextToken());
				if(num!=0) sL.add(new shark(i, j, num));
			}
		}
		Collections.sort(sL, (o1, o2)->{ return o1.num - o2.num; });
		
		st=new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<M; i++) sL.get(i).d=Integer.parseInt(st.nextToken()); 
		for(int i=0; i<M; i++)
			for(int j=1; j<5; j++) {
				st=new StringTokenizer(br.readLine(), " ");
				int[] td = new int[5];
				for(int l=1; l<5; l++)
					td[l]=Integer.parseInt(st.nextToken());
				sL.get(i).setd(td, j);
			}
		
		int[][][] map=new int[N][N][2]; // 0:상어냄새 1:상어번호
		int t = 0;
		while(true) {
			for(int i=0; i<M; i++) { // 냄새표시
				shark now=sL.get(i);
				map[now.x][now.y][0]=k;
				map[now.x][now.y][1]=now.num;
			}
			PriorityQueue<int[]> pq=new PriorityQueue<>((o1,o2)->{return o1[2]-o2[2];});
			for(int i=0; i<M; i++) {
				shark now=sL.get(i);
				for(int j=1; j<5; j++) {
					int dx=now.x+delx[now.dset[now.d][j]];
					int dy=now.y+dely[now.dset[now.d][j]];
					if(dx<0||dx>=N||dy<0||dy>=N||(map[dx][dy][0]!=0&&map[dx][dy][1]!=now.num)) continue;
					if(map[dx][dy][0]==0) {
						pq.clear();
						pq.offer(new int[]{dx,dy,j,now.dset[now.d][j]});
						break;
					}
					pq.offer(new int[]{dx,dy,j,now.dset[now.d][j]});
				}
				int[] next=pq.poll();
				pq.clear();
				now.nextx=next[0]; now.nexty=next[1]; now.nextd=next[3]; // 일단 기록만하고 이동은 동시에진행한다
			}
	 label:for(int i=0; i<M; i++) { // 이동은 동시에 진행한다
				shark now=sL.get(i);
				for(int j=i-1; j>=0; j--) { // 자신보다 큰상어가 다음위치에 있으면 삭제
					shark big=sL.get(j);
					if(big.x==now.nextx && big.y==now.nexty) {
						sL.remove(i);
						M--; i--;
						continue label;
					}
				}
				now.x=now.nextx; now.y=now.nexty; now.d=now.nextd;
			}
			for(int i=0; i<N; i++)
				for(int j=0; j<N; j++)
					if(map[i][j][0]!=0)
						if(--map[i][j][0]==0)
							map[i][j][1]=0;
			if(M==1) break;
			if(++t>=1000) {
				System.out.println(-1);
				System.exit(0);
			}
		}
		System.out.println(t);
		br.close();
	}
}
