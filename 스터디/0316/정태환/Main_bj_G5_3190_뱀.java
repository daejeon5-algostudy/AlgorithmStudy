package algoStudy.a0316;

import java.io.*;
import java.util.*;

public class Main_bj_G5_3190_뱀 {
	
	static class Move {
		int time;
		String d;
		
		public Move(int time, String d) {
			this.time = time;
			this.d = d;
		}
		
	}
	
	static int[] delx= new int[] { 0, 1, 0,-1}; // 우 하 좌 상
	static int[] dely= new int[] { 1, 0,-1, 0};
	static int[][] map;
	static LinkedList<Move> move=new LinkedList<>();
	static LinkedList<int[]> body=new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_3190"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N=Integer.parseInt(br.readLine()); // 보드의 크기
		map=new int[N+1][N+1];
		
		int K=Integer.parseInt(br.readLine()); // 사과의 개수
		for(int i=0; i<K; i++) {
			st=new StringTokenizer(br.readLine()," ");
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 4; // 사과세팅
		}
		
		int L=Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수
		for(int i=0; i<L; i++) {
			st=new StringTokenizer(br.readLine()," ");
			move.add(new Move(Integer.parseInt(st.nextToken()), st.nextToken()));
		}
		
		int sx=1, sy=1, sd=0; // 뱀의 시작위치, 시작방향
		map[sx][sy]=1;
		body.add(new int[] {sx,sy});
		int time=0;
		while(true) {
			if(!move.isEmpty()&&time==move.get(0).time) { // 방향 변경 시간과 같으면 변경 후 기록삭제
				String d=move.get(0).d;
				if(d.equals("D")) sd=(sd+1)%4;
				else sd=(sd+3)%4;

				move.remove(0);
			}
			time++;
			sx+=delx[sd];
			sy+=dely[sd];
			if(sx<1||sx>N||sy<1||sy>N||map[sx][sy]==1) break; // 벽만나거나 몸과부딪히면 종료
			if(map[sx][sy]==4) { // 사과만나면 전진만
				body.add(new int[] {sx,sy});
				map[sx][sy]=1;
			} else { // 전진하고 가장 마지막 꼬리 자르기
				body.add(new int[] {sx,sy});
				map[sx][sy]=1;
				int[] last=body.get(0);
				body.remove(0);
				map[last[0]][last[1]]=0;
			}
		}
		
		System.out.println(time);
		
	}
}
