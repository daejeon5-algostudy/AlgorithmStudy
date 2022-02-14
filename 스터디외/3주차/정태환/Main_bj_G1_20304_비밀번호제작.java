package algoStudy.a0214;

import java.io.*;
import java.util.*;

public class Main_bj_G1_20304_비밀번호제작 {

	/*
	 * 굉장히 이해하기 난해한 문제였다.
	 * 구글링을 통해 얻은 정보들에서도 도표를 이용해 이해하기 편하게 노력한것 같지만
	 * 왜 해커의 패스워드와 1을 왼쪽시프트하면서 XOR 연산을 하는지 이유에 대한 설명이 없어 이해하기 힘들었다
	 * 아이디어는 간단하다
	 * 해커가 사용한 비밀번호들과 XOR연산시 안전도를 1씩 늘려가면서 구하려고 한것이다
	 * 가령 3의경우 11 인데
	 * 첫번째,
	 * 11
	 * 01 (1<<0) XOR 하면 10이 나오고 십진수로 2이다.
	 * 이 소리는 해커가 사용한 3과 2를 XOR할시 안전도가 1이라는 소리다
	 * 
	 * 두번째,
	 * 11
	 * 10 (1<<1) XOR 하면 01이 나오고 십진수로 1이다. 
	 * 이 소리는 해커가 사용한 3과 1를 XOR할시 안전도가 1이라는 소리다
	 * 
	 * 세번째,
	 *  11
	 * 100 (1<<2) XOR하면 111이 나오고 십진수로 7이다.
	 * 이소리는 3과 7을 XOR할시 안전도가 1이라는소리다.
	 * 
	 * 정답을 구할 배열에 2, 1, 7번 인덱스에 1을 넣는다.
	 * 해커들이 사용한 숫자는 입력받으면서 정답 배열에 0으로 넣는다
	 * 
	 * 이런식으로 연산을 하여 안전도가 1인경우를 먼저구하는데,
	 * 각 연산마다 도출된 2, 1, 7을 큐에 순서대로 큐에 넣는다
	 * 안전도가 1이 나온것들을 대상으로 큐를 돌리면 안전도가 2인것을 구할수 있다
	 * 그런데! 이 다음부터 많이 혼란이 올 수 있다고 생각한다! 
	 * 안전도가 1인것들을 큐에서 꺼내서 2인것을 구하는데, 이미 안전도가 1이라고 나온것들이 연산결과로 나올것이다.
	 * 우리가 구하고자하는것은 해커가 사용한 패스워드와 XOR연산해서 구할 안전도이므로
	 * 이미 구해진것들과 중복되는 연산결과는 해커가 사용한 패스워드들과의 안전도이므로 저장대상에서 제외한다.
	 * 다시 설명하자면, 안전도가 2인것을 구했는데 이미 안전도가 1이라고 정해진것은 저장대상에서 제외한다.
	 * 저장대상에서 제외되지 않는 수들은 해커가 사용한 패스워드와 XOR연산시 안전도가 2인것들이다.
	 * 이런 식으로 안전도를 1씩 늘려가며 연산을 한다
	 * 
	 */
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_20304")); // 테스트하기 편하게 파일로 입력을 받기 위한 파일입력스트림 열기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 빠르게 입력을 받기 위한 버퍼스트림 열기

		int N = Integer.parseInt(br.readLine()); // 관리자 패스워드의 최대값
		int M = Integer.parseInt(br.readLine()); // 해커가 사용한 패스워드의 개수
		Integer[] arr = new Integer[1_000_001]; // 정답을 구할 배열 (시프트 연산시 에러 안나도록 배열크기 정함)
		
		Queue<Integer> q = new LinkedList<>(); // 안전도를 1씩 늘려가면서 구한다
		StringTokenizer token = new StringTokenizer(br.readLine(), " "); // 해커의 패스워드입력이 공백을 구분자로 한줄로 주어지므로 토큰으로 자름
		for (int i = 0; i < M; i++) { // 해커가 사용한 패스워드 개수만큼
			int p = Integer.parseInt(token.nextToken()); // 입력을 받는다
			arr[p] = 0; // 정답을 구할 배열에 해커가 사용한 번호는 안전도가 0이므로 초기화
			q.offer(p); // 큐에 바로 넣음
		}
		
		int ans = 0;
		while(!q.isEmpty()) {
			int now = q.poll(); // 큐에서 하나씩 차례차례 뺀다
			for(int i=0; i<20; i++) {
				int result = now ^ (1<<i); // 안전도 1씩 증가시키기 위해 1인 비트가 한개씩만 있도록 돌면서 연산
				if(result > N || arr[result] != null) continue; // 이미 구해졌거나, 구할값보다 크면 생략
				arr[result] = arr[now] + 1; // 안전도 1씩 증가시키면서 구한다
				ans = Math.max(ans, arr[result]); // 최대값 저장
				q.offer(result); // 구한 결과값 큐에 삽입
			}
		}
		System.out.println(ans); // 최대값 출력

	}

}
