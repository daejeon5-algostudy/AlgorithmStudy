package algoStudy.asolved.a0421;

import java.io.*;
import java.util.*;

public class Main_bj_G4_2638_치즈 {

	static int[] delx= {-1, 0, 1, 0};
	static int[] dely= { 0, 1, 0,-1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2638"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		int map[][]=new int[N][M];
		
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) map[i][j]=Integer.parseInt(st.nextToken());
		}
		
		int ans=0;
		label:
		while(true) {
			ans++;
			// 만나는면 체크
			int cmap[][]=new int[N][M];
			boolean v[][]=new boolean[N][M];
			ArrayDeque<int[]> q=new ArrayDeque<>();
			q.offer(new int[] {0, 0});
			while(!q.isEmpty()) {
				int[] now=q.poll();
				
				if(v[now[0]][now[1]]) continue;
				v[now[0]][now[1]]=true;
				
				for(int d=0; d<4; d++) {
					int dx=now[0]+delx[d], dy=now[1]+dely[d];
					if(dx<0||dx>=N||dy<0||dy>=M) continue;
					
					if(map[dx][dy]==1) cmap[dx][dy]++;
					else 		 	   q.offer(new int[] {dx, dy});
				}
			}
			
			// 2이상 삭제
			for(int i=0; i<N; i++)
				for(int j=0; j<M; j++)
					if(cmap[i][j]>=2) map[i][j]=0;
		
			
			// 다없어졌는지?
			for(int i=0; i<N; i++)
				for(int j=0; j<M; j++)
					if(map[i][j]==1) continue label;
			break;
		}
		
		System.out.println(ans);
		br.close();
	}
}
