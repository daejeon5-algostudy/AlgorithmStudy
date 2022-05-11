package p05;
import java.io.*;
import java.util.*;
public class Main_bj_2011_암호코드 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input= br.readLine().toCharArray(); 
		int[] dp = new int[input.length+1];
		dp[0]=1;
		dp[1]=1;
		int result=0;
		// 0부터 시작 0
		if(input[0]=='0') {
			System.out.println(0);
		}else {
			for(int i=2;i<=input.length; i++) {
				int num = (input[i-2]-'0')*10+(int)(input[i-1]-'0');
				if(input[i-1]=='0') {
					if(input[i-2]=='1'||input[i-2]=='2')dp[i]=dp[i-2];
					else break;
				}else if(input[i-2]=='0') {
					dp[i]=dp[i-1];
				}else if(10<num&&num<=26) {
					dp[i]=(dp[i-2]+dp[i-1])%1000000;
				}else {
					dp[i]=dp[i-1];
				}
			}
			
			System.out.println(dp[input.length]%1000000);
		}
		br.close();

	}

}
