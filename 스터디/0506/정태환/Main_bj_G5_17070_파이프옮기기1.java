import java.io.*;
import java.util.*;

public class Main_bj_G5_17070_파이프옮기기1 {

	static Integer[][][] memo;
	static int[] delx= { 0, 1, 1}; // 우 우하 하
	static int[] dely= { 1, 1, 0}; // 우 우하 하
	static int N;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17070"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N=Integer.parseInt(br.readLine());
		map=new int[N][N]; // 통로0 벽1
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) map[i][j]=Integer.parseInt(st.nextToken());
		}
		
		memo=new Integer[3][N][N];
		dfs(0,1,0); // 가로0 대각1 세로2 , 방향전환은 45도만, 모듈러x
		System.out.println(memo[0][0][1]);
		
		br.close();
	}

	static int dfs(int x, int y, int d) {
		if(x==N-1 && y==N-1) return 1;
		
		if(memo[d][x][y]==null) {
			memo[d][x][y]=0;
			label:
			for(int i=0; i<3; i++) {
				if((d==0 && i==2)||(d==2 && i==0)) continue;
				if(i==0 || i==2) {
					int dx=x+delx[i], dy=y+dely[i];
					if(dx<0||dx>=N||dy<0||dy>=N||map[dx][dy]==1) continue;
					memo[d][x][y]+=dfs(dx,dy,i);
				}
				else {
					for(int j=0; j<3; j++) {
						int dx=x+delx[j], dy=y+dely[j];
						if(dx<0||dx>=N||dy<0||dy>=N||map[dx][dy]==1) continue label;
					}
					memo[d][x][y]+=dfs(x+delx[i],y+dely[i],i);
				}
			}
		}
		return memo[d][x][y];
	}
	
}
