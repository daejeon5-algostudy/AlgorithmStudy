package algoStudy.a0408;

import java.io.*;
import java.util.*;

public class Main_bj_G5_14503_로봇청소기 {

	static int[] delx=new int[] {-1, 0, 1, 0}; // 상우하좌
	static int[] dely=new int[] { 0, 1, 0,-1}; // 상우하좌
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14503"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N=Integer.parseInt(st.nextToken()); // 행크기
		int M=Integer.parseInt(st.nextToken()); // 열크기
		int[][] map=new int[N][M];
		
		st=new StringTokenizer(br.readLine()," ");
		int x=Integer.parseInt(st.nextToken()); // 청소기 초기좌표
		int y=Integer.parseInt(st.nextToken());
		int d=Integer.parseInt(st.nextToken()); // 청소기 초기방향

		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++)
				map[i][j]=Integer.parseInt(st.nextToken());
		}
		
		int cnt=0;
		int relay=0;
		while(true) {
			// 1.현재위치를 청소한다
			if(map[x][y]==0) {
				map[x][y]=2; // 청소한곳 2로표시
				cnt++;
			}
			
			// 2.왼쪽에 청소안한 빈공간이면 왼쪽방향으로 회전후 전진하고 1번으로
			int td=(d+3)%4;
			int dx=x+delx[td], dy=y+dely[td];
			if(map[dx][dy]==0) {
				x=dx; y=dy; d=td; relay=0;
				continue;
			// 아니라면 왼쪽방향으로 회전
			} else d=td;
			relay++;
			
			// 3.1번으로 돌아가거나 후진하지 않고 4연속 실행되었을경우, 바로 뒤쪽이 벽이라면 멈춘다, 그렇지 않다면 한칸후진
			if(relay==4) {
				dx=x-delx[d]; dy=y-dely[d];
				if(map[dx][dy]==1) break;
				else {
					x=dx; y=dy;
					relay=0;
				}
			}
		}
		System.out.println(cnt);
		br.close();
	}
	
}
