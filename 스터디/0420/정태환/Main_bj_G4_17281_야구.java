package algoStudy.asolved.a0420;

import java.io.*;
import java.util.*;

public class Main_bj_G4_17281_야구 {
	
	static int N, ans;
	static int[][] base;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17281"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N=Integer.parseInt(br.readLine());
		base=new int[N][9];
		// 1안타 22루타 33루타 4홈런 0아웃
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=0; j<9; j++) base[i][j]=Integer.parseInt(st.nextToken());
		}
		
		int[] order=new int[9];
		boolean[] v=new boolean[9];
		v[0]=true;
		perm(0, order, v);
		System.out.println(ans);
		br.close();
	}

	static void perm(int cnt, int[] order, boolean[] v) {
		if(cnt==9) {
			int n=calc(order);
			ans=Math.max(ans, n);
			return;
		}
		if(cnt==3) {
			perm(cnt+1, order, v);
			return;
		}
		for(int i=1; i<9; i++) {
			if(v[i]) continue;
			order[cnt]=i;
			v[i]=true;
			perm(cnt+1, order, v);
			v[i]=false;
		}
	}

	static int calc(int[] order) {
		int result=0; // 해당 경우에서의 점수
		
		int pnum=0; // 플레이어 순서
		for(int in=0; in<N; in++) { // 이닝
			int[] ground=new int[4]; // 진루상황
			int out=0; // 아웃카운트
			while(true) {
				if(out==3) break; // 3아웃시 종료
				
				int player=order[pnum++]; // 선수번호
				if(pnum==9) pnum=0;
				switch(base[in][player]) {
					case 0: out++; break; // 아웃
					case 1: // 1루타
						if(ground[3]==1) {
							ground[3]=0;
							result++;
						}
						for(int i=2; i>=1; i--) ground[i+1]=ground[i];
						ground[1]=1;
						break;
					case 2: // 2루타
						for(int i=3; i>=2; i--) 
							if(ground[i]==1) {
								ground[i]=0;
								result++;
							}
						ground[3]=ground[1];
						ground[1]=0;
						ground[2]=1;
						break;
					case 3: // 3루타
						for(int i=3; i>=1; i--) 
							if(ground[i]==1) {
								ground[i]=0;
								result++;
							}
						ground[3]=1;
						break;
					case 4: // 홈런
						for(int i=3; i>=1; i--) 
							if(ground[i]==1) {
								ground[i]=0;
								result++;
							}
						result++;
						break;
				}
			}
		}
		return result;
	}
	
}
