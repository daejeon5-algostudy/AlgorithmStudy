package practice0310;
import java.io.*;
import java.util.*;
public class Main_bj_14500_테트로미노_대전_5반_최원재 {
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};//상우하좌
	static int max = -1;
	static boolean[][] v;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		v= new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				v[i][j]=true;
				solve(i, j,0, 0, N, M, map);
				solve2(i, j, N, M, map);
				v[i][j]=false;
			}
		}
		
		
		System.out.println(max);
		
		br.close();

	}
	static void solve(int i, int j, int cnt, int sum, int N , int M, int[][] map) {
		if(cnt==3) {
			//System.out.println(ii+" "+jj +" "+cnt+" "+sum);
			sum+=map[i][j];
			if(sum>max)max=sum;
			//System.out.println(i+" "+j +" "+cnt+" "+sum);
			//for(boolean[] a: v)System.out.println(Arrays.toString(a));
			//System.out.println("------------------------");
			
			return;
		}
		
		
		
		for(int d=0; d<4; d++) {
			int ni=i+di[d];
			int nj = j+dj[d];
			
			if(0<=ni&&ni<N&&0<=nj&&nj<M&&!v[ni][nj]) {
				v[ni][nj]=true;
				solve(ni, nj, cnt+1, sum+map[i][j], N , M , map );
				v[ni][nj]=false;
				//System.out.println(i+" "+j +" "+cnt+" "+(sum+map[i][j]));
				
			}
		}
		
	}
	
	// ㅗ ㅏ ㅜ ㅓ 
	static void solve2(int i, int j,  int N , int M, int[][] map) {
		int sum1=map[i][j];
		int sum2=map[i][j];
		int sum3=map[i][j];
		int sum4=map[i][j];
		
		
		for(int d=0; d<4;d++) {
			if(d==0)continue;
			int ni = i+di[d];
			int nj = j+dj[d];
			if(0<=ni&&ni<N&&0<=nj&&nj<M)sum1+=map[ni][nj];
			else break;
		}
		for(int d=0; d<4;d++) {
			if(d==1)continue;
			int ni = i+di[d];
			int nj = j+dj[d];
			if(0<=ni&&ni<N&&0<=nj&&nj<M)sum2+=map[ni][nj];
			else break;
		}
		for(int d=0; d<4;d++) {
			if(d==2)continue;
			int ni = i+di[d];
			int nj = j+dj[d];
			if(0<=ni&&ni<N&&0<=nj&&nj<M)sum3+=map[ni][nj];
			else break;
		}
		for(int d=0; d<4;d++) {
			if(d==3)continue;
			int ni = i+di[d];
			int nj = j+dj[d];
			if(0<=ni&&ni<N&&0<=nj&&nj<M)sum4+=map[ni][nj];
			else break;
		}
		if(sum1>max)max=sum1;
		if(sum2>max)max=sum2;
		if(sum3>max)max=sum3;
		if(sum4>max)max=sum4;
	}

}
