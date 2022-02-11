import java.io.*;
import java.util.*;

/*
 * 조합 문제
 * 9C7 합이 100인 경우를 찾아 오름차순으로 출력
 */

public class Main_bj_2390_일곱난쟁이 {

	static int[] people, selected, answer;
	static int N = 9;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2390.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		people = new int[N]; selected = new int[7]; answer = new int[7];
		for(int n=0; n<N; n++) {
			people[n] = Integer.parseInt(br.readLine());
		}
		
		combi(0, 0, 0);
		
		Arrays.sort(answer);
		for(int i : answer)
			System.out.println(i);
		
	}
	
	static void combi(int start, int total, int cnt) {
		
		if(cnt==7) {
			if(total==100) {
				for(int i=0; i<7; i++)
					answer[i] = selected[i];
			}
			
			return;
		}
		
		
		for(int i=start; i<N; i++) {
			selected[cnt] = people[i];
			combi(i+1, total+people[i], cnt+1);
		}
	}
}
