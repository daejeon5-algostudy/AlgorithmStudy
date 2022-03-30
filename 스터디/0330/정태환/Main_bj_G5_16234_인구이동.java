package algoStudy.a0330;

import java.io.*;
import java.util.*;

public class Main_bj_G5_16234_인구이동 {
	
	static int[] delx=new int[] {-1, 0, 1, 0}; // 상우하좌
	static int[] dely=new int[] { 0, 1, 0,-1}; // 상우하좌

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_16234"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
		int N=Integer.parseInt(st.nextToken());
		int L=Integer.parseInt(st.nextToken());
		int R=Integer.parseInt(st.nextToken());
		
		int[][] map=new int[N][N];
		
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++)
				map[i][j]=Integer.parseInt(st.nextToken());
		}
		
		int time=0;
		while(true) {
			// 인구차이 계산후 묶기
			int[][] ch=new int[N][N];
			int cnt=1;
			boolean moved=false;
			for(int i=0;i<N;i++)
				for(int j=0;j<N;j++)
					if(ch[i][j]==0) {
						ArrayDeque<int[]> q=new ArrayDeque<>();
						q.add(new int[] {i,j});
						ch[i][j]=cnt;
						while(!q.isEmpty()) {
							int[] now=q.poll();

							for(int d=0; d<4; d++) {
								int dx=now[0]+delx[d], dy=now[1]+dely[d];
								if(dx<0||dx>=N||dy<0||dy>=N||ch[dx][dy]!=0) continue;
								int diff=Math.abs(map[now[0]][now[1]]-map[dx][dy]);
								if(diff<L || diff>R) continue;
								moved=true;
								ch[dx][dy]=cnt;
								q.add(new int[] {dx, dy});
							}
						}
						cnt++;
					}
			// 인구이동
			boolean[][] v=new boolean[N][N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!v[i][j]) {
						int sum=0;
						ArrayList<int[]> team=new ArrayList<>();
						ArrayDeque<int[]> q=new ArrayDeque<>();
						team.add(new int[] {i,j});
						
						q.add(new int[] {i,j});
						v[i][j]=true;
						sum+=map[i][j];
						// 큐돌면서 팀찾기
						while(!q.isEmpty()) {
							int[] now=q.poll();
							
							for(int d=0; d<4; d++) {
								int dx=now[0]+delx[d], dy=now[1]+dely[d];
								if(dx<0||dx>=N||dy<0||dy>=N||ch[i][j]!=ch[dx][dy]||v[dx][dy]) continue;
								sum+=map[dx][dy];
								v[dx][dy]=true;
								team.add(new int[] {dx,dy});
								q.add(new int[] {dx,dy});
							}
						}
						// 팀들 인구이동시키기
						sum/=team.size();
						for(int k=0; k<team.size(); k++) {
							int[] now=team.get(k);
							map[now[0]][now[1]]=sum;
						}
					}
				}
			}
			
			// 만약 모든 국가가 하나로 묶였다면 종료
			if(cnt==2) {
				if(moved) time++; // 인구이동이 일어났을경우만 증가
				break;
			}
			// 하나도 안묶이면 종료
			if(cnt==N*N+1) {
				break;
			}
			time++;
		}
		System.out.println(time);
	}

}
