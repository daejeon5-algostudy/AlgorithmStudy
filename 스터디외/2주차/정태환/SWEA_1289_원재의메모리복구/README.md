```Java
import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			String s = br.readLine();
			int count = 0;
			boolean check = false;
			for(int i=0; i<s.length(); i++) {
				if(check && s.charAt(i) == '0') {
					count++;
					check = false;
				}
				if(!check && s.charAt(i) == '1') {
					count++;
					check = true;
				}
			}
			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}
		System.out.println(sb.toString());
		
		br.close();
	}

}

```
