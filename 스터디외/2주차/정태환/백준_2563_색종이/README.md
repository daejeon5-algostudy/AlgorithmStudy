```java
import java.io.*;
import java.util.*;

public class Main {
	static boolean[][] map = new boolean[101][101];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2563"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 색종이의 숫자
		int N = Integer.parseInt(br.readLine());
		// 색종이 붙이기
		for (int i = 0; i < N; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(token.nextToken());
			int b = Integer.parseInt(token.nextToken());

			// 수학적 계산보다는 범위가 10000칸으로 적은 편인데 반해
			// 색종이가 100장이하로 주어지므로 복잡해질 가능성이 있어서
			// boolean 배열을 통해 간단한 구현을 이용함
			for (int j = a; j < a + 10; j++)
				for (int k = b; k < b + 10; k++)
					map[j][k] = true;
		}

		// 도출된 지도를 통해 결과를 계산한다
		int result = 0;
		for (int i = 0; i < 100; i++)
			for (int j = 0; j < 100; j++)
				if (map[i][j]) result++;

		System.out.println(result);
	}
}
```
