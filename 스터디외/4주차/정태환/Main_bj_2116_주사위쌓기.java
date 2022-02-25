package algoStudy.a0210;

import java.io.*;
import java.util.*;

public class Main_bj_2116_주사위쌓기 {

	static int N,max = 0;
	static int[][] arr;
	static ArrayList<Integer> tmp;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2116"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());

		arr=new int[N][6]; // N개의 주사위배열
		for(int i=N-1; i>=0; i--) { // 주사위 입력받음
			StringTokenizer token=new StringTokenizer(br.readLine());
			for(int j=0; j<6; j++) arr[i][j]=Integer.parseInt(token.nextToken());
		} 

		for(int i=1; i<=6; i++) // 시작 주사위의 눈 설정해주고 재귀돌림
			cf(N-1,i,0);
		System.out.println(max); // 결과값 출력
	}
	
	static void cf(int level, int num, int sum) {
		if(level == -1) { // 전부돌았으면 이전 최대값과 비교하여 갱신
//			System.out.println(sum);
			max=sum>max ? sum:max;
			return;
		}
		for (int i=0; i<6; i++) { // 문제에 주어진 주사위 눈경우에따라 경우를 나눔 
			if(arr[level][i]==num) { // 특별한 케이스로 나눌 수 없어서 그냥 구현함
				tmp=new ArrayList<>();
				int rnum=0;
				if(i==0 ||i==5) {
					tmp.add(arr[level][1]);tmp.add(arr[level][2]);tmp.add(arr[level][3]);tmp.add(arr[level][4]);
					rnum=i==0 ? arr[level][5]:arr[level][0];
				}else if(i==1 || i==3) {
					tmp.add(arr[level][0]);tmp.add(arr[level][2]);tmp.add(arr[level][4]);tmp.add(arr[level][5]);
					rnum=i==1 ? arr[level][3]:arr[level][1];
				}else if(i==2 || i==4) {
					tmp.add(arr[level][0]);tmp.add(arr[level][1]);tmp.add(arr[level][3]);tmp.add(arr[level][5]);
					rnum=i==2 ? arr[level][4]:arr[level][2];
				}
				Collections.sort(tmp); // 맞닿아 있는 주사위를 제외한 숫자를 오름차순으로 정렬함
				// 0,5 / 1,3 / 2,4 짝
//				System.out.print(rnum + " " + tmp + " " + tmp.get(3) + " / ");
				cf(level-1, rnum, sum+tmp.get(3)); // 새롭게 맞닿게 된 눈숫자와, 오름차순 정렬된 배열에서 가장큰값 더해준값으로 재귀돌림
			}
		}
		
	}
}
