```java
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1158"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine(), " ");

		// 사람
		int N = Integer.parseInt(token.nextToken());
		// 제거할 순서
		int K = Integer.parseInt(token.nextToken());

		// 삭제가 이루어지므로 LinkedList로 생성
		List<Integer> list = new LinkedList<>();
		// 입력을 받음
		for (int i = 1; i <= N; i++) list.add(i);

		StringBuilder sb = new StringBuilder();
		sb.append("<");

		// list의 시작인덱스는 0
		// 최초 제거할 순서는 2번인덱스
		int idx = K - 1;
		while (true) {
			sb.append(list.get(idx)).append(", ");      // 제거할 인덱스를 sb에 추가
			list.remove(idx--);                         // 제거 실행, 해당 인덱스 삭제되었으므로 1을 감해줌
			if (list.isEmpty()) break;                   // 비어있을때 예외처리
			idx = (idx + K) % list.size();               // 다음 제거할 인덱스를 계산함
		}						// 모듈러 연산으로 초과범위 계산을 할 수 있음
		
		// 쉼표제거
		sb.delete(sb.length() - 2, sb.length()).append(">");
		System.out.println(sb);
		br.close();
	}

}

```
