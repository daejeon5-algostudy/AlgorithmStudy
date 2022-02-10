```java
import java.io.*;
import java.util.*;

public class Solution {

	static int[][] deltas = new int[][] { 
		{-1, 0}, // 상
		{ 0,-1}, // 좌
		{ 0, 1}, // 우
	};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d4_1210"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int[][] arr = new int[100][100];
		for (int tc = 1; tc <= 10; tc++) {
			br.readLine();
			for (int i = 0; i < 100; i++) {
				StringTokenizer token = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 100; j++)
					arr[i][j] = Integer.parseInt(token.nextToken());
			}

			int idx = -1;
			for (int i = 0; i < 100; i++) {
				int a = arr[99][i];
				if (a == 2) {
					idx = i;
					break;
				}
			}
			int i = 98;
			int direct = 1; // 0이면 상, 1이면 좌, 2이면 우 먼저
			while (i > 0) {
				if (direct == 0) {
					if (arr[i - 1][idx] == 1) {
						i--;
						if(idx-1 > 0 && arr[i][idx-1]== 1) 
							direct = 1;
						else if(idx+1 < 100 && arr[i][idx+1] == 1) {
							direct = 2;
						}
					}
				} else {
					int dy = idx + deltas[direct][1];
					if (dy < 0 || dy >= 100) {
						direct = 0;
						continue;
					}
					if (arr[i][dy] == 0) {
						direct = direct == 1 ? 2 : 0;
						continue;
					} else {
						while (true) {
							if (arr[i][dy] == 1) {
								idx = dy;
								dy += direct == 1 ? -1 : 1;
							}
							if (dy < 0 || dy >= 100 || arr[i][dy] == 0) {
								direct = 0;
								break;
							}
						}
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(idx).append("\n");
		}
		System.out.println(sb);
	}

}

```
