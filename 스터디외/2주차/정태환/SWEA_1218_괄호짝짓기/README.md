```java
import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d4_1218"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			String[] s = br.readLine().split("");

			Stack<String> stack = new Stack<>();
			for (int i = 0; i < N; i++) {
				if (s[i].equals("(") || s[i].equals("[") || s[i].equals("{") || s[i].equals("<")) {
					stack.push(s[i]);
				} else {
					if (s[i].equals(")")) {
						if (stack.peek().equals("(")) {
							stack.pop();
						} else {
							System.out.println("#" + tc + " " + "0");
							break;
						}
					} else if (s[i].equals("]")) {
						if (stack.peek().equals("[")) {
							stack.pop();
						} else {
							System.out.println("#" + tc + " " + "0");
							break;
						}
					} else if (s[i].equals("}")) {
						if (stack.peek().equals("{")) {
							stack.pop();
						} else {
							System.out.println("#" + tc + " " + "0");
							break;
						}
					} else if (s[i].equals(">")) {
						if (stack.peek().equals("<")) {
							stack.pop();
						} else {
							System.out.println("#" + tc + " " + "0");
							break;
						}
					}
				}
				if (i == N - 1)
					System.out.println("#" + tc + " " + "1");
			}
		}
	}
}

```
