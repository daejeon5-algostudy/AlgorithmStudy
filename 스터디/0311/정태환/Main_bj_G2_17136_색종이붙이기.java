package algoStudy.a0311;

import java.io.*;
import java.util.*;

public class Main_bj_G2_17136_색종이붙이기 {
	
	static boolean[][] v=new boolean[10][10];
	static int[][] map=new int[10][10];
	static int[] count=new int[] {0, 5, 5, 5, 5, 5}; // 5가지 색종이 0번인덱스 사용x
	static int ans=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17136"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i=0; i<10; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<10; j++)
				map[i][j]=Integer.parseInt(st.nextToken());
		}
		
		attach(0,0,0);
		if(ans==Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ans);
	}

	static void attach(int x, int y, int cnt) {
		if(cnt>=ans) return; // 백트래킹
		if(x==10) {
//			System.out.println("x:"+x+" y:"+y+" cnt:"+(cnt+1));
//			System.out.println(Arrays.toString(count));
			for(int i=1; i<6; i++)
				if(count[i]<0) return;
			ans=Math.min(ans, cnt);
			return;
		}
		if(map[x][y]==1 && !v[x][y]) {
			label:for(int i=5; i>0; i--) {
				if(count[i]==0 || x+i-1>9 || y+i-1>9) continue; // 색종이개수와 범위체크
				for(int j=0; j<i; j++) for(int k=0; k<i; k++) // 2~5칸이 모두 1인지, 이미 붙였는지 체크하고 아니면 패스
						if(map[x+j][y+k]==0||v[x+j][y+k]) continue label;
				
				for(int j=0; j<i; j++) for(int k=0; k<i; k++) // 방문표시하고 재귀
						v[x+j][y+k]=true;
				count[i]--;
				if(y+i-1==9) attach(x+1,0,cnt+1);
				else attach(x,y+i-1,cnt+1);
				count[i]++;
				for(int j=0; j<i; j++) for(int k=0; k<i; k++) // 다음 경우를 위해 방문 해제
						v[x+j][y+k]=false;
			}
		} else {
			if(y==9) attach(x+1,0,cnt);
			else attach(x,y+1,cnt);
		}
	}
	
}
