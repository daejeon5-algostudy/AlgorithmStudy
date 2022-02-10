# 문제
해빈이는 패션에 매우 민감해서 한번 입었던 옷들의 조합을 절대 다시 입지 않는다. 예를 들어 오늘 해빈이가 안경, 코트, 상의, 신발을 입었다면, 다음날은 바지를 추가로 입거나 안경대신 렌즈를 착용하거나 해야한다. 해빈이가 가진 의상들이 주어졌을때 과연 해빈이는 알몸이 아닌 상태로 며칠동안 밖에 돌아다닐 수 있을까?

# 입력
첫째 줄에 테스트 케이스가 주어진다. 테스트 케이스는 최대 100이다.
- 각 테스트 케이스의 첫째 줄에는 해빈이가 가진 의상의 수 n(0 ≤ n ≤ 30)이 주어진다.
- 다음 n개에는 해빈이가 가진 의상의 이름과 의상의 종류가 공백으로 구분되어 주어진다.
-  같은 종류의 의상은 하나만 입을 수 있다.

모든 문자열은 1이상 20이하의 알파벳 소문자로 이루어져있으며 같은 이름을 가진 의상은 존재하지 않는다.

# 분석 
- 같은 의상은 없고, 같은 종류의 의상은 하나만 입을 수 있다는 점을 통해서 한 종류의 의상이 N개 있을 때 아무 것도 입지 않았을 경우를 포함한 N+1의 경우의 수가 있음을 알 수 있다.
- 그러므로 (a 종류 의상의 수+1) x (b 종류의 의상의 수+1) x ...을 하면 전체 경우의 수를 구할 수 있다.
- 아무 것도 입지않은 경우의 수를 제외하기 위해서 -1을 하면 답을 구할 수 있다. 

# 코드 
들어 올 수 있는 옷의 종류가 가변 적이기 때문에 ArrayList를 사용하였고, 옷의 종류와 수량을 하나에 객체에 저장하기 위해서 클래스를 생성하였다.


---
``` java
import java.io.*;
import java.util.*;
public class Main_bj_9375_패션왕신해빈 {
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t =0 ; t<T;t++) {
			int N = Integer.parseInt(br.readLine());
			List<Dress> list = new ArrayList<Dress>();
			int sum=0;
			for(int x = 0 ;x < N; x++) {
				StringTokenizer st=new StringTokenizer(br.readLine()," ");	
				st.nextToken();
				String clo=st.nextToken();
				boolean dup = false;
				for(int i =0; i<list.size();i++) {		
					if(clo.equals(list.get(i).item)) {
						list.get(i).yang++;
						dup = true;
						break;
					}				
				}
				if(!dup)list.add(new Dress(clo, 1));
			}
			sum=1;
			for(int i =0;i<list.size();i++) {
				sum*=(list.get(i).yang+1);		
			}
			sum-=1;
			sb.append(sum).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
class Dress{
	String item;
	int yang;
	public Dress(String item, int yang) {
		this.item=item;
		this.yang=yang;
	}
}

``` 
