package algoStudy.a0311;

import java.io.*;
import java.util.*;

public class Main_bj_S1_2002_추월 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2002"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int N=Integer.parseInt(br.readLine());
		LinkedList<String> cars=new LinkedList<>();
		for(int i=0; i<N; i++)
			cars.add(br.readLine());
		
		ArrayDeque<String> q=new ArrayDeque<>();
		for(int i=0; i<N; i++)
			q.offer(br.readLine());
		
		int cnt=0;
		while(!q.isEmpty()) {
			String now=q.poll();
			if(cars.get(0).equals(now)) {
				cars.remove(0);
				continue;
			}
			else {
				cnt++;
				for(int i=0; i<cars.size(); i++)
					if(cars.get(i).equals(now))
						cars.remove(i);
			}
		}
		System.out.println(cnt);
	}

}
