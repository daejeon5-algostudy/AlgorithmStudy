import java.io.*;
import java.util.*;
public class Main_bj_16637_괄호추가하기 {
	static int max=-Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		List<Character> op = new ArrayList<>();
		List<Integer> nums = new ArrayList<Integer>();
		
		
		String str = br.readLine();
		//연산자와 숫자를 따로 저장한다. 
		for(int i=0; i<N;i++) {
			if(str.charAt(i)=='*'||str.charAt(i)=='+'||str.charAt(i)=='-') {
				op.add(str.charAt(i));
			}else {
				nums.add(str.charAt(i)-'0');
			}
			
				
			
		}
		
		solve(op, nums, 0, nums.get(0));
		System.out.println(max);
	
		
	}
	static void solve(List<Character> op, List<Integer> nums, int idx, int sum) {
		
		//기저 조건 아래에서 idx+1 혹은 idx+2를 파라미터로 solve함수 호출하기 때문에 idx가 op.size를 넘어가는 경우가 있기 때문에
		// 같거나 크게 
		if(idx>=op.size()) {
			max = Math.max(max, sum);
			
			return;
		}
		//뒤에 괄호 x ----------->sum + b + c
		solve(op,nums, idx+1, calculate(op.get(idx), sum, nums.get(idx+1)));
		
		//뒤에 연산자 괄호 o---------> sum+(b+c)
		//뒤에 두 수를 먼저 연산하고 현재의 숫자와 연산 후 재귀로 넘긴다. 이 때 연산자 인덱스는 현재의 +2 
		//다음 것을 미리 했기 때문에 
		if(idx+1<op.size()) {
			int r = calculate(op.get(idx+1), nums.get(idx+1), nums.get(idx+2));
			solve(op, nums,idx+2, calculate(op.get(idx),sum, r));
		}
	}
	static int calculate(char op, int a, int b) {
		if(op=='*')return a*b;
		if(op=='+')return a+b;
		else return a-b;
	}

}
