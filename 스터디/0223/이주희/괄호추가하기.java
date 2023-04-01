import java.io.*;
import java.util.*;

public class Main_bj_16637_괄호추가하기 {
	
	static List<Character> ops;		// 연산자만 담는 배열
	static List<Integer> nums;		// 피연산자만 담는 배열
	static int ans;				

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_bj_16637.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		
		ops = new ArrayList<>();
		nums = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			char c = s.charAt(i);
			if(c == '+' || c == '-' || c == '*')
				ops.add(c);
			else
				nums.add(Character.getNumericValue(c));
		}
		
		ans = Integer.MIN_VALUE;
		DFS(nums.get(0), 0);	// 합계, 계산해야 할 연산자의 인덱스
		
		System.out.println(ans);
		br.close();
	}
	
	static void DFS(int sum, int opsIdx) {
		if(opsIdx >= ops.size()) {	// 끝까지 연산
			ans = Math.max(ans, sum);
			return;
		}
		
		int res = cal(sum, nums.get(opsIdx+1), ops.get(opsIdx));
		DFS(res, opsIdx+1);
		
		// 괄호 연산
		if(opsIdx+1 < ops.size()) {
			res = cal(nums.get(opsIdx+1), nums.get(opsIdx+2), ops.get(opsIdx+1));
			sum = cal(sum, res, ops.get(opsIdx));
			DFS(sum, opsIdx+2);
		}
	}
	
	static int cal(int num1, int num2, char ops) {
		
		switch(ops) {
		case '+' :
			return num1 + num2;
		case '-' :
			return num1 - num2;
		case '*' :
			return num1 * num2;
		}
		
		return -1;
	}

}
