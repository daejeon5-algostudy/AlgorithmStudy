package algoStudy.asolved.a0421;

import java.io.*;
import java.util.*;

public class Main_bj_G4_5427_불 {
	
	static int[] delx= {-1, 0, 1, 0};
	static int[] dely= { 0, 1, 0,-1};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_5427"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st;
		
		int T=Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st=new StringTokenizer(br.readLine(), " ");
			int w=Integer.parseInt(st.nextToken());
			int h=Integer.parseInt(st.nextToken());
			char[][] map=new char[h][w];
			boolean[][] nv=new boolean[h][w];
			
			ArrayDeque<int[]> fire=new ArrayDeque<>();
			ArrayDeque<int[]> q=new ArrayDeque<>();
			for(int i=0; i<h; i++) {
				char[] ca=br.readLine().toCharArray();
				for(int j=0; j<w; j++) {
					map[i][j]=ca[j];
					if(map[i][j]=='@') {
						q.offer(new int[] {i,j,0});
						nv[i][j]=true;
					} else if(map[i][j]=='*') {
						fire.offer(new int[] {i,j});
					}
				}
			}
					
			int ans=0;
			label:
			while(!q.isEmpty()) {
				// 불 확산
				int n=fire.size();
				for(int i=0; i<n; i++) {
					int[] nfire=fire.poll();
					for(int d=0; d<4; d++) {
						int dx=nfire[0]+delx[d], dy=nfire[1]+dely[d];
						if(dx<0||dx>=h||dy<0||dy>=w) continue;
						if(map[dx][dy]=='.') {
							fire.offer(new int[] {dx, dy});
							map[dx][dy]='*';
						}
					}
				}
				// 이동
				n=q.size();
				for(int i=0; i<n; i++) {
					int[] now=q.poll();
					for(int d=0; d<4; d++) {
						int dx=now[0]+delx[d], dy=now[1]+dely[d];
						if(dx<0||dx>=h||dy<0||dy>=w) {
							ans=now[2]+1;
							break label;
						}
						if(map[dx][dy]=='.' && !nv[dx][dy]) {
							nv[dx][dy]=true;
							q.offer(new int[] {dx,dy,now[2]+1});
						}
					}
				}
			}
			
			if(ans==0) sb.append("IMPOSSIBLE");
			else 	   sb.append(ans);
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	
}
