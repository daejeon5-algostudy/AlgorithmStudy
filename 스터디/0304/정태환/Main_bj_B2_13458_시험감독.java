package algoStudy.a0304;

import java.io.*;
import java.util.*;

public class Main_bj_B2_13458_시험감독 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_13458"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N=Integer.parseInt(br.readLine()); // 시험장의 개수
		int[] map=new int[N]; // 시험장에 있는 응시자의 수
		st=new StringTokenizer(br.readLine()," ");
		for(int i=0; i<N; i++) map[i]=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine()," ");
		int B=Integer.parseInt(st.nextToken()); // 총감독관이 감시하는 응시자수 오직 1명
		int C=Integer.parseInt(st.nextToken()); // 부감독관이 감시하는 응시자수 여러명 가능
		
		long ans=0;
		for(int i=0; i<N; i++) {
			ans++;
			if(map[i]<=B) continue;
			else ans+=Math.ceil(1.0*(map[i]-B)/C);
		}
		
		System.out.println(ans);
	}

}
