package practice0317;
import java.io.*;
import java.util.*;
public class Main_bj_15684_사다리조작 {
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
	
		
		int N = Integer.parseInt(st.nextToken()); //세로선
		int M = Integer.parseInt(st.nextToken()); // 가로 선 수
		int H = Integer.parseInt(st.nextToken()); // 가로 몇개 넣을 수 있는가?
		
		int[][] map = new int[H+1][N+1];
		
		
		
		for(int i=0; i<M; i++) {
			st= new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			map[a][b]=1;
		}
		//for(int[] a: map)System.out.println(Arrays.toString(a));
		//System.out.println();
		solve(1, 1, H, 0, N, map);
		if(min>3)System.out.println(-1);
		else System.out.println(min);
		
		
		br.close();

	}
	// 선을 긋거나 안 긋거나 
	static void solve(int ni, int nj, int H, int cnt, int N, int[][] map) { //H가로선 가능 수, cnt 추가한 가로선 수 
		if(cnt>min)return;
		if(cnt > 3) return;
		
		if(play(map, N, H)) {
			if(cnt<min)min =cnt;
		}
		
		for(int i=ni; i<=H; i++ ) {
			for(int j = 1; j<=N; j++) {
				if(map[i][j]==0) {
					if((j-1)>=1&&(j+1)<=N) {
						if(map[i][j-1]==0 && map[i][j+1]==0) {
							map[i][j]=1;
	
							solve(i,j,H,cnt+1, N, map);
							map[i][j]=0;
							
						}
					}else if((j+1)<=N){
						if(map[i][j+1]==0) {
							map[i][j]=1;
							
							solve(i,j,H,cnt+1, N, map);
							map[i][j]=0;
						}
						
					}
				}
			}
		}
	}
	
	static boolean play(int[][] map, int N, int H) {
		//for(int[] a: map)System.out.println(Arrays.toString(a));
		for(int j=1; j<=N; j++) {
			//int ni=1;//스텝
			int nj=j;//세로선 
			
			for(int i=1; i<=H ; i++) {
				if(map[i][nj]==1) {
					nj=nj+1;
					
				}else if((nj-1)>=1&&map[i][nj-1]==1) {
					nj-=1;
				}
			}
			//System.out.println(j+" "+nj);
			if(j!=nj){
				
				return false;
			}
		}
		//System.out.println(1);
		return true;
	}

}
