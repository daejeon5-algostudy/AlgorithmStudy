package algoStudy.a0413;

import java.io.*;
import java.util.*;

public class Main_bj_G4_15685_드래곤커브 {

	static int[] delx= { 1, 0,-1, 0};
	static int[] dely= { 0,-1, 0, 1};
	static boolean[][] map;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_15685"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map=new boolean[101][101];
		int N=Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			int g=Integer.parseInt(st.nextToken());
			makemap(x,y,d,g);
		}
		
		for(int i=0; i<100; i++)
			for(int j=0; j<100; j++)
				if(map[i][j]&&map[i][j+1]&&map[i+1][j+1]&&map[i+1][j]) ans++;
		
		System.out.println(ans);
		br.close();
	}

	static void makemap(int x, int y, int d, int g) {
		ArrayList<Integer> dlist=new ArrayList<>();
		dlist.add(d);
		
		// 0부터 목표세대까지 누적식으로 1세대씩 방향구함 (다음세대는 이전세대의 뒤에서부터 덧셈)
		// ex
		// 0세대 - 0
		// 1세대 - 0, 1(0으로부터 도출)
		// 2세대 - 0, 1, 2(1로부터도출), 1(0으로부터도출)
		// 3세대 - 0, 1, 2, 1, / 2, 3, 2, 1
		for(int i=0; i<g; i++)
			for(int j=dlist.size()-1; j>=0; j--)
				dlist.add((dlist.get(j)+1)%4);
		
		// 구해진 방향 맵에 표시
		map[y][x]=true;
		for(int direct:dlist) {
			x+=delx[direct];
			y+=dely[direct];
			map[y][x]=true;
		}
	}
	
}
