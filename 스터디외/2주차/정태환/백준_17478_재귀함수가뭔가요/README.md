```Java
package a0203.iorecursive;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main_bj_17478_재귀함수가뭔가요_대전_5반_정태환 {

	static int N;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17478"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		cf(1);
		System.out.println(sb);
	}
	
	static void cf(int n) {
		for(int i=1; i<n; i++) sb.append("____");
		sb.append("\"재귀함수가 뭔가요?\"\n");
		if(n-1==N) {
			for(int i=1; i<n; i++) sb.append("____");
			sb.append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
			for(int i=1; i<n; i++) sb.append("____");
			sb.append("라고 답변하였지.\n");
			return;
		} else {
			for(int i=1; i<n; i++) sb.append("____");
			sb.append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
			for(int i=1; i<n; i++) sb.append("____");
			sb.append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
			for(int i=1; i<n; i++) sb.append("____");
			sb.append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
			cf(n+1);
		}
		for(int i=1; i<n; i++) sb.append("____");
		sb.append("라고 답변하였지.\n");
	}
	
}
```
