```java
import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer token = new StringTokenizer(br.readLine(), " ");
			int H = Integer.parseInt(token.nextToken());
			int W = Integer.parseInt(token.nextToken());

			char[][] arr = new char[H][W];
			boolean check = false;
			int x = 0, y = 0;
			for (int i = 0; i < H; i++) {
				String s = br.readLine();
				for (int j = 0; j < W; j++) {
					arr[i][j] = s.charAt(j);
					if (!check && (arr[i][j] != '.' && arr[i][j] != '*' && arr[i][j] != '#' && arr[i][j] != '-')) {
						x = i;
						y = j;
						check = true;
					}
				}
			}
			
			int direct = 0; // 0 상, 1 우, 2 하, 3 좌
				 if(arr[x][y] == '>') direct = 1;
			else if(arr[x][y] == 'v') direct = 2;
			else if(arr[x][y] == '<') direct = 3;

			int N = Integer.parseInt(br.readLine());
			String s = br.readLine();
			for (int i = 0; i < N; i++) {
				if(s.charAt(i)=='U') {			// 방향 위쪽으로, 한칸 전진
					direct = 0;
					if(x-1 >= 0 && arr[x-1][y] == '.') {
						arr[x-1][y] = '^';
						arr[x][y] = '.';
						x = x - 1;
					}
				} else if (s.charAt(i) == 'R') { // 방향 오른쪽으로, 한칸 전진
					direct = 1;
					if (y + 1 < W && arr[x][y + 1] == '.') {
						arr[x][y + 1] = '>';
						arr[x][y] = '.';
						y = y + 1;
					}
				} else if (s.charAt(i) == 'D') { // 방향 아래로, 한칸 전진
					direct = 2;
					if (x + 1 < H && arr[x + 1][y] == '.') {
						arr[x + 1][y] = 'v';
						arr[x][y] = '.';
						x = x + 1;
					}
				} else if (s.charAt(i) == 'L') { // 방향 왼쪽으로, 한칸 전진
					direct = 3;
					if (y - 1 >= 0 && arr[x][y - 1] == '.') {
						arr[x][y - 1] = '<';
						arr[x][y] = '.';
						y = y - 1;
					}
				} else if (s.charAt(i) == 'S') { // 보고있는 방향으로 포탄발사
					if (direct == 0) { // 위
						for (int j = x - 1; j >= 0; j--) {
								 if(arr[j][y] == '#') break;
							else if(arr[j][y] == '*') {
								arr[j][y] = '.';
								break;
							}
						}
					} else if(direct == 1) { // 우
						for (int j = y + 1; j < W; j++) {
							 if(arr[x][j] == '#') break;
						else if(arr[x][j] == '*') {
							arr[x][j] = '.';
							break;
						}
					}
					} else if(direct == 2) { // 하
						for (int j = x + 1; j < H; j++) {
							 if(arr[j][y] == '#') break;
						else if(arr[j][y] == '*') {
							arr[j][y] = '.';
							break;
						}
					}
					} else if(direct == 3) { // 좌
						for (int j = y - 1; j >= 0; j--) {
							 if(arr[x][j] == '#') break;
						else if(arr[x][j] == '*') {
							arr[x][j] = '.';
							break;
						}
					}
					}
				}
			}
			if(direct == 0) {
				arr[x][y] = '^';
			} else if(direct == 1) {
				arr[x][y] = '>';
			} else if(direct == 2) {
				arr[x][y] = 'v';
			} else if(direct == 3) {
				arr[x][y] = '<';
			} 
			sb.append("#").append(tc).append(" ");
			for(char[] cc : arr) {
				for(char c : cc)
					sb.append(c);
				sb.append("\n");
			}
		}
		System.out.println(sb);
		br.close();
	}

}

```
