풀이
-----
* 이차원 배열을 통한 LCS 알고리즘을 수행

코드
-----

```java
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_9251.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {

			String A = bf.readLine();
			String B = bf.readLine();
			int[][] mat = new int[A.length()][B.length()];
			int max = 0;
			for(int i=0;i<A.length();i++)
			{
				boolean Add = false;
				for(int j=0;j<B.length();j++)
				{
					if(i-1>=0)
						mat[i][j] = mat[i-1][j];
					if(j-1>=0)
						mat[i][j] = Integer.max(mat[i][j], mat[i][j-1]);
					if(A.charAt(i)==B.charAt(j))
					{
						if(i-1>=0 && j-1>=0)
							mat[i][j] = mat[i-1][j-1]+1;
						else
							mat[i][j] = 1;
					}
					if(max<mat[i][j])
						max = mat[i][j];
				}
			}
			for(int i=0;i<mat.length;i++)
			{
				System.out.println(Arrays.toString(mat[i]));
			}
			System.out.println(max);
		}
	}

}
