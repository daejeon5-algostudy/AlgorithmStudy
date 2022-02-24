import java.io.*;
import java.util.*;

public class Main_bj_1780_종이의개수 {
	
	static int[][] paper;
	static int[] answer;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_1780.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		paper = new int[n][n];
		answer = new int[3];
		
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken())+1; // -1 : 0 , 0 : 1, 1 : 2
			}
		}
		
		cut(n, 0, 0);

		for(int i=0; i<3; i++) {
			System.out.println(answer[i]);
		}
	}
	
	static void cut(int len, int r, int c) {
		if(check(len, r, c)) {
			answer[paper[r][c]]+=1;
			
			return;
		}
		
		len /= 3;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				cut(len, r+i*len, c+j*len);
			}
		}
		
	}

	static boolean check(int len, int r, int c) {
		for(int i=r; i<r+len; i++) {
			for(int j=c; j<c+len; j++) {
				if(paper[i][j] != paper[r][c]) return false;
			}
		}
		
		return true;
	}
}
