import java.io.*;
import java.util.*;

public class Main_bj_G2_17825_주사위윷놀이 {

	static int ans;
	static int[] n, score;
	static int[][] map;
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17825"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");

		n=new int[10];
		for(int i=0; i<10; i++) n[i]=Integer.parseInt(st.nextToken());
		
		// 각 경우에 따른 위치
		map=new int[4][29];
		map[0]=new int[] {0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,-1,-1,-1,-1,-1,-1,-1,-1};
		map[1]=new int[] {0,0,0,0,0,10,13,16,19,25,30,35,40,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		map[2]=new int[] {0,0,0,0,0, 0, 0, 0, 0, 0,20,22,24,25,30,35,40,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		map[3]=new int[] {0,0,0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,30,28,27,26,25,30,35,40,-1,-1,-1,-1,-1,-1};
		
		v=new boolean[4][29];
		v[0][0]=true;
		
		game(0, 0, new int[4], new int[4]);
		
		System.out.println(ans);
		br.close();
	}
	
	static void game(int cnt, int sum, int[] x, int[] y) {
		if(cnt==10) {
			ans=Math.max(ans, sum);
			return;
		}
		
		for(int i=0; i<4; i++) {
			int tx=x[i], ty=y[i]; // 다음 경우를 위한 저장
			if(map[x[i]][y[i]]==-1) continue; // 도착지면 패스
			v[x[i]][y[i]]=false; // 이동할것이므로 방문처리 해제
			int dx=0; // 지름길 변수
			if(x[i]==0) { // 외곽인데 지름길 위치면 세팅 
				if(y[i]==5) dx=1;
				else if(y[i]==10) dx=2;
				else if(y[i]==15) dx=3;
			}
			
			x[i]+=dx; // 지름길배열로 이동
			y[i]+=n[cnt]; // 주사위수만큼 칸이동
			int nv=map[x[i]][y[i]]; // 이동할 칸 숫자
			
			// 3개의 지름길이 공통적으로 25, 30, 35, 40번칸을 지나감
			// 방문배열처리를 위해 공통 위치에선 1번지름길을 지나간것으로 처리함
			if(x[i]!=0) { 
				if(nv==40) { 
					x[i]=0; y[i]=20; 
				} else if(nv==25) { 
					x[i]=1; y[i]=9; 
				} else if(nv==30) {
					x[i]=1; y[i]=10;
				} else if(nv==35) {
					x[i]=1; y[i]=11;
				}
			}
			
			if(!v[x[i]][y[i]]) { // 이동할 위치에 말이 없을때만
				if(nv!=-1) {
					v[x[i]][y[i]]=true;
					game(cnt+1, sum+nv, x, y); // 도착지 아니면 값 더해서 패스
					v[x[i]][y[i]]=false;
				} else {
					game(cnt+1, sum, x, y); // 도착지라면 그대로 패스
				}
			}
			
			x[i]=tx; y[i]=ty; v[x[i]][y[i]]=true; // 다음 경우를 위한 복구
		}
	}
	
}
