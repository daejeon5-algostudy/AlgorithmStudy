import java.io.*;
import java.util.*;

/* 붙일 수 있는 색종이의 최솟값을 찾는 문제
 * 각 색종이는 5장씩
 */
public class Main {
	
	static int ans;
	static int[][] paper;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		paper = new int[10][10];

		StringTokenizer st;
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 10; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 100;
		int[] remain = new int[] {0, 5, 5, 5, 5, 5};	// 남은 색종이의 갯수
		bp(0, 0, remain, 0);	// 0, 0 부터 붙이기 시작
		
		if(ans==100) ans=-1;
		System.out.println(ans);
		br.close();
	}

	static void bp(int x, int y, int[] remain, int cnt) {
		if(x==9 && y==10) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		if(cnt > ans) return;
		if(y>=10) {
			bp(x+1, 0, remain, cnt);
			return;
		}
		
		if(paper[x][y]==1) {	// 색종이를 붙인다
			for(int k=5; k>=1; k--) {	// 5사이즈의 종이부터 붙여본다
				if(isAttach(x, y, k) && remain[k]>0) {
					attach(x, y, k);
					remain[k]--;
					bp(x, y+1, remain, cnt+1);
					detach(x, y, k);
					remain[k]++;
				}
			}
		}
		else {	// 다음 줄로 넘어간다
			bp(x, y+1, remain, cnt);
		}
	}
	
	static boolean isAttach(int x, int y, int size) {
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				if(i>=10 || j>=10 || paper[i][j]!=1) return false;
			}
		}
		return true;
	}
	
	static void attach(int x, int y, int size) {
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				paper[i][j] = 2;	// 종이를 붙인다
			}
		}
	}
	
	static void detach(int x, int y, int size) {
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				paper[i][j] = 1; 	// 종이를 뗀다
			}
		}
	}

}
