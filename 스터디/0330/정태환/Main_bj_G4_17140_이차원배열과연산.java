package algoStudy.a0330;

import java.io.*;
import java.util.*;

public class Main_bj_G4_17140_이차원배열과연산 {

	static int nr=3, nc=3;
	static int[][] map=new int[101][101]; //1부터 최대 100까지
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17140"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine()," ");

		int r=Integer.parseInt(st.nextToken()); // 행
		int c=Integer.parseInt(st.nextToken()); // 열
		int k=Integer.parseInt(st.nextToken()); // 값
		
		for(int i=1;i<=3;i++) {
			st=new StringTokenizer(br.readLine()," ");
			for(int j=1;j<=3;j++)
				map[i][j]=Integer.parseInt(st.nextToken());
		}
		
		int time=0;
		while(true) {
			if(map[r][c]==k) break;
			
			if(nr>=nc) R(); // R 연산
			else 	   C(); // C 연산
			if(time++>100) {
				time=-1;
				break;
			}
		}
		System.out.println(time);
	}
	
	static void R() {
		int maxc=0;
		int[][] save=new int[101][101];
		for(int i=1; i<=nr; i++) { // 행의 수 만큼
			
			// 각각의 수가 몇 번 나왔는지 체크
			int[] num=new int[101]; // 들어있는 수의 배열
			for(int j=1; j<=nc; j++) // 열의 수 만큼 
				num[map[i][j]]++; // 수의 등장횟수구하기
			
			// 수와 등장횟수를 리스트에 삽입
			ArrayList<int[]> list=new ArrayList<>();
			for(int j=1;j<=100;j++)
				if(num[j]!=0) {
					list.add(new int[] {j,num[j]}); // 수와 등장횟수 삽입
				}
			
			// 정렬
			Collections.sort(list, (o1, o2)->{
					if(o1[1]!=o2[1]) return Integer.compare(o1[1], o2[1]);
					else return Integer.compare(o1[0], o2[0]);
			});
			
			// 임시배열에 대입
			for(int j=0; j<list.size(); j++) {
				if(j>50) break;
				int[] now=list.get(j);
				save[i][j*2+1]=now[0];
				save[i][j*2+2]=now[1];
			}
			
			// 열길이 갱신
			maxc=Math.max(maxc, list.size()*2);
		}
		// 임시배열로 바꿔치기
		map=save;
		nc=maxc;
	}
	static void C() {
		int maxr=0;
		int[][] save=new int[101][101];
		for(int i=1; i<=nc; i++) { // 열의 수만큼
			int[] num=new int[101]; // 들어있는 수의 배열
			for(int j=1; j<=nr; j++)  // 행의 수 만큼
				num[map[j][i]]++; // 수의 등장횟수를 구하기
			
			// 수와 등장횟수를 리스트에 삽입
			ArrayList<int[]> list=new ArrayList<>();
			for(int j=1;j<=100;j++)
				if(num[j]!=0) {
					list.add(new int[] {j,num[j]}); // 수와 등장횟수 삽입
				}
			
			// 정렬
			Collections.sort(list, (o1, o2)->{
					if(o1[1]!=o2[1]) return Integer.compare(o1[1], o2[1]);
					else return Integer.compare(o1[0], o2[0]);
			});
			
			// 임시배열에 대입
			for(int j=0; j<list.size(); j++) {
				if(j>50) break;
				int[] now=list.get(j);
				save[j*2+1][i]=now[0];
				save[j*2+2][i]=now[1];
			}
			
			// 열길이 갱신
			maxr=Math.max(maxr, list.size()*2);
			
		}
		// 임시배열로 바꿔치기
		map=save;
		nr=maxr;
	}

}
