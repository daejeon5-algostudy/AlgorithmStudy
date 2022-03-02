package practice0301;
import java.io.*;
import java.util.*;
public class Main_bj_19236_청소년상어 {
	static int[] di = { -1, -1, 0, 1, 1, 1, 0, -1};// 상, 좌상, 좌, 좌하, 하, 우하, 우, 우상,
	static int[] dj = { 0, -1, -1, -1, 0, 1, 1, 1};
	static class pisces{
		int num ; // 상어는 -1로 하자
		int dir; //방향		
		public pisces(int num, int dir) {
			super();
			this.num = num;
			this.dir = dir;
		}	
	}
	static int max =Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		pisces[][] map = new pisces[4][4];
		for(int i=0; i<4;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken())-1;
				map[i][j]=new pisces(num, dir);
			}
		}
		int eat = map[0][0].num; 
		map[0][0]=new pisces(-1, map[0][0].dir);
			
		solve(map, eat);
		System.out.println(max);
		br.close();

	}
	
	
	static void solve(pisces[][] map, int eat) {
		pisces[][] copy = new pisces[4][4];
		for(int i=0; i<4;i++) {
			for(int j=0; j<4; j++) {
				if(map[i][j]==null)copy[i][j]=null;
				else copy[i][j]=new pisces(map[i][j].num, map[i][j].dir);
			}
		}

		fishmove(copy);

		//상어 위치 찾기 
		int si=-1;
		int sj=-1;
		found:for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(copy[i][j]==null )continue;
				if(copy[i][j].num==-1) {
					si=i;
					sj=j;
					break found;
				}
			}
		}
		for(int d=1; d<4;d++) {
			int ni = si+di[copy[si][sj].dir]*d;
			int nj = sj+dj[copy[si][sj].dir]*d;
			
			if(0<=ni&&ni<4&&0<=nj&&nj<4&&copy[ni][nj]!=null) {
				pisces fish = copy[ni][nj];
				pisces shark = copy[si][sj];
				copy[ni][nj]=copy[si][sj];
				copy[ni][nj]=new pisces(-1, fish.dir);
				copy[si][sj]=null;
				solve(copy, eat+fish.num);
				copy[si][sj]=shark;
				copy[ni][nj]=fish;
			}
			
		}
		if(eat>max)max = eat;
	}
	static void fishmove(pisces[][] map) {
		label:for(int n = 1; n<=16; n++) {
			//물고기 번호 순으로 이동하기 위해 n 이용 
			
			//탐색 시작 
			for(int i=0; i<4; i++ ) {
				for(int j=0; j<4; j++) {
					if(map[i][j]==null)continue; //비어잇으면 넘기기 
					if(map[i][j].num==n) {
						while(true) {
							int ni=i+di[map[i][j].dir];
							int nj = j+dj[map[i][j].dir];
							if(0<=ni && ni < 4 && 0<=nj&& nj< 4) { //물고기 이동 방향이 범위 안에 있고 
								if(map[ni][nj]==null) {//비어 있다면?
									map[ni][nj]=map[i][j]; //이동시키고
									map[i][j] = null; //이전 위치는 빈 공간으로  
									continue label; //n을 증가 시켜서 다음 물고기 변경 할 것 찾기
								}else if(map[ni][nj].num!=-1) { //이동 위치가 비어 있지도 않고 상어도 아니라면?
									pisces tmp = map[ni][nj];
									map[ni][nj]=map[i][j];
									map[i][j]=tmp; //서로 위치 교환
									continue label; //n을 증가 시켜서 다음 물고기 변경 할 것 찾기
								}else {//상어라면 ?
									map[i][j].dir=(map[i][j].dir+1)%8; //방향 45도 돌리기
									
								}
							}else { //이동하려는 곳이 공간 바깥이라면?
								map[i][j].dir=(map[i][j].dir+1)%8; //방향 45도 돌리기
							}
									
						}
					}
				}
			}		
		}
	}
}
