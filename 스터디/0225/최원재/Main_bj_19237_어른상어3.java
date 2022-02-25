import java.io.*;
import java.util.*;

public class Main_bj_19237_어른상어3 {
	static int[] di= {0,-1,1,0,0};
	static int[] dj= {0,0,0,-1,1};
	static class Shark{
		int num;//상어 번호
		int i, j; // 위치
		int dir;		
		public Shark(int num, int i, int j,int dir) {
			this.num = num;
			this.i = i;
			this.j = j;
			this.dir = dir;				
		}	
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N =Integer.parseInt(st.nextToken());//격자 크기
		int M=Integer.parseInt(st.nextToken());//상어 수 
		int K = Integer.parseInt(st.nextToken());
		
		int[][] smelltime = new int[N][N];
		int[][] smell = new int[N][N];
		int[][][] priority = new int[M+1][5][4];
		Shark[] shark = new Shark[M+1];
		
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				int data = Integer.parseInt(st.nextToken());
				if(data!=0) {
					smell[i][j]=data; // 어떤 상어의 냄새?
					smelltime[i][j]=K;
					shark[data]=new Shark(data, i, j, -1);
				}				
			}
		}
				
		st = new StringTokenizer(br.readLine()," ");
		
		//처음 설정 방향 저장
		for(int i=1; i<=M;i++) {
			shark[i].dir=Integer.parseInt(st.nextToken());
		}
		for(int m=1; m<=M ;m++) {
			for(int i=1;i<=4;i++) {
				st= new StringTokenizer(br.readLine()," ");
				for(int j=0;j<4;j++) {
					priority[m][i][j]=Integer.parseInt(st.nextToken());
				}
			}
		}
		int time = 0;
		while(true) {
			int count = 0;
			for(int m=1; m<=M;m++) {
				if(shark[m]!=null)count++;
			}
			if(count==1)break;
			if(time>1000)break;
					
			//이동 시키기 
			for(int m=1;m<=M;m++){
				boolean check = false;
				int ni=0, nj=0;
				int nd=0;
				if(shark[m]!=null) {
					for(int d=0; d<4; d++) {
						nd = priority[m][shark[m].dir][d];
						ni = shark[m].i+di[nd]; //m상어의 우선순위 중에에서 m상어의 현재 가리키는 방향의 첫번째 우선순위부터 탐색
						nj = shark[m].j+dj[nd];
						if(0<=ni&&ni<N&&0<=nj&&nj<N&&smell[ni][nj]==0) {
							check = true;
							break;
						}
					}
					if(!check) {
						for(int d=0; d<4; d++) {
							nd = priority[m][shark[m].dir][d];
							ni = shark[m].i+di[nd]; //m상어의 우선순위 중에에서 m상어의 현재 가리키는 방향의 첫번째 우선순위부터 탐색
							nj = shark[m].j+dj[nd];
							if(0<=ni&&ni<N&&0<=nj&&nj<N&&smell[ni][nj]==m) {
								break;
							}
						}
					}
				//이동 			
					shark[m].i=ni;
					shark[m].j=nj;
					shark[m].dir = nd;
				}			
			//상어 모두 이동 완료 							
			}
			//중복 위치의 상어 하나 제거
			for(int i=1;i<M;i++) {
				if(shark[i]==null)continue;
				for(int j=i+1;j<=M;j++) {
					if(shark[j]==null)continue;
					if(shark[i].i==shark[j].i&&shark[i].j==shark[j].j) {
						shark[j]=null;
					}
				}
			}
			//원래 있던 냄새 시간 갱신
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(smell[i][j]>0) {
						smelltime[i][j]--;
						if(smelltime[i][j]==0)smell[i][j] = 0;
					}
					
				}
			}
			//smell smellcount에서 이동 반영
			for(int m=1;m<=M;m++) {
				if(shark[m]!=null) {
					smell[shark[m].i][shark[m].j]=m;
					smelltime[shark[m].i][shark[m].j]=K;
				}
			}
			
			time++;
		}
		if(time>1000)System.out.println(-1);
		else System.out.println(time);
		br.close();		
	}
}
