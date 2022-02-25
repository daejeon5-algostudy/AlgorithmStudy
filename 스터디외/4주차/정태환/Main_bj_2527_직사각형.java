package algoStudy.a0211;

import java.io.*;
import java.util.*;

public class Main_bj_2527_직사각형 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2527"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<4; i++) {
			StringTokenizer token=new StringTokenizer(br.readLine());
			
			int x1=Integer.parseInt(token.nextToken());
			int y1=Integer.parseInt(token.nextToken());
			int x2=Integer.parseInt(token.nextToken());
			int y2=Integer.parseInt(token.nextToken());
			
			int x3=Integer.parseInt(token.nextToken());
			int y3=Integer.parseInt(token.nextToken());
			int x4=Integer.parseInt(token.nextToken());
			int y4=Integer.parseInt(token.nextToken());
			
			if(x3>x2 || x1>x4 || y3>y2 || y1>y4) // 만나지 않는 경우 d
				System.out.println("d");
			else if((x2==x3 && y2==y3)||(x2==x3 && y1==y4)|| // 점에서 만나는 경우 c
					(x1==x4 && y1==y4)||(x1==x4 && y2==y3))
				System.out.println("c");
			//속에서 선에서만나는경우 b
			else if(y2==y3 && !(x4<x1 || x3>x2)) System.out.println("b"); // 상
			else if(x2==x3 && !(y3>y2 || y1>x4)) System.out.println("b"); // 우
			else if(y1==y4 && !(x1>x4 || x3>x2)) System.out.println("b"); // 하
			else if(x1==x4 && !(y1>y4 || y3>y2)) System.out.println("b"); // 좌
			// 이외 직사각형 a
			else System.out.println("a");
		}

	}

}
