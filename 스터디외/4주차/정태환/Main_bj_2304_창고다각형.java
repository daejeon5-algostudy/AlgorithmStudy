package algoStudy.a0210;

import java.io.*;
import java.util.*;

public class Main_bj_2304_창고다각형 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2304"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		int[] arr=new int[1001];// 문제에서 주어진 기둥의 최대개수 + 1만큼 배열생성

		int max=-1; // 최대값과 최대값의 위치를 저장할 변수
		int maxIdx=-1;
		int startIdx=1000; // 배열의 범위가 너무 넓으므로 연산을 줄이기 위해 시작점과 끝점을 구함
		int endIdx=0;
		for(int i=0; i<N; i++) {
			StringTokenizer token=new StringTokenizer(br.readLine());
			int L=Integer.parseInt(token.nextToken());
			int H=Integer.parseInt(token.nextToken());
			arr[L]=H;
			
			if(H>max) { // 이전에 저장된 최대값들보다 크면 값과 위치 갱신
				max=H;
				maxIdx=L;
			}
			if(L<startIdx) startIdx=L; // 시작점보다 더 작거나, 끝점보다 더 큰 위치일때 갱신 
			if(L>endIdx)   endIdx=L;
		}
		
		int result=0; // 입력받은 값들과 인덱싱한 정보들을 토대로 연산진행
		for(int i=startIdx; i<maxIdx; i++) { // 시작점부터 최대값위치까지 연산진행
			result+=arr[i];
			if(arr[i]>arr[i+1]) arr[i+1]=arr[i];
		}
		for(int i=endIdx; i>=maxIdx; i--) { // 끝점부터 최대값위치까지 연산진행
			result+=arr[i];
			if(arr[i-1] < arr[i]) arr[i-1]=arr[i];
		}
		System.out.println(result); // 결과값 출력
	}
}
