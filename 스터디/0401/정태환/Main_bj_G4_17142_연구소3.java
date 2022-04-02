package algoStudy.a0401;

import java.io.*;
import java.util.*;

public class Main_bj_G4_17142_연구소3 {

	static int[] delx=new int[] {-1, 0, 1, 0}; // 상우하좌
	static int[] dely=new int[] { 0, 1, 0,-1}; // 상우하좌
	
	static int N,M,mintime;
	static int[][] map;
	static ArrayList<int[]> virus;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17142"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N=Integer.parseInt(st.nextToken()); // 연구소크기
		M=Integer.parseInt(st.nextToken()); // 바이러스 개수
		
		map=new int[N][N];
		virus=new ArrayList<>();
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					map[i][j]=-1;
					virus.add(new int[] {i, j});
				}
			}
		}
		
		mintime=Integer.MAX_VALUE;
		perm(0, 0, new boolean[virus.size()]);
		
		if(mintime==Integer.MAX_VALUE) 
			mintime=-1;
		System.out.println(mintime);
		br.close();
	}
	
	static void perm(int start, int cnt, boolean[] v) {
		if(cnt==M) {
			bfs(v);
			return;
		}
		
		for(int i=start; i<virus.size(); i++)
			if(!v[i]) {
				v[i]=true;
				perm(i+1,cnt+1,v);
				v[i]=false;
			}
	}
	
	static void bfs(boolean[] v) {
		int[][] save=new int[N][N];
		for(int i=0; i<N; i++)
			save[i]=Arrays.copyOf(map[i], N);
		
		ArrayDeque<int[]> q=new ArrayDeque<>();
		for(int i=0; i<virus.size(); i++)
			if(v[i]) {
				q.add(new int[] {virus.get(i)[0], virus.get(i)[1], 0});
				save[virus.get(i)[0]][virus.get(i)[1]]=3;
			}
		
		int time=0;
		while(!q.isEmpty()) {
			int[] now=q.poll();
			
			for(int d=0; d<4; d++) {
				int dx=now[0]+delx[d], dy=now[1]+dely[d];
				if(dx<0||dx>=N||dy<0||dy>=N) continue;
				if(save[dx][dy]==-1) {
					save[dx][dy]=3;
					q.add(new int[] {dx, dy, now[2]+1});
					continue;
				}
				if(save[dx][dy]!=0) continue;
				save[dx][dy]=now[2]+1;
				q.add(new int[] {dx, dy, now[2]+1});
				time=Math.max(time, now[2]+1);
			}
		}
//		for(int[] ia:save)
//			System.out.println(Arrays.toString(ia));
//		System.out.println();
		
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				if(save[i][j]==0) return;
		mintime=Math.min(mintime, time);
	}
	
}
