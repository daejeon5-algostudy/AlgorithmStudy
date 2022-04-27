package algoStudy.asolved.a0420;

import java.io.*;
import java.util.*;

public class Main_bj_G4_17406_배열돌리기4 {

	static int[] delx= { 0, 1, 0,-1}; // 우하좌상
	static int[] dely= { 1, 0,-1, 0}; // 우하좌상
	static int[][] map, rdata;
	static int N,M,K,ans=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17406"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");

		N=Integer.parseInt(st.nextToken()); // 행
		M=Integer.parseInt(st.nextToken()); // 열
		K=Integer.parseInt(st.nextToken()); // 연산횟수
		
		map=new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=1; j<=M; j++) map[i][j]=Integer.parseInt(st.nextToken());
		}
		
		rdata=new int[K][3];
		for(int i=0; i<K; i++) {
			st=new StringTokenizer(br.readLine()," ");
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());
			rdata[i]=new int[] {r,c,s};
		}
		Perm(0, new boolean[K], new int[K]);
		System.out.println(ans);
		br.close();
	}

	static void Perm(int cnt, boolean[] v, int[] nums) {
		if(cnt==K) {
			int n=rotate(nums);
			ans=Math.min(ans, n);
			return;
		}
		
		for(int i=0; i<K; i++) {
			if(v[i]) continue;
			nums[cnt]=i;
			v[i]=true;
			Perm(cnt+1, v, nums);
			v[i]=false;
		}
	}

	static int rotate(int[] nums) {
		int min=Integer.MAX_VALUE;
		int[][] map2=new int[N+1][M+1];
		for(int i=1; i<=N; i++) map2[i]=map[i].clone();
		
		for(int n=0; n<K; n++) {
			int now=nums[n]; // 현재 경우의수 연산번호
			int[] lu=new int[] {rdata[now][0]-rdata[now][2], rdata[now][1]-rdata[now][2]}; // 왼쪽상단
			int[] rd=new int[] {rdata[now][0]+rdata[now][2], rdata[now][1]+rdata[now][2]}; // 오른쪽하단 
			
			while(true) {
				int sx=lu[0], sy=lu[1]; // 이동변수
				int tmp=map2[sx][sy], tmp2=map2[sx][sy]; // 회전시 임시저장
				for(int d=0; d<4; d++) {
					while(true) {
						sx+=delx[d]; sy+=dely[d]; // 이동
						if(sx<lu[0]||sx>rd[0]||sy<lu[1]||sy>rd[1]) { // 각 경우 범위초과
							sx-=delx[d]; sy-=dely[d]; // 이동 취소후 다음방향
							break;
						}
						tmp2=map2[sx][sy]; // 이동한 위치 값 저장
						map2[sx][sy]=tmp;  // 이동한 위치에 이전값 저장
						tmp=tmp2; 		   // 이동한 위치값 다른 임시값에 저장
					}
				}
				lu[0]+=1; lu[1]+=1; // 왼쪽상단 우하대각이동
				rd[0]-=1; rd[1]-=1; // 오른쪽하단 좌상대각이동
				if(lu[0] >= rd[0] || lu[1] >= rd[1]) break; // 이동끝
			}
		}
		for(int i=1; i<=N; i++) {
			int sum=0;
			for(int j=1; j<=M; j++) sum+=map2[i][j];
			min=Math.min(sum, min);
		}
		return min;
	}
}
