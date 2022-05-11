package algoStudy.astudy;

import java.io.*;
import java.util.*;

public class Main_bj_G5_2011_암호코드 {

	static int mod=1_000_000;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2011"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		int ans=0;
		String pw=br.readLine();
		int[] memo=new int[pw.length()+1];
		if(pw.charAt(0)!='0') {
			memo[0]=1;
			memo[1]=1;
			for(int i=2; i<=pw.length(); i++) {
				char now=pw.charAt(i-1);
				char left=pw.charAt(i-2);
				if(now=='0') {
					if(left=='1' || left=='2') memo[i]=memo[i-2]%mod;
					else break;
				}
				else {
					if(left=='0') memo[i]=memo[i-1]%mod;
					else {
						int n=(left-48)*10+(now-48);
						if(n<=26 && n>0) memo[i]=(memo[i-2]+memo[i-1])%mod;
						else memo[i]=memo[i-1]%mod;
					}
				}
			}
			ans=memo[pw.length()]%mod;
		}
		System.out.println(ans);
		br.close();
	}
	
}
