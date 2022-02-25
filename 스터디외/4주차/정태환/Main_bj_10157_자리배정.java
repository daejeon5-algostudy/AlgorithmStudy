package algoStudy.a0212;

import java.io.*;
import java.util.*;

public class Main_bj_10157_자리배정 {

	static int[][] deltas = new int[][] {
		// 1사분면에서의 좌표체계
		{ 0, 1}, // 상
		{ 1, 0}, // 우
		{ 0,-1}, // 하
		{-1, 0}, // 좌
	};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_10157"));
		Scanner sc=new Scanner(System.in);
		int X=sc.nextInt(), Y=sc.nextInt(), N=sc.nextInt();
		// 1사분면에서의 좌표체계
		if(N>X*Y) {
			System.out.println("0");
			System.exit(0);
		}
		if(N==1) {
			System.out.println("1 1");
			System.exit(0);
		}
		int[][] arr=new int[X][Y];
		arr[0][0]=1;
		int d=0, i=0, j=0, cnt=2;
		while(true){
			if(arr[i][j]==N) {
				System.out.println((i+1)+" "+(j+1));
				break;
			}
			int dx=i+deltas[d][0];
			int dy=j+deltas[d][1];
			if(dx<0 || dx>=X || dy<0 || dy>=Y) {
				d=(d+1)%4;
				continue;
			}
			if(arr[dx][dy]!=0) {
				d=(d+1)%4;
				continue;
			}
			arr[dx][dy]=cnt;
			i=dx;
			j=dy;
			cnt++;
		}
		sc.close();
	}
}
