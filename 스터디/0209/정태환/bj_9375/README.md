# 패션왕 신혜빈 _ 9375 _ 백준

### 해빈이는 패션에 매우 민감해서 한번 입었던 옷들의 조합을 절대 다시 입지 않는다. 예를 들어 오늘 해빈이가 안경, 코트, 상의, 신발을 입었다면, 다음날은 바지를 추가로 입거나 안경대신 렌즈를 착용하거나 해야한다. 해빈이가 가진 의상들이 주어졌을때 과연 해빈이는 알몸이 아닌 상태로 며칠동안 밖에 돌아다닐 수 있을까?
---
## 입력
첫째 줄에 테스트 케이스가 주어진다. 테스트 케이스는 최대 100이다.

각 테스트 케이스의 첫째 줄에는 해빈이가 가진 의상의 수 n(0 ≤ n ≤ 30)이 주어진다.
다음 n개에는 해빈이가 가진 의상의 이름과 의상의 종류가 공백으로 구분되어 주어진다. 같은 종류의 의상은 하나만 입을 수 있다.
모든 문자열은 1이상 20이하의 알파벳 소문자로 이루어져있으며 같은 이름을 가진 의상은 존재하지 않는다.

---
## 출력 
각 테스트 케이스에 대해 해빈이가 알몸이 아닌 상태로 의상을 입을 수 있는 경우를 출력하시오.

---
## 입력 예시 1
2<br>
3<br>
hat headgear<br>
sunglasses eyewear<br>
turban headgear<br>
3<br>
mask face<br>
sunglasses face<br>
makeup face<br>

---
## 출력 예시 1
5<br>
1<br>

---
## 분석
입력으로 주어지는 의상의 이름은 모두 다르게 주어지므로 의미없는 입력
의상의 종류를 구분하고, 각 종류의 개수를 얻는것이 중요함
의상의 종류당 하나의 의상만 입을 수 있음을 인지

각 의상 종류의 갯수를 모두 곱한 결과에서 아무것도 입지않은 경우를 제외하기 위하여 1을 감해준다

---
``` java
import java.io.*;
import java.util.*;

public class Main_bj_9375_패션왕신해빈 {

	static ArrayList<String> s;
	static ArrayList<Integer> snum;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_9375"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			s = new ArrayList<>();
			snum = new ArrayList<>();
			int idx = 0;

			for (int t = 0; t < N; t++) {
				StringTokenizer token = new StringTokenizer(br.readLine(), " ");
				token.nextToken();
				String sin = token.nextToken();
				for (int i = 0; i < snum.size(); i++) {
					String st = s.get(i);
					if (st.equals(sin)) {
						snum.set(i, snum.get(i) + 1);
						sin = null;
						break;
					}
				}
				if (sin != null) {
					s.add(sin);
					snum.add(1);
					idx++;
				}
			}
			int ans = 1;
			for (int i = 0; i < snum.size(); i++)
				ans *= snum.get(i) + 1;
			System.out.println(ans - 1);
		}
	}


}
```
