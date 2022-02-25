package algoStudy.a0212;

import java.io.*;
import java.util.*;

public class Main_bj_10158_개미 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_10158"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int x=Integer.parseInt(st.nextToken());
		int y=Integer.parseInt(st.nextToken());

		st=new StringTokenizer(br.readLine());
		int ax=Integer.parseInt(st.nextToken());
		int ay=Integer.parseInt(st.nextToken());
		int T=Integer.parseInt(br.readLine());

		int tx=T%(x*2);
		int ty=T%(y*2);
		
		int dx=1;
		while(tx>0) {
			if(ax==x) dx=-1;
			if(ax==0) dx=1;
			ax+=dx;
			tx--;
		}
		int dy=1;
		while(ty>0) {
			if(ay==y) dy=-1;
			if(ay==0) dy=1;
			ay+=dy;
			ty--;
		}
		System.out.println(ax+" "+ay);
	}
}
