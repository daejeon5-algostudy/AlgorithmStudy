import java.io.*;
import java.util.*;
public class Main_bj_1780_종이의개수 {
	static int zero = 0;
	static int one = 0;
	static int mione = 0;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[][] map= new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solve(0,0,N,map);
		sb.append(mione).append("\n").append(zero).append("\n").append(one);
		System.out.println(sb.toString());
		br.close();

	}
	// 분할 정복
	static void solve(int ni, int nj, int size, int[][] map) {
		if(size==1) { // 쪼개고 쪼개서 1x1 사이즈의 사각형이 된다면 한 칸 안에 든 숫자 확인 후 알맞게 ++
			
			if(map[ni][nj]==0) {
				zero++;
				
			}else if(map[ni][nj]==1) {
				one++;
				
			}else {
				mione++;
				
			}
			return;
		}
		
		
		int start = map[ni][nj]; //첫 값을 받아서 그림을 돌면서 확인해서 첫 값과 다르면 같은 숫자가 아니라고 판단
		boolean check =true;//같은지 확인
		// for 문으로 배열을 돌면서 확인한다.
	here:for(int i=ni; i<ni+size;i++) {
			for(int j=nj; j<nj+size; j++) {
				if(map[i][j]!=start) {
					//다르다면 false
					check =false;
					break here;
				}
			}
		}
		if(check) {
		
			if(start==0 ) {
				zero++;
				
			}else if(start==1) {
				one++;
			}else {
				mione++;
			}
		}else {
			//사각형 안의 숫자가 모두 같지 않다면 9조각으로 나눠서 다시 함수에 넣어준다. 
			solve(ni,          nj,           size/3, map);//1 1
			solve(ni,          nj+ size/3,   size/3, map); // 1 2
			solve(ni +size/3,  nj,           size/3, map);// 2 1
			solve(ni+ size/3,  nj+ size/3,   size/3, map); // 2 2
			solve(ni+2*size/3, nj,           size/3, map); // 3 1
			solve(ni,          nj+2*size/3,  size/3, map); // 1 3
			solve(ni+2*size/3, nj+2*size/3,  size/3, map);// 3 3
			solve(ni+size/3,   nj+2*size/3,  size/3, map);// 2 3
			solve(ni+2*size/3, nj+size/3,    size/3, map);// 3 2
			
		}
	}
}
