package algoStudy.a0210;

import java.io.*;
import java.util.*;

public class Main_bj_2628_종이자르기 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2628"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		int cut=Integer.parseInt(br.readLine());
		
		ArrayList<Integer> xL=new ArrayList<>(); // 세로 자른부분 저장
		ArrayList<Integer> yL=new ArrayList<>(); // 가로 자른부분 저장
		
		xL.add(0); // 넓이 연산을 위해 시작점을 삽입 
		yL.add(0);
		for(int c=0; c<cut; c++) {
			st=new StringTokenizer(br.readLine());
			int m=Integer.parseInt(st.nextToken()); // 가로, 세로모드 입력
			int idx=Integer.parseInt(st.nextToken());  // 자를 번호 입력
			if(m==0) yL.add(idx);// 가로 인덱스 자르기
			else xL.add(idx);// 세로 인덱스 자르기
		}
		Collections.sort(xL); // 자르는 인덱스 넣고 오름차순정렬
		Collections.sort(yL);
		
		xL.add(N); // 넓이 연산을 위한 끝점 인덱스 넣기
		yL.add(M);
		
		int max = 0;
		// 저장후 오름차순 정렬된 인덱스에서 두개씩 꺼내서 간격만큼 곱함
		// 주어진 기본예제의 첫번째경우 (2 - 0) * (4 - 0)
		for(int i=0; i<xL.size()-1; i++)
			for(int j=0; j<yL.size()-1; j++) {
				int cal=(xL.get(i+1)-xL.get(i))*(yL.get(j+1)-yL.get(j));
				max=cal>max ? cal:max;
			}
		System.out.println(max);
	}
}
