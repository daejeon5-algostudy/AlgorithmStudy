package practice0303;
import java.io.*;
import java.util.*;
public class Main_bj_21610_마법사상어와비바라기3 {
	static int[] di = {0,-1,-1,-1,0,1,1,1};
	static int[] dj = {-1,-1,0,1,1,1,0,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N= Integer.parseInt(st.nextToken());//격자 칸
		int M = Integer.parseInt(st.nextToken());//이동 수
		
		int[][] map = new int[N][N];
		boolean[][][] cloud = new boolean[N][N][2];//cur future
		
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		cloud[N-1][0][0]=true;
		cloud[N-1][1][0]=true;
		cloud[N-2][0][0]=true;
		cloud[N-2][1][0]=true;

		for(int t=0; t<M ; t++) {
		
			st = new StringTokenizer(br.readLine()," ");
			int d = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			//구름 이동
			for(int i=0; i<N;i++) {
				for(int j=0; j<N;j++) {
					if(cloud[i][j][0]) {
						int ni=i;
						int nj=j;
						for(int c=0;c<s;c++) {
							
							ni+=di[d];
							nj+=dj[d];
							
							if(ni<0)ni+=N;
							if(nj<0)nj+=N;
							if(ni>=N)ni-=N;
							if(nj>=N)nj-=N;
							
						
							
						}
						cloud[ni][nj][1]=true;
					}
				}
			}
			
			for(int i=0; i<N;i++) {
				for(int j=0; j<N; j++) {
					cloud[i][j][0]=cloud[i][j][1];
					cloud[i][j][1]=false;
				}
			}
			//구름이 있는 곳에 물의 양 1 증가
			for(int i=0; i<N;i++) {
				for(int j=0; j<N; j++) {
					if(!cloud[i][j][0])continue;
					map[i][j]++;
				}
			}
//			System.out.println("구름이동");	
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(cloud[i][j][0]+" "+cloud[0][0][1]+"     ");
//				}
//				System.out.println();
//			}
			
			for(int i=0; i<N;i++) {
				for(int j=0; j<N; j++) {
					if(!cloud[i][j][0])continue;
					int ci = i;
					int cj = j;
					int count = 0;
					if(0<=ci+1&&ci+1<N&&0<=cj+1&cj+1<N&&map[ci+1][cj+1]>0)count++;
					if(0<=ci+1&&ci+1<N&&0<=cj-1&cj-1<N&&map[ci+1][cj-1]>0)count++;
					if(0<=ci-1&&ci-1<N&&0<=cj+1&cj+1<N&&map[ci-1][cj+1]>0)count++;
					if(0<=ci-1&&ci-1<N&&0<=cj-1&cj-1<N&&map[ci-1][cj-1]>0)count++;
					
					map[ci][cj]+=count;
					
				}
			}
			

			
			
		
			
			for(int i=0; i<N;i++) {
				for(int j=0; j<N; j++) {
					if(cloud[i][j][0])cloud[i][j][0]=false;
					else {
						if(map[i][j]>=2) {
							cloud[i][j][0]=true;
							map[i][j]-=2;
						}
					}
				}
			}
			
//		System.out.println();	
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(cloud[i][j][0]+" "+cloud[0][0][1]+"     ");
//			}
//			System.out.println();
//		}
//			
//		System.out.println();	
//		for(int[] a: map )System.out.println(Arrays.toString(a));	
		
			
			
			
		}
		int sum=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N;j++) {
				sum+=map[i][j];
			}
		}
		
		System.out.println(sum);
		br.close();

	}

}
