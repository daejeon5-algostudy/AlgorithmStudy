```java
import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d4_1223"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			String[] s = br.readLine().split("");
			String result = "";
			Stack<String> stack = new Stack<>();
			for (int i = 0; i < N; i++) {
				if (s[i].equals("*")) {
					stack.push(s[i]);
				} else if (s[i].equals("+")) {
					while (!stack.isEmpty())
						result += stack.pop();
					stack.push(s[i]);
				} else {
					result += s[i];
				}
			}
			while (!stack.isEmpty())
				result += stack.pop();

			Stack<Integer> numstack = new Stack<>();
			String[] s2 = result.split("");
			for (int i = 0; i < N; i++) {
				if(s2[i].equals("+")) {
					int n1 = numstack.pop();
					int n2 = numstack.pop();
					numstack.push(n1+n2);
				} else if(s2[i].equals("*")) {
					int n1 = numstack.pop();
					int n2 = numstack.pop();
					numstack.push(n1*n2);
				} else {
					numstack.push(Integer.parseInt(s2[i]));
				}
			}
			sb.append("#").append(tc).append(" ")
			  .append(numstack.pop()).append("\n");
		}
		System.out.println(sb);
	}

}

```
