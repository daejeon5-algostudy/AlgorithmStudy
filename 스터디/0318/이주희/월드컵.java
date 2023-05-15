import java.io.*;
import java.util.*;

public class Main_bj_6987_월드컵 {
	
	static int[][] match;
	static final int TEAM_NUMBER = 6;
	static int[][] score;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_6987.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int matchCount = 0;		// 총 경기 횟수
		for(int i=1; i<TEAM_NUMBER; i++) {
			matchCount += i;
		}
		
		match = new int[matchCount][2];	 // i번째 경기 때 경기할 팀의 번호 ex)match[0][0] : A, match[0][1] : B => 0번째에 A와 B가 경기를 함.
		int idx=0;
		for(int i=0; i<TEAM_NUMBER; i++) {
			for(int j=i+1; j<TEAM_NUMBER; j++) {
				match[idx][0] = i;
				match[idx++][1] = j;
			}
		}
		
		score = new int[TEAM_NUMBER][3];	// [0]: 이긴 횟수, [1]: 비긴 횟수: [2]: 진 횟수
		
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int res = 1;
			
			for(int a=0; a<TEAM_NUMBER; a++) {
				int total=0;
				
				int win = score[a][0] = Integer.parseInt(st.nextToken());
				int draw = score[a][1] = Integer.parseInt(st.nextToken());
				int lose = score[a][2] = Integer.parseInt(st.nextToken());
				
				total += win + draw + lose;
				if(total != 5) {
					res = 0;
				}
			}
			
			if(res==1 && !play(0)) {
				res = 0;
			}
			
			sb.append(res).append(" ");
			
		}
		System.out.println(sb.toString());
		br.close();
		
	}
	
	static boolean play(int turn) {
		if(turn == 15) {
			return true;
		}
		
		// i번째 경기
		// A 승 / B 패
		int team1 = match[turn][0];
		int team2 = match[turn][1];
		
		if(score[team1][0] > 0 && score[team2][2] > 0) {
			score[team1][0]--;
			score[team2][2]--;
			if(play(turn+1)) return true;
			score[team1][0]++;
			score[team2][2]++;
		}
		
		// A 무 / B 무
		if(score[team1][1] > 0 && score[team2][1] > 0) {
			score[team1][1]--;
			score[team2][1]--;
			if(play(turn+1)) return true;
			score[team1][1]++;
			score[team2][1]++;
		}
		
		// A 패 / B 승
		if(score[team1][2] > 0 && score[team2][0] > 0) {
			score[team1][2]--;
			score[team2][0]--;
			if(play(turn+1)) return true;
			score[team1][2]++;
			score[team2][0]++;
		}
		
		return false;
	}

}
