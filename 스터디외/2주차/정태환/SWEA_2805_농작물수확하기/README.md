```java
import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++)
					arr[i][j] = s.charAt(j) - 48;
			}
			
			int mid = N / 2;
			int sum = 0;
			int idx = 0;
			for (int i = 0; i < N; i++) {
				if(i <= mid)
					idx = i + 1;
				else
					idx = N - i;
				for (int j = 0; j < idx ; j++) {
					if(j == 0)
						sum += arr[i][mid];
					else {
						sum += arr[i][mid + j];
						sum += arr[i][mid - j];
					}
				}
			}
			sb.append("#").append(tc).append(" ")
			  .append(sum).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

}

```
