import java.io.*;
import java.util.*;

/*
 * kC6
 */

public class Main_bj_6603_로또 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_6603.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st;
		String s;
		while(true) {
			s = br.readLine();
			if(s.equals("0")) break;
			
			st = new StringTokenizer(s);
			
			int n = Integer.parseInt(st.nextToken());
			int r = 6;
			int[] input = new int[n];
			int[] selected = new int[r];
			
			for(int i=0; i<n; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			
			combi(0, 0, n, r, input, selected, sb);
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static void combi(int start, int cnt, int n, int r, int[] input, int[] selected, StringBuilder sb) {
		if(cnt==r) {
			for(int i=0; i<r; i++)
				sb.append(selected[i]).append(" ");
			sb.append("\n");
			return;
		}
	
		for(int i=start; i<n; i++) {
			selected[cnt] = input[i];
			combi(i+1, cnt+1, n, r, input, selected, sb);
		}
	}

}
