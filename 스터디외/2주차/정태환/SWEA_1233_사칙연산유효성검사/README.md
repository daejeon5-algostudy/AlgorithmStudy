```java
import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d4_1233"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스는 10개로 주어져있다
		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine()); // 노드의 개수를 입력받는다
			
			boolean check = true; // 전체 트리의 유효성을 저장하는 변수
			for (int i = 0; i < N; i++) {
				// 유효성이 파괴되지 않은 경우만 체크
				if(check) {
					StringTokenizer token = new StringTokenizer(br.readLine());
					token.nextToken();	// 유효성 판별에 트리의 노드번호는 사용하지 않음
					String s = token.nextToken();	// 노드의 내용을 입력받음 ( 연산자 or 숫자 )
					// 연산자 노드의 경우 입력을 두개 받아야 유효하다 
					if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
						if(token.countTokens() != 2) check = false;
					} else {
					// 숫자 노드의 경우 추가 입력이 없어야 유효하다
						if(token.hasMoreTokens()) check = false;
					}
				} else {
					br.readLine();
				}
				
			}
			// 입력이 완료된 후 출력
			sb.append("#").append(tc).append(" ");
			if(check) 	sb.append(1).append("\n");
			else 		sb.append(0).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

}

```
