import java.io.*;
import java.util.*;

public class Main_bj_10819_차이를최대로 {

	static int max;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_10819.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] input = new int[n];
		int[] numbers = new int[n];
		
		max = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		perm(n, 0, 0, input, numbers);
		System.out.println(max);
		br.close();
	}
	
	static void perm(int n, int cnt, int flag, int[] input, int[] numbers ) {
		if(cnt==n) {
			int sum = 0;
			for(int i=0; i<n-1; i++) {
				sum += Math.abs(numbers[i] - numbers[i+1]);
			}
			
			max = Math.max(max, sum);
			return;
		}
		
		for(int i=0; i<n; i++) {
			if((flag & 1<<i) != 0) continue;
			numbers[cnt] = input[i];
			perm(n, cnt+1, flag | 1<<i, input, numbers);
		}
	}

}
