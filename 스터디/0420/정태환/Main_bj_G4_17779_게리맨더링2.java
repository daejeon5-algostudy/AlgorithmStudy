package algoStudy.asolved.a0420;

import java.io.*;
import java.util.*;

public class Main_bj_G4_17779_게리맨더링2 {
	
	static int N, ans=Integer.MAX_VALUE, total;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17779"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				total+=map[i][j];
			}
		}
		
		for(int x=0; x<N; x++) 
			for(int y=0; y<N; y++) 
				for(int d1=1; d1<N; d1++)
					for(int d2=1; d2<N; d2++) {
						if(x+d1+d2 >= N) continue; // 아래로 초과
						if(y-d1<0 || y+d2>=N) continue; // 양옆으로 초과
						mapsetting(x,y,d1,d2);
					}
		
		System.out.println(ans);
		br.close();
	}

	static void mapsetting(int x, int y, int d1, int d2) {
		boolean[][] bmap=new boolean[N][N];
		
		for(int i=0; i<=d1; i++) {
			bmap[x+i][y-i]=true; 		// 2사분면
			bmap[x+d2+i][y+d2-i]=true;	// 4사분면 
		}
		for(int i=0; i<=d2; i++) {
			bmap[x+i][y+i]=true;		// 1사분면
			bmap[x+d1+i][y-d1+i]=true;	// 3사분면 
		}
		
		int[] section=new int[5];
		
		for(int i=0; i<x+d1; i++) { 	// 2사분면
			for(int j=0; j<=y; j++) {
				if(bmap[i][j]) break;
				section[0]+=map[i][j];
			}
		}
		for(int i=0; i<=x+d2; i++) {	// 1사분면
			for(int j=N-1; j>y; j--) {
				if(bmap[i][j]) break;
				section[1]+=map[i][j];
			}
		}
		for(int i=x+d1; i<N; i++) {		// 3사분면
			for(int j=0; j<y-d1+d2; j++) {
				if(bmap[i][j]) break;
				section[2]+=map[i][j];
			}
		}
		for(int i=x+d2+1; i<N; i++) {	// 4사분면
			for(int j=N-1; j>=y-d1+d2; j--) {
				if(bmap[i][j]) break;
				section[3]+=map[i][j];
			}
		}
		
		section[4]=total;
		for(int i=0; i<4; i++) section[4]-=section[i];
		
		Arrays.sort(section);
		ans=Math.min(ans, section[4]-section[0]);
	}
	
}
