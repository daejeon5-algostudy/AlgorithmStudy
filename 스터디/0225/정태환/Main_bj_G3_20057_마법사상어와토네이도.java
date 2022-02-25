package algoStudy.a0225;

import java.io.*;
import java.util.*;

public class Main_bj_G3_20057_마법사상어와토네이도 {

	static int N, sand, map[][];
	static int[] deltax = new int[] { 0, 1, 0,-1}; // 좌하우상
	static int[] deltay = new int[] {-1, 0, 1, 0};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_20057"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N=Integer.parseInt(br.readLine());
		map=new int[N][N];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				sand += map[i][j];
			}
		}
		
		int x=N/2, y=N/2, d=0;
		int ml=1, mc=0, mt=0;
		while(true) {
			x+=deltax[d]; y+=deltay[d];
			tornado(x,y,d);
			if(++mt==ml) {
				if(++mc==2) {
					ml++; mc=0;
				}
				mt=0;
				d=(d+1)%4;
			}
//			System.out.println(x + " " + y + " " + ml + " " + mc + " " + mt + " " + d);
//			System.out.println(cnt++);
			if(x==0 && y==0) break;
		}
		
		int sum=0;
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				sum+=map[i][j];
		System.out.println(sand-sum);
//		System.out.println(sand);
//		System.out.println(sum);
//		for(int[] ia:map)
//			System.out.println(Arrays.toString(ia));
	}
	
	static void tornado(int x, int y, int d) {
		int sand=map[x][y];
		map[x][y]=0;
		int wind=0;
		
		int tmp=(int) Math.floor(sand*0.05);
		int dx=x+deltax[d]*2, dy=y+deltay[d]*2;
		if(dx>=0 && dx<N && dy>=0 && dy<N) map[dx][dy]+=tmp; // 정면 5%
		wind+=tmp;
		
		tmp=(int) Math.floor(sand*0.1);
		dx=x+deltax[d]+deltax[(d+1)%4]; dy=y+deltay[d]+deltay[(d+1)%4];
		if(dx>=0 && dx<N && dy>=0 && dy<N) map[dx][dy]+=tmp; // 좌하10%
		wind+=tmp;
		dx=x+deltax[d]+deltax[(d+3)%4]; dy=y+deltay[d]+deltay[(d+3)%4];
		if(dx>=0 && dx<N && dy>=0 && dy<N) map[dx][dy]+=tmp; // 좌상10%
		wind+=tmp;
		
		tmp=(int) Math.floor(sand*0.07);
		dx=x+deltax[(d+3)%4]; dy=y+deltay[(d+3)%4]; 
		if(dx>=0 && dx<N && dy>=0 && dy<N) map[dx][dy]+=tmp; // 상 7%
		wind+=tmp;
		dx=x+deltax[(d+1)%4]; dy=y+deltay[(d+1)%4];
		if(dx>=0 && dx<N && dy>=0 && dy<N) map[dx][dy]+=tmp; // 하 7%
		wind+=tmp;
		
		tmp=(int) Math.floor(sand*0.02);
		dx=x+deltax[(d+3)%4]*2; dy=y+deltay[(d+3)%4]*2; 
		if(dx>=0 && dx<N && dy>=0 && dy<N) map[dx][dy]+=tmp; // 상 2%
		wind+=tmp;
		dx=x+deltax[(d+1)%4]*2; dy=y+deltay[(d+1)%4]*2;
		if(dx>=0 && dx<N && dy>=0 && dy<N) map[dx][dy]+=tmp; // 하 2%
		wind+=tmp;
		
		tmp=(int) Math.floor(sand*0.01);
		dx=x+deltax[(d+3)%4]+deltax[(d+2)%4]; dy=y+deltay[(d+3)%4]+deltay[(d+2)%4];
		if(dx>=0 && dx<N && dy>=0 && dy<N) map[dx][dy]+=tmp; // 우상 1%
		wind+=tmp;
		dx=x+deltax[(d+1)%4]+deltax[(d+2)%4]; dy=y+deltay[(d+1)%4]+deltay[(d+2)%4];
		if(dx>=0 && dx<N && dy>=0 && dy<N) map[dx][dy]+=tmp; // 우하 1%
		wind+=tmp;
		
		dx=x+deltax[d]; dy=y+deltay[d];  
		if(dx>=0 && dx<N && dy>=0 && dy<N) map[dx][dy]+=sand-wind; // 남은 모래양넣기
	}

}
