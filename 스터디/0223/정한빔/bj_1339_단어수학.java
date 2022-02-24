import java.io.*;
import java.util.*;
/*단어 수학 문제는 N개의 단어로 이루어져 있으며, 각 단어는 알파벳 대문자로만 이루어져 있다. 
 *각 알파벳 대문자를 0부터 9까지의 숫자 중 하나로 바꿔서 N개의 수를 합하는 문제이다. 
 *N개의 단어가 주어졌을 때, 그 수의 합을 최대로 만드는 프로그램을 작성하시오.
 *
 * ABCD
 *  = A*1000 + B*100 + C*10 + D*1 
 * 
 *dfs
 * */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine()); //단어 개수
		
		//N개의 단어 입력
		String[] word = new String[N];
		
		for (int i = 0; i < N; i++) {
			word[i] = br.readLine();	
		}
		int [] alphabet = new int[26];
		
		for (int i = 0; i < N; i++) {
			int index = (int) Math.pow(10, word[i].length()-1);
			for (int j = 0; j < word[i].length(); j++) {
				alphabet[word[i].charAt(j)-65]+=index;
				index/=10;
			}
		}
		System.out.println(Arrays.toString(alphabet));
		Arrays.sort(alphabet);
		System.out.println(Arrays.toString(alphabet));
		int sum=0;
		int index = 25; //alphabet[25] 마지막값
		for (int i = 9; i >=0; i--) {
			//System.out.println(i+"*"+alphabet[index]);
			sum+=i*alphabet[index];
			index--;
		}
		System.out.println(sum);
		br.close();
	}	
}
