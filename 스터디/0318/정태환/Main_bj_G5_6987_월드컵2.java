package algoStudy.a0318;

import java.io.*;
import java.util.*;

public class Main_bj_G5_6987_월드컵2 {

	static int ans;
	static int[] win, draw, lose;
	static int[][] game=new int[15][2];
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_6987"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		
		label:for(int tc=0; tc<4; tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			win=new int[6];
			draw=new int[6];
			lose=new int[6];
			int cnt=0;
			for(int i=0; i<6; i++) {
				for(int j=i+1; j<6; j++) {
					game[cnt][0]=i;
					game[cnt][1]=j;
					cnt++;
				}
			}
			
			int sum=0;
			for(int i=0; i<6; i++) {
				win[i]=Integer.parseInt(st.nextToken());
				draw[i]=Integer.parseInt(st.nextToken());
				lose[i]=Integer.parseInt(st.nextToken());
				
				sum+=win[i]+draw[i]+lose[i];
			}
			if(sum!=30) {
				sb.append(0).append(" ");
				continue label;
			}
			
			ans=0;
			simul(0);
			
			sb.append(ans).append(" ");
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static void simul(int cnt) {
		if(cnt==15) {
			ans=1;
			return;
		}
		if(ans==1) return;
		
		int a=game[cnt][0];
		int b=game[cnt][1];
		
		if(win[a]>0 && lose[b]>0) {
			win[a]--;
			lose[b]--;
			simul(cnt+1);
			win[a]++;
			lose[b]++;
		}
		if(draw[a]>0 && draw[b]>0) {
			draw[a]--;
			draw[b]--;
			simul(cnt+1);
			draw[a]++;
			draw[b]++;
		}
		if(lose[a]>0 && win[b]>0) {
			lose[a]--;
			win[b]--;
			simul(cnt+1);
			lose[a]++;
			win[b]++;
		}
	}

}
