package practice0310;
import java.io.*;
import java.util.*;
public class Main_bj_17136_색종이붙이기3 {

		
	    static int[] paper = { 0, 5, 5, 5, 5, 5 };
	    static int result = Integer.MAX_VALUE;
	 
	    public static void main(String[] args) throws Exception {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        
	        int map[][] = new int[10][10];
	        
	        map = new int[10][10];
	        for (int i = 0; i < map.length; i++) {
	        	StringTokenizer st = new StringTokenizer(br.readLine());
	            for (int j = 0; j < map[i].length; j++) {
	                map[i][j] = Integer.parseInt(st.nextToken());
	            }
	        }
	 
	        solve(0, 0, 0, map);
	 
	        if (result == Integer.MAX_VALUE) {
	            result = -1;
	        }
	 
	        System.out.println(result);
	        
	        br.close();
	    }
	    
	    public static void solve(int i, int j, int cnt, int[][] map) {
	        if (i == 9 && j > 9) {
	            result = Math.min(result, cnt);
	            return;
	        }
	 
	        // 최솟값을 구해야하는데 result보다 cnt가 커지는 순간
	        // 더 이상 탐색할 필요가 없어짐.
	        if (result <= cnt) {
	            return;
	        }
	 
	        //한줄로 탐색이 완료되면 
	        if (j > 9) {
	            solve(i + 1, 0, cnt, map);
	            return;
	        }
	 
	        if (map[i][j] == 1) {
	            for (int size = 5; size >= 1; size--) {
	            	//일단 가능하면 붙이고 보기
	                if (paper[size] > 0 && possible(i, j, size, map)) {
	                    attach(i, j, size, 0, map); // 색종이를 붙임.
	                    paper[size]--;
	                    solve(i, j + 1, cnt + 1, map);
	                    attach(i, j, size, 1, map); // 색종이 떼어내기
	                    paper[size]++;
	                }
	            }
	        } else { // 오른쪽으로 이동.
	            solve(i, j + 1, cnt, map);
	        }
	    }
	    
	    // 색종이를 붙일 수 있는지 확인
	    public static boolean possible(int i, int j, int size, int[][] map) {
	        for (int ni = i; ni < i + size; ni++) {
	            for (int nj = j; nj < j + size; nj++) {
	                if ( ni < 10 && nj < 10 && map[ni][nj]==1 ) {
	                    continue;
	                }else {
	                	return false;
	                }
	 
	            }
	        }
	        return true;
	    }
	 
	    
	    //색종이 붙여보자
	    
	    public static void attach(int i, int j, int size, int num, int[][] map) {
	        for (int ni = i; ni < i + size; ni++) {
	            for (int nj = j; nj < j + size; nj++) {
	                map[ni][nj] = num;
	            }
	        }
	    }	
}
