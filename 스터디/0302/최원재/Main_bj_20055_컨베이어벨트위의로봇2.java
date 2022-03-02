package practice0226;
import java.io.*;
import java.util.*;
public class Main_bj_20055_컨베이어벨트위의로봇2 {
	/*
	 * 올리는 위치 0
	 * 내리는 위치 N-1
	 * 올리는 위치에 로봇 올리거나 로봇 한칸 이동하면 이동하는 곳 내구도 -1
	 * 1. 벨트가 로봇과 함께 회전
	 * 2. 앞 칸에 로봇이 없거나 내구도가 1이상이면 로봇은 한 칸 앞으로;
	 * 3.올리는 위치에 내구도 0 아니라면 올린다.
	 * 4. 
	 */
	static int belt[];
	static int n;
	static int k;
	static boolean robot[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		int count = 0;
		belt = new int[n*2];
		robot = new boolean[n];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n*2; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}

		while(true) {
			circle();
			count++;
			move();
			if(isEnd()) {
				System.out.println(count);
				break;
			}
		}
	}
	
	static void circle() {
		int end = belt[belt.length-1];
		for(int i = belt.length-2; i > -1; i--) {
			belt[i+1] = belt[i];
		}
		belt[0] = end;
		
		for(int i = n-2; i > -1; i--) {
			robot[i+1] = robot[i];
		}
		robot[0] = false;
		
	}
	
	static boolean isEnd() {
		int count = 0;
		for(int i = 0; i < belt.length; i++) {
			if(belt[i] == 0) {
				count++;
			}
		}
		if(count >= k) {
			return true;
		}else {
			return false;
		}
	}

	static void move() {
		if(robot[n-1]) {
			robot[n-1] = false;
		}
		
		for(int i = n-2; i > 0; i--) {
			if(robot[i] && !robot[i+1] && belt[i+1] > 0) {
				robot[i+1] = true;
				robot[i] = false;
				belt[i+1]--;
			}
		}
		
		if(belt[0] > 0 && !robot[0]) {
			robot[0] = true;
			belt[0]--;
		}
		
	}
}
