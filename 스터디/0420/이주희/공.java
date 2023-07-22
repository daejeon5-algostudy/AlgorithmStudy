import java.io.*;
import java.util.*;

public class Main {
	
	static final int PLAYER = 9;
	static int N, answer;
	static int[][] score;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_bj_17281.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		score = new int[N][PLAYER];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<PLAYER; j++) {
				score[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] order = new int[PLAYER];
		order[3] = 0;		//4번 타자는 1번으로 고정 (인덱스는 0부터 시작하므로, 3번타자는 0번으로 고정)
		boolean[] isSelected = new boolean[PLAYER];
		isSelected[0] = true;
		//9명의 선수 순번 나열하기
		answer = 0;
		perm(0, order, isSelected);
		System.out.println(answer);
	}
	
	static void perm(int idx, int[] order, boolean[] isSelected) {
		if(idx == PLAYER) {		//선수들 순서 선택 완료
			answer = Math.max(answer, play(order));
			return;
		}
		
		if(idx == 3) {
			perm(idx+1, order, isSelected);
		} else {
			for(int i=0; i<PLAYER; i++) {
				if(isSelected[i]) continue;
				isSelected[i] = true;
				order[idx] = i;
				perm(idx+1, order, isSelected);
				isSelected[i] = false;
			}
		}
	}
	
	static int play(int[] order) {
		int total=0, idx=0;
		
		for(int i=0; i<N; i++) {
			int out = 0;
			boolean[] ground = new boolean[4];	//1루, 2루, 3루 타자 진출 여부
			
			while(true) {
				if(out == 3) break;
				int res = score[i][order[idx]];		//이번 경기의 결과
				idx = (idx+1) % PLAYER;
				switch(res) {
				case 0:
					out++;
					break;
				case 1:
					if(ground[3]) total++;
					for(int j=3; j>=1; j--) {
						ground[j] = ground[j-1];
					}
					ground[1] = true;		//타자 진출
					break;
				case 2:
					for(int j=2; j<=3; j++) {
						if(ground[j]) total++;
						ground[j] = false;
					}
					if(ground[1]) {
						ground[3] = true;
						ground[1] = false;
					}
					ground[2] = true;		// 타자 진출
					break;
				case 3:
					for(int j=1; j<=3; j++) {
						if(ground[j]) total++;
						ground[j] = false;
					}
					ground[3] = true;
					
					break;
				case 4:
					for(int j=1; j<=3; j++) {
						if(ground[j]) total++;
						ground[j] = false;
					}
					total++;	//타자 홈
					break;
				}
			}
		}
		
		return total;
	}
}
