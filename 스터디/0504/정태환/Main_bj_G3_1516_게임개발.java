package algoStudy.astudy;

import java.io.*;
import java.util.*;

public class Main_bj_G3_1516_게임개발 {

	static class Building {
		int time;
		ArrayList<Integer> after;

		public Building(int time) {
			this.time = time;
			after=new ArrayList<>();
		}
		
		public void addAfter(int num) {
			after.add(num);
		}
		
	}
	
	static int N;
	static int[] degree, result;
	static Building[] list;
	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1516"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N=Integer.parseInt(br.readLine()); // 건물의 종류 수
		
		list=new Building[N+1];
		for(int i=1; i<=N; i++) list[i]=new Building(0);
		degree=new int[N+1]; // 위상정렬배열
		result=new int[N+1];
		
		for(int i=1; i<=N; i++) {
			st=new StringTokenizer(br.readLine()," ");
			list[i].time=Integer.parseInt(st.nextToken());
			while(true) {
				int a=Integer.parseInt(st.nextToken());
				if(a==-1) break;
				list[a].addAfter(i);
				degree[i]++;
			}
		}
		
		while(true) {
			int cnt=0;
			for(int i=1; i<=N; i++) {
				if(degree[i]==-1)
					cnt++;
				else if(degree[i]==0) {
					Construct(i);
					degree[i]--;
				}
			}
			if(cnt==N) break;
		}
		
		for(int i=1; i<=N; i++) System.out.println(result[i]);
		
		br.close();
	}

	static void Construct(int num) {
		result[num]+=list[num].time;
		for(int i:list[num].after) {
			result[i]=Math.max(result[i], result[num]);
			degree[i]--;
		}
	}
	
}
