package algoStudy.a0210;

import java.io.*;
import java.util.*;

public class Main_bj_2477_참외밭 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2477"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 1m^2당 자라는 참외의 개수

		ArrayList<Integer> darr = new ArrayList<>(); // 동서남북저장
		ArrayList<Integer> larr = new ArrayList<>(); // 길이저장
		for (int i = 0; i < 6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			darr.add(Integer.parseInt(st.nextToken()));
			larr.add(Integer.parseInt(st.nextToken()));
		}
		
		// 입력이 어떻게 주어지든 순서를 동일하게 만들자
		// 긴변1, 긴변2, 짧은변1, 짧은변2, 짧은변1, 짧은변2
		// 도형의 형태에 상관없이 반시계방향순서로 주어지므로 순서일치만하면 쉽게풀린다
		// 검사할 것이 7개 밖에없으므로 한개씩 뒤로 보내면서 검사한다
		// 앞의것 2개를 순차탐색하면서 2개 모두 같은것이 한개도 없어야한다
		while(true) {
			int count1 = 0; // 긴변1
			int tmp = darr.get(0);
			for(int i=1; i<6; i++)
				if(tmp == darr.get(i)) count1++;
			
			int count2 = 0; // 긴변2
			tmp = darr.get(1);
			for(int i=2; i<6; i++)
				if(tmp == darr.get(i)) count2++;
			
			if(count1 == 0 && count2 == 0) break; // 앞의 두개가 모두 긴변이면 끝
			else { // 앞의 두개가 모두 긴변이 아니면 맨앞의것 맨뒤로 보내기
				int tmp1=darr.get(0), tmp2=larr.get(0);
				darr.remove(0); larr.remove(0);
				darr.add(tmp1); larr.add(tmp2);
			}
		}
		// 넓이구하고 1m^2당 자라는 참외의 개수 곱해준다
		int cal=((larr.get(0)*larr.get(1))-(larr.get(3)*larr.get(4)))*N;
		System.out.println(cal);
		
	}

}
