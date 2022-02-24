import java.io.*;
import java.util.*;
public class Main_bj_21608_상어초등학교_대전_5반_최원재 {
	static int[] di= new int[]  {-1,0,1,0}; //상 우 하 좌
	static int[] dj = new int[] {0,1,0,-1};
	static ArrayList<Dot> dot;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N =Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		int[][] input = new int[N*N][5];
		
		int result = 0;
		
		for(int i=0; i<N*N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<5;j++) {
				input[i][j]=Integer.parseInt(st.nextToken());
			}
		}
	
		map[1][1] = input[0][0];
		for(int n=1; n<N*N; n++) {    //모든 학생 1~ 9
			 dot= new ArrayList<>();
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]!=0)continue;
					int count=0;
					int blcount=0;
					for(int d = 0 ; d<4; d++) {
						
						int ni = i+di[d];
						int nj = j+dj[d];
						if(0<=ni&&ni<N&&0<=nj&&nj<N) {
							if(map[ni][nj]==0)blcount++;
							for(int c=1; c<=4;c++) {
								if(input[n][c]==map[ni][nj])count++;
							}
						}
					}//사방 탐색 후 나오는 곳 최종적으로 주변 공백 수 친구 수 결정 
					Dot d = new Dot(count, blcount,i,j);//{i,j}에서 
					
					dot.add(d);
					
					
				}
			}//모든 자리 확인 후 탈출
			Collections.sort(dot);
			map[dot.get(0).i][dot.get(0).j]=input[n][0];
			
							
		}
		Arrays.sort(input, new Comparator<int[]>(){

			@Override
			public int compare(int[] o1, int[] o2) {
				
				return o1[0]-o2[0];
			}
			
		});
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int count=0;
				for(int d = 0 ; d<4; d++) {
					
					int ni = i+di[d];
					int nj = j+dj[d];
					if(0<=ni&&ni<N&&0<=nj&&nj<N) {
						for(int c=1; c<=4;c++) {
							if(input[map[i][j]-1][c]==map[ni][nj])count++;
						}
					}
				}//사방 탐색 후
				if(count==1)result+=1;
				else if(count==2)result+=10;
				else if(count==3)result+=100;
				else if(count==4)result+=1000;
			
			}
		}

		System.out.println(result);
		br.close();
		
	
		
	}
	static class Dot implements Comparable<Dot>{
		int friendcount;
		int blcount; // 주변 빈공간
		int i;//열
		int j;//행
		
		public Dot(int friendcount, int blcount, int i, int j) {
			super();
			this.friendcount = friendcount;
			this.blcount = blcount;
			this.i = i;
			this.j = j;
		}

		@Override
		public int compareTo(Dot o) {
			if(friendcount!=o.friendcount) {
				return o.friendcount-this.friendcount;
			}else if(blcount != o.blcount) {
				return o.blcount - this.blcount;
			}else if(i!=o.i) {
				return this.i-o.i;
			}else {
				return this.j-o.j;
			}
			
		}
	}
}
