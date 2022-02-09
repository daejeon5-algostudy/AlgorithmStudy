백준 9375번 : 패션왕 신해빈      
===============
[문제링크](https://www.acmicpc.net/problem/9375)
``` java
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();// tc
		for (int tc = 0; tc < T; tc++) {
			int n = sc.nextInt(); // 의상수
      
			// 갖고있는 의상 받기 중에 의상종류만 필요
			ArrayList<String> cloth = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				String trash =sc.next();
				cloth.add(sc.next()); 
			}
			
			int sum=1;
      //중복 횟수 찾기(중복횟수 = 의상종류별 의상개수)
			Set<String> set = new HashSet<String>(cloth);
			for (String str : set) {
				int ans=0;
				ans = Collections.frequency(cloth, str)+1;
				sum *= ans;
			}
			System.out.println(sum-1);
			
		}
		sc.close();
	}
}
```
