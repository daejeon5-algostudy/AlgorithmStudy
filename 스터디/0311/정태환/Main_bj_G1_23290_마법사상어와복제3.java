package algoStudy.a0311;

import java.io.*;
import java.util.*;

public class Main_bj_G1_23290_마법사상어와복제3 {
	
	static class Fish {
		int x,y,d;

		public Fish(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		
	}
	
	static int[][] smell=new int[4][4];
	static int[] delx=new int[] { 0,-1,-1,-1, 0, 1, 1, 1}; // 좌 좌상 상 우상 우 우하 하 좌하
	static int[] dely=new int[] {-1,-1, 0, 1, 1, 1, 0,-1};
	static int[] sharkx=new int[] {-1, 0, 1, 0}; // 상 좌 하 우
	static int[] sharky=new int[] { 0,-1, 0, 1};
	static Fish shark=new Fish(-1, -1, -1);
	static ArrayList<Integer>[][] map=new ArrayList[4][4];
	static ArrayList<Fish> fl=new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_23290"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
		int M=Integer.parseInt(st.nextToken()); // 물고기의 수
		int S=Integer.parseInt(st.nextToken()); // 마법을 연습한 횟수
		
		for(int i=0; i<4; i++) for(int j=0; j<4; j++) // 맵 생성
				map[i][j]=new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int x=Integer.parseInt(st.nextToken())-1;
			int y=Integer.parseInt(st.nextToken())-1;
			int d=Integer.parseInt(st.nextToken())-1;
			map[x][y].add(d);
		}
		
		st=new StringTokenizer(br.readLine()," "); // 상어 위치 세팅
		shark.x=Integer.parseInt(st.nextToken())-1;
		shark.y=Integer.parseInt(st.nextToken())-1;
		
		for(int tc=0; tc<S; tc++) {
			// 마법복사
			copy();
			// 물고기 이동
			moveFish();
			// 상어 이동
			moveShark();
			// 냄새 제거
			removeSmell();
			// 복사마법완료
			magicSum();
		}
		int ans=count();
		System.out.println(ans);
	}
	
	static void moveShark() {
		boolean[][] v;
		int nowx=shark.x, nowy=shark.y;
		int fishNum=0, maxFishNum=0;
		int move=0, maxMove=0;
		boolean end=false;
		
		for(int i=0; i<4; i++) {
			v=new boolean[4][4];
			nowx+=sharkx[i];
			nowy+=sharky[i];
			if(nowx<0||nowx>3||nowy<0||nowy>3) {
				nowx=shark.x;
				nowy=shark.y;
				continue;
			}
			move=i;
			v[nowx][nowy]=true;
			fishNum=map[nowx][nowy].size();
			for(int j=0; j<4; j++) {
				nowx+=sharkx[j];
				nowy+=sharky[j];
				if(nowx<0||nowx>3||nowy<0||nowy>3) {
					nowx-=sharkx[j];
					nowy-=sharky[j];
					continue;
				}
				move=(move*10)+j;
				if(!v[nowx][nowy]) fishNum += map[nowx][nowy].size();
				for(int k=0; k<4; k++) {
					nowx+=sharkx[k];
					nowy+=sharky[k];
					if(nowx<0||nowx>3||nowy<0||nowy>3) {
						nowx-=sharkx[k];
						nowy-=sharky[k];
						continue;
					}
					
					if(!v[nowx][nowy]) fishNum+=map[nowx][nowy].size();
					int finalFishNum=fishNum;
					if(!v[nowx][nowy]) fishNum-=map[nowx][nowy].size();
					
					nowx-=sharkx[k];
					nowy-=sharky[k];
					
					if(end && maxFishNum >= finalFishNum) continue;
					move=(move*10)+k;
					maxMove=move;
					maxFishNum=finalFishNum;
					end=true;
					move/=10;
				}
				if(!v[nowx][nowy]) fishNum-=map[nowx][nowy].size();
				v[nowx][nowy]=false;
				nowx-=sharkx[j];
				nowy-=sharky[j];
				move/=10;
			}
			v[nowx][nowy]=false;
			nowx=shark.x;
			nowy=shark.y;
		}
		nowx=shark.x;
		nowy=shark.y;
		int idx=2;
		while(idx>=0) {
			int base=(int)Math.pow(10, idx--);
			int m=maxMove/base;
			maxMove%=base;
			
			nowx+=sharkx[m];
			nowy+=sharky[m];
			
			if(map[nowx][nowy].size()==0) continue;
			smell[nowx][nowy]=3;
			map[nowx][nowy].clear();
		}
		shark.x=nowx;
		shark.y=nowy;
	}

	static void moveFish() {
		ArrayList<Integer>[][] save=new ArrayList[4][4];
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++)
				save[i][j]=new ArrayList<>();
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				for(int d:map[i][j]) {
					int dx=i, dy=j;
					boolean moved=false;
					for(int k=0; k<8; k++) {
						int nowd=((d-k)+8)%8;
						dx=i+delx[nowd];
						dy=j+dely[nowd];
						if(dx<0||dx>3||dy<0||dy>3||smell[dx][dy]!=0||(dx==shark.x && dy==shark.y)) continue;
						save[dx][dy].add(nowd);
						moved=true;
						break;
					}
					if(!moved)
						save[i][j].add(d);
				}
			}
		}
		map=save;
	}

	static void copy() {
		fl.clear();
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++)
				for(int d:map[i][j])
					fl.add(new Fish(i, j, d));
	}

	static void removeSmell() {
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++)
				if(smell[i][j]!=0)
					smell[i][j]--;
	}
	
	static void magicSum() {
		for(Fish f:fl) {
			map[f.x][f.y].add(f.d);
        }
	}

	static int count() {
		int cnt=0;
		for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
            	cnt+=map[i][j].size();
            }
        }
        return cnt;
	}
	
}
