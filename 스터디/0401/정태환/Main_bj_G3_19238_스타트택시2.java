package algoStudy.a0401;

import java.io.*;
import java.util.*;

public class Main_bj_G3_19238_스타트택시2 {
	
	static int[] delx=new int[] {-1, 0, 0, 1};//상좌우하
	static int[] dely=new int[] { 0,-1, 1, 0};//상좌우하

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_19238"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N=Integer.parseInt(st.nextToken()); // 맵 크기
		int M=Integer.parseInt(st.nextToken()); // 승객의 수
		int F=Integer.parseInt(st.nextToken()); // 택시의 최초 연료량
		
		int[][] map=new int[N][N];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++)
				map[i][j]=Integer.parseInt(st.nextToken());
		}
		
		st=new StringTokenizer(br.readLine()," ");
		int bjx=Integer.parseInt(st.nextToken())-1, bjy=Integer.parseInt(st.nextToken())-1;
		
		LinkedList<int[]> path=new LinkedList<>();
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int a1=Integer.parseInt(st.nextToken())-1;
			int b1=Integer.parseInt(st.nextToken())-1;
			int a2=Integer.parseInt(st.nextToken())-1;
			int b2=Integer.parseInt(st.nextToken())-1;
			map[a1][b1]=2;
			path.add(new int[] {a1,b1,a2,b2});
		}
		while(true) {
			boolean[][] v=new boolean[N][N];
			
			// 현재위치에 승객이 없으면
			// bfs로 맵에서 최단거리 승객(2)을 연료소모해가며 찾는다
			int tx=-1, ty=-1;
			if(map[bjx][bjy]!=2) {
				ArrayDeque<int[]> q=new ArrayDeque<>();
				q.add(new int[] {bjx, bjy, 0});
				v[bjx][bjy]=true;
				
				label:while(!q.isEmpty()) {
					int[] now=q.poll();
					for(int d=0; d<4; d++) {
						int dx=now[0]+delx[d], dy=now[1]+dely[d];
						if(dx<0||dx>=N||dy<0||dy>=N||v[dx][dy]||map[dx][dy]==1) continue;
						if(map[dx][dy]==2) {
							bjx=dx; bjy=dy; tx=dx; ty=dy;
							F-=now[2]+1;
							map[dx][dy]=0;
							break label;
						}
						v[dx][dy]=true;
						q.add(new int[] {dx,dy,now[2]+1});
					}
				}
			}
			if((tx==-1 && ty==-1) || F<=0) {
				F=-1;
				break;
			}
			
			// 그 승객의 목적지를 경로배열에서 찾아오면서 해당 승객 배열에서 삭제
			for(int i=0; i<path.size(); i++) {
				int[] now=path.get(i);
				if(now[0]==tx && now[1]==ty) {
					tx=now[2]; ty=now[3];
					path.remove(i);
				}
			}
			// 방문체크 배열 재생성
			v=new boolean[N][N];
			
			// 목적지로 이동, 이동중 연료 떨어지면 연료에 -1 세팅후 종료
			ArrayDeque<int[]> q=new ArrayDeque<>();
			q.add(new int[] {bjx,bjy,0});
			v[bjx][bjy]=true;
			bjx=-1; bjy=-1;
			label:while(!q.isEmpty()) {
				int[] now=q.poll();
				for(int d=0; d<4; d++) {
					int dx=now[0]+delx[d], dy=now[1]+dely[d];
					if(dx<0||dx>=N||dy<0||dy>=N||v[dx][dy]||map[dx][dy]==1) continue;
					if(dx==tx && dy==ty) {
						bjx=tx; bjy=ty;
						F-=now[2]+1;
						if(F<0) {
							System.out.println(-1);
							return;
						}
						// 목적지 도착시 이동중 소모한 연료의 2배 충전
						F+=(now[2]+1)*2;
						break label;
					}
					v[dx][dy]=true;
					q.add(new int[] {dx,dy,now[2]+1});
				}
			}
			if(bjx==-1 && bjy==-1) {
				F=-1;
				break;
			}
			
			// 남은 승객 있는지 확인 후 없으면 종료
			if(path.size()==0)
				break;
		}
		System.out.println(F);
		br.close();
	}
	
}
