package algoStudy.a0212;

import java.io.*;
import java.util.*;

public class Main_bj_13300_방배정 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_13300"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken()); // 학생수
		int K=Integer.parseInt(st.nextToken()); // 한 방에 배정할 수 있는 최대 인원 수
		
		int[][] arr=new int[6][2];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken()); // 성별 0 여성 1 남성
			int y=Integer.parseInt(st.nextToken()); // 학년
			arr[y-1][s]++;
		}
		
		int cnt=0;
		for(int i=0; i<6; i++) {
			cnt+=Math.ceil(1.0*arr[i][0]/K);
			cnt+=Math.ceil(1.0*arr[i][1]/K);
		}
		System.out.println(cnt);
	}
}
