import java.io.*;
import java.util.*;
/* 연산자와 숫자를 따로 저장한다
 *  
7
8*3+5+2

 
 * */
public class Main {
	static int[] num;
	static char[] op;
	static int N, Max=Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine()); //수식길이
		String s = br.readLine();
		//연산자와 숫자 따로 저장
		num = new int[N/2+1];
		op = new char[N/2];
		for (int i = 0; i < N; i++) {
			char c= s.charAt(i);
			if(i%2==0) num[i/2]=c-'0';
			else op[i/2]=c;
		}
		dfs(0,num[0]);
		System.out.println(Max);
		br.close();
	}
	
	static void dfs(int cnt, int sum) {
		if(cnt >= op.length) {
			if(Max<sum) Max=sum;
			return;
		}
		//괄호 안치고계산 (현재 값이랑 다음 값 계산)
		int sum1 = calc(op[cnt],sum,num[cnt+1]);
		System.out.println("괄호 안치고 계산 ("+cnt+") :"+sum+op[cnt]+num[cnt+1]+"="+sum1);
		dfs(cnt+1,sum1);
		//괄호 치고 계산 (다음값 먼저 계산해주고 계산)
		if(cnt<op.length-1) {//다음 연산자가 있는 경우에만 해줘야됨
		int sum2 = calc(op[cnt+1],num[cnt+1],num[cnt+2]);
		System.out.println("괄호 치고 계산 ("+cnt+") :"+sum+op[cnt]+"("+num[cnt+1]+op[cnt+1]+num[cnt+2]+")="+sum+op[cnt]+sum2);
		dfs(cnt+2, calc(op[cnt],sum,sum2));
		}
	}
	static int calc(char op, int a, int b) {
		switch(op) {
		case '+': 
			return a+b;
		case '-': 
			return a-b;
		case '*': 
			return a*b;
		}
		return 0;
	}
	
}
