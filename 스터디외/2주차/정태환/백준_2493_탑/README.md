```java
import java.io.*;
import java.util.*;

public class Main {

	static class Tower {
		int idx, height;

		public Tower(int idx, int height) {
			this.idx = idx;
			this.height = height;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2493"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		Stack<Tower> stack = new Stack<>();
		StringTokenizer token = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int h = Integer.parseInt(token.nextToken());
			if (stack.isEmpty()) {
				sb.append("0 ");
				stack.push(new Tower(i, h));
			} else {
				while (true) {
					if (stack.isEmpty()) {
						sb.append("0 ");
						stack.push(new Tower(i, h));
						break;
					}
					Tower top = stack.peek();
					if (top.height > h) {
						sb.append(top.idx).append(" ");
						stack.push(new Tower(i, h));
						break;
					} else {
						stack.pop();
					}
				}
			}
		}
		System.out.println(sb);
		br.close();
	}
}

```
