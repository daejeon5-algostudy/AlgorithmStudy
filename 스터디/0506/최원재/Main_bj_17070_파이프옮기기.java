package p05;
import java.io.*;
import java.util.*;

public class Main_bj_17070_파이프옮기기 {
	
	//우 우하 하 
	static int[] di= {0,1,1};
	static int[] dj= {1,1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				
			}
		}
		int[][][] dp = new int[N][N][3];
		dp[0][1][0]=1;
		for(int i=0; i<N;i++) {
			for(int j=0; j<N; j++) {
				if((i==0&&j==0)||(i==0&&j==1))continue;
				if(map[i][j]==0) {
					
					//우 우하 하 
					//가로
					//좌 부분에서-가로 대각선 체크
					int ni=i-di[0];
					int nj=j-dj[0];
					
					if(0<=ni&&ni<N&&0<=nj&&nj<N&&map[ni][nj]==0) {
						
						dp[i][j][0]=dp[ni][nj][0]+dp[ni][nj][2];
						
					}
				
					//세로
					//상 부분에서--세로 대각 체크  
					ni=i-di[2];
					nj=j-dj[2];
					if(0<=ni&&ni<N&&0<=nj&&nj<N&&map[ni][nj]==0) {
						dp[i][j][1]=dp[ni][nj][1]+dp[ni][nj][2];
					}
					//대각
					//상 좌 체크 
					//좌상 부분에서 --가로 세로 대각 체크
					ni=i-di[1];
					nj=j-dj[1];
					int ui=i-di[2];//상
					int uj=j-dj[2];
					int li=i-di[0];//좌
					int lj=j-dj[0];
					if(0<=ui&&ui<N&&0<=uj&&uj<N&&map[ui][uj]==0&&0<=li&&li<N&&0<=lj&&lj<N&&map[li][lj]==0) {
						if(0<=ni&&ni<N&&0<=nj&&nj<N&&map[ni][nj]==0) {
							dp[i][j][2]=dp[ni][nj][0]+dp[ni][nj][1]+dp[ni][nj][2];
						}
					}
				}
				
			}
		}

		System.out.println(dp[N-1][N-1][0]+dp[N-1][N-1][2]+dp[N-1][N-1][1]);
		br.close();

	}

}
